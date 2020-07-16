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
    mkdir -p /home/data/mysql/conf && mkdir -p /home/data/mysql/data

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

    Visit local site(Verify the data exits the MySQL8 database):
        172.16.26.128/update_content?id=1
        172.16.26.128/read_content?id=1

5. Nginx Current Limit
    控制速率的方式之一就是采用漏桶算法。
        (1)漏桶算法实现控制速率限流
            漏桶(Leaky Bucket)算法思路很简单,水(请求)先进入到漏桶里,漏桶以一定的速度出水(接口有响应速率),当水流入速度过大会直接溢出(访问频率超过接口响应速率),然后就拒绝请求,可以看出漏桶算法能强行限制数据的传输速率.示意图如下:
        (2)nginx的配置
            vi /usr/local/openresty/nginx/conf/nginx.conf
                user  root root;
                worker_processes  1;
                events {
                    worker_connections  1024;
                }
                http {
                    include       mime.types;
                    default_type  application/octet-stream;
                    #cache
                    lua_shared_dict dis_cache 128m;
                    #限流设置
                    limit_req_zone $binary_remote_addr zone=contentRateLimit:10m rate=2r/s;
                    sendfile        on;
                    #tcp_nopush     on;
                    #keepalive_timeout  0;
                    keepalive_timeout  65;
                    #gzip  on;
                    server {
                        listen       80;
                        server_name  localhost;
                        location /update_content {
                            content_by_lua_file /root/lua/update_content.lua;
                        }
                        location /read_content {
                            #使用限流配置
                            limit_req zone=contentRateLimit;
                            content_by_lua_file /root/lua/read_content.lua;
                        }
                    }
                }
                配置说明：
                    binary_remote_addr 是一种key，表示基于 remote_addr(客户端IP) 来做限流，binary_ 的目的是压缩内存占用量。
                    zone：定义共享内存区来存储访问信息， contentRateLimit:10m 表示一个大小为10M，名字为contentRateLimit的内存区域。1M能存储16000 IP地址的访问信息，10M可以存储16W IP地址访问信息。
                    rate 用于设置最大访问速率，rate=10r/s 表示每秒最多处理10个请求。Nginx 实际上以毫秒为粒度来跟踪请求信息，因此 10r/s 实际上是限制：每100毫秒处理一个请求。这意味着，自上一个请求处理完后，若后续100毫秒内又有请求到达，将拒绝处理该请求.我们这里设置成2 方便测试。
            systemctl restart openresty
            Visit local site(Verify the data exits the MySQL8 database):
                172.16.26.128/read_content?id=1
        (3) Dealing with burst flow
            上面例子限制 2r/s，如果有时正常流量突然增大，超出的请求将被拒绝，无法处理突发流量，可以结合 **burst** 参数使用来解决该问题。
                server {
                    listen       80;
                    server_name  localhost;
                    location /update_content {
                        content_by_lua_file /root/lua/update_content.lua;
                    }
                    location /read_content {
                        limit_req zone=contentRateLimit burst=4;
                        content_by_lua_file /root/lua/read_content.lua;
                    }
                }
            burst 译为突发、爆发，表示在超过设定的处理速率后能额外处理的请求数,当 rate=10r/s 时，将1s拆成10份，即每100ms可处理1个请求。
            此处，**burst=4 **，若同时有4个请求到达，Nginx 会处理第一个请求，剩余3个请求将放入队列，然后每隔500ms从队列中获取一个请求进行处理。若请求数大于4，将拒绝处理多余的请求，直接返回503.
            不过，单独使用 burst 参数并不实用。假设 burst=50 ，rate依然为10r/s，排队中的50个请求虽然每100ms会处理一个，但第50个请求却需要等待 50 * 100ms即 5s，这么长的处理时间自然难以接受。
            因此，burst 往往结合 nodelay 一起使用。
                server {
                    listen       80;
                    server_name  localhost;
                    location /update_content {
                        content_by_lua_file /root/lua/update_content.lua;
                    }
                    location /read_content {
                        limit_req zone=contentRateLimit burst=4 nodelay;
                        content_by_lua_file /root/lua/read_content.lua;
                    }
                }
            如上表示：
            平均每秒允许不超过2个请求，突发不超过4个请求，并且处理突发4个请求的时候，没有延迟，等到完成之后，按照正常的速率处理。
            如上两种配置结合就达到了速率稳定，但突然流量也能正常处理的效果。完整配置代码如下：
                user  root root;
                worker_processes  1;
                events {
                    worker_connections  1024;
                }
                http {
                    include       mime.types;
                    default_type  application/octet-stream;
                    #cache
                    lua_shared_dict dis_cache 128m;
                    #限流设置
                    limit_req_zone $binary_remote_addr zone=contentRateLimit:10m rate=2r/s;
                    sendfile        on;
                    #tcp_nopush     on;
                    #keepalive_timeout  0;
                    keepalive_timeout  65;
                    #gzip  on;
                    server {
                        listen       80;
                        server_name  localhost;
                        location /update_content {
                            content_by_lua_file /root/lua/update_content.lua;
                        }
                        location /read_content {
                            limit_req zone=contentRateLimit burst=4 nodelay;
                            content_by_lua_file /root/lua/read_content.lua;
                        }
                    }
                }
            测试：
                在1秒钟之内可以刷新4次，正常处理。
                但是超过之后，连续刷新5次，抛出异常。
    控制并发量（连接数）
        ngx_http_limit_conn_module  提供了限制连接数的能力。主要是利用limit_conn_zone和limit_conn两个指令。
        利用连接数限制 某一个用户的ip连接的数量来控制流量。
        注意：并非所有连接都被计算在内 只有当服务器正在处理请求并且已经读取了整个请求头时，才会计算有效连接。此处忽略测试。
        配置语法：
            Syntax:	limit_conn zone number;
            Default: —;
            Context: http, server, location;
        (1)配置限制固定连接数
            http {
                include       mime.types;
                default_type  application/octet-stream;
                #cache
                lua_shared_dict dis_cache 128m;
                #限流设置
                limit_req_zone $binary_remote_addr zone=contentRateLimit:10m rate=2r/s;
                #根据IP地址来限制，存储内存大小10M
                limit_conn_zone $binary_remote_addr zone=addr:1m;
                sendfile        on;
                #tcp_nopush     on;
                #keepalive_timeout  0;
                keepalive_timeout  65;
                #gzip  on;
                server {
                    listen       80;
                    server_name  localhost;
                    #所有以brand开始的请求，访问本地changgou-service-goods微服务
                    location /brand {
                        limit_conn addr 2;
                        proxy_pass http://192.168.0.103:18081;
                    }
                    location /update_content {
                        content_by_lua_file /root/lua/update_content.lua;
                    }
                    location /read_content {
                        limit_req zone=contentRateLimit burst=4 nodelay;
                        content_by_lua_file /root/lua/read_content.lua;
                    }
                }
            }
            配置说明：
                limit_conn_zone $binary_remote_addr zone=addr:10m;  表示限制根据用户的IP地址来显示，设置存储地址为的内存大小10M
                limit_conn addr 2;   表示 同一个地址只允许连接2次。
            测试：
                此时开3个线程，测试的时候会发生异常，开2个就不会有异常
        (2)限制每个客户端IP与服务器的连接数，同时限制与虚拟服务器的连接总数。脚本实际输入不能出现中文(了解)
            limit_conn_zone $binary_remote_addr zone=perip:10m;
            limit_conn_zone $server_name zone=perserver:10m;
            server {
                listen       80;
                server_name  localhost;
                charset utf-8;
                location / {
                    limit_conn perip 10;#单个客户端ip与服务器的连接数．
                    limit_conn perserver 100; ＃限制与服务器的总连接数
                    root   html;
                    index  index.html index.htm;
                }
            }





