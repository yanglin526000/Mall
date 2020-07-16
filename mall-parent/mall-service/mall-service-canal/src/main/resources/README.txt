1.Install Lua in CentOS8:
    Preparation:
        yum install gcc
        yum install make
    Installation:
        curl -R -O http://www.lua.org/ftp/lua-5.4.0.tar.gz
        tar zxf lua-5.4.0.tar.gz
        cd lua-5.4.0
        make linux test
        make install
    Test:
        lua
        lua -i

2.Install OpenResty in CentOS8:
    Preparation:
        yum install yum-utils
        yum-config-manager --add-repo https://openresty.org/package/centos/openresty.repo
    Installation:
        yum install -y openresty
    Open 80 port:
        firewall-cmd --zone=public --remove-port=80/tcp --permanent
        firewall-cmd --reload
    Start:
        systemctl start openresty
    Restart:
        systemctl restart openresty
    Stop:
        systemctl stop openresty
    Reboot CentOS8:
        reboot
    Visit local site:
        http://172.16.26.128/
    See current OpenResty version:
        /usr/local/openresty/bin/openresty -V
    Install cmd tool for openresty named resty:
        yum install -y openresty-resty

3.Install Redis in CentOS8:
    Pull image:
        docker pull redis
    Run:
        docker run -itd --name redis-mall -p 6379:6379 redis
    Test:
        docker exec -it redis-mall /bin/bash
        redis-cli

4.Test Openresty connect MySQL8 via Lua

    Config in /etc/mysql/my.cnf about MySQL8 in docker!!!!!!
    ===================================================================
    mkdir –p /home/data/
    mkdir –p /home/data/mysql/
    mkdir –p /home/data/mysql/conf/
    mkdir –p /home/data/mysql/data/

    docker cp MySQL8.0.20:/etc/mysql/my.cnf /home/data/mysql/conf/my.cnf
    vi /home/data/mysql/conf/my.cnf
        default_authentication_plugin=mysql_native_password

    docker run --name mysql8-Lua \
    -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root \
    --mount type=bind,src=/home/data/mysql/conf/my.cnf,dst=/etc/mysql/my.cnf \
    --mount type=bind,src=/home/data/mysql/data,dst=/var/lib/mysql \
    --restart=on-failure:3 \
    -d mysql:8.0.20
    ===================================================================

    mkdir /root/lua/
    touch /root/lua/update_content.lua
    vi /root/lua/update_content.lua
        ngx.header.content_type="application/json;charset=utf8"
        local cjson = require("cjson")
        local mysql = require("resty.mysql")
        local uri_args = ngx.req.get_uri_args()
        local id = uri_args["id"]
        local db = mysql:new()
        db:set_timeout(1000)
        local props = {
            host = "172.16.26.128",
            port = 3306,
            database = "mall_content",
            user = "root",
            password = "root"
        }
        local res = db:connect(props)
        local select_sql = "select url,pic from content where status ='1' and category_id="..id.." order by sort_order"
        res = db:query(select_sql)
        db:close()
        local redis = require("resty.redis")
        local red = redis:new()
        red:set_timeout(2000)
        local ip ="172.16.26.128"
        local port = 6379
        red:connect(ip,port)
        red:set("content_"..id,cjson.encode(res))
        red:close()
        ngx.say("{flag:true}")


    touch /root/lua/read_content.lua
    vi /root/lua/read_content.lua
        ngx.header.content_type="application/json;charset=utf8"
        local uri_args = ngx.req.get_uri_args();
        local id = uri_args["id"];
        --获取本地缓存
        local cache_ngx = ngx.shared.dis_cache;
        --根据ID 获取本地缓存数据
        local contentCache = cache_ngx:get('content_cache_'..id);
        if contentCache == "" or contentCache == nil then
            local redis = require("resty.redis");
            local red = redis:new()
            red:set_timeout(2000)
            red:connect("172.16.26.128", 6379)
            local rescontent=red:get("content_"..id);
            if ngx.null == rescontent then
                local cjson = require("cjson");
                local mysql = require("resty.mysql");
                local db = mysql:new();
                db:set_timeout(2000)
                local props = {
                    host = "172.16.26.128",
                    port = 3306,
                    database = "mall_content",
                    user = "root",
                    password = "root"
                }
                local res = db:connect(props);
                local select_sql = "select url,pic from content where status ='1' and category_id="..id.." order by sort_order";
                res = db:query(select_sql);
                local responsejson = cjson.encode(res);
                red:set("content_"..id,responsejson);
                ngx.say(responsejson);
                db:close()
            else
                cache_ngx:set('content_cache_'..id, rescontent, 10*60);
                ngx.say(rescontent)
            end
            red:close()
        else
            ngx.say(contentCache)
        end

    vi /usr/local/openresty/nginx/conf/nginx.conf 添加头信息，和 location信息
        lua_shared_dict dis_cache 128m;
        server {
            listen       80;
            server_name  localhost;
            location /update_content {
                content_by_lua_file /root/lua/update_content.lua;
            }
            location /read_content {
                 content_by_lua_file /root/lua/read_content.lua;
            }
        }





