1.Install Lua in Linux:
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

2.Install OpenResty in Linux:
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
    Visit local site:
        http://172.16.26.128/
    See current OpenResty version:
        /usr/local/openresty/bin/openresty -V
    Install cmd tool for openresty named resty:
        yum install -y openresty-resty

