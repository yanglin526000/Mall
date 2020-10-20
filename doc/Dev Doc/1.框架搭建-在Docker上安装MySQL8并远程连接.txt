Install MySQL8 in Docker
1.下载Mysql的Docker镜像
    docker search mysql
    docker pull mysql:8.0.20(当时搜索的最新版本)
2.运行镜像，设置root账号初始密码（123456），映射本地宿主机端口3306到Docker端口3306。测试过程没有挂载本地数据盘：
    docker run -p 3307:3306 --name MYSQL8.0.20 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.20
    解释下这条命令的参数：
        -p 3306:3306：将容器内的3306端口映射到实体机3306端口
        --name MYSQL8.0.20：给这个容器取一个容器记住的名字
        -e MYSQL_ROOT_PASSWORD=123456：docker的MySQL默认的root密码是随机的，这是改一下默认的root用户密码
        -d mysql:8.0.20：在后台运行mysql:8.0.20镜像产生的容器
3.新装了mysql8.0后再用navicat链接就会报2059的错误。上网查了发现是8.0之后mysql更改了密码的加密规则，只要在命令窗口把加密方法改回去即可。
    首先使用以下命令进入MySQL的docker容器
        docker exec -it MYSQL8.0.20 bash
    然后登录MySQL
        mysql -uroot -proot
    然后运行以下SQL即可
        GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
        alter user 'root'@'%' identified by 'root' password expire never;
        alter user 'root'@'%' identified with mysql_native_password by 'root';
        flush privileges;
4.退出MySQL，退出容器
    退出MySQL
    quit
    退出容器
    exit
5.重启MYSQL8.0.20
    docker restart MYSQL8.0.20



