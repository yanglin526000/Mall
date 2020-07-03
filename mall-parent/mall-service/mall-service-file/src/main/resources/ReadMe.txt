The installation steps of FastDFS：
1.Pull image
    docker pull morunchang/fastdfs(if not installed)
2.Run tracker
    docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh
3.Run storage
    docker run -d --name storage --net=host -e TRACKER_IP=172.16.26.128:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh
4.Open port number
    firewall-cmd --zone=public --add-port 21000/tcp --permanent;
    firewall-cmd --zone=public --add-port 22000/tcp --permanent;
    firewall-cmd --zone=public --add-port 22122/tcp --permanent;
    firewall-cmd --zone=public --add-port 23000/tcp --permanent;
    firewall-cmd --reload;

使用的网络模式是–net=host, TRACKER_IP=192.168.0.104是宿主机的IP,
group1是组名，即storage的组
如果想要增加新的storage服务器，再次运行该命令，注意更换 新组名

Configure Nginx
1.Nginx在这里主要提供对FastDFS图片访问的支持，Docker容器中已经集成了Nginx，我们需要修改nginx的配置,进入storage的容器内部，修改nginx.conf
    docker exec -it storage  /bin/bash
2.进入后
    vi /etc/nginx/conf/nginx.conf
    添加以下内容
    location ~ /M00 {
         root /data/fast_data/data;
         ngx_fastdfs_module;
    }
3.禁止缓存
    add_header Cache-Control no-store;
4.退出容器
    exit
5.重启storage容器
    docker restart storage
6.查看启动容器docker ps
    9f2391f73d97 morunchang/fastdfs "sh storage.sh" 12 minutes ago Up 12 seconds storage
    e22a3c7f95ea morunchang/fastdfs "sh tracker.sh" 13 minutes ago Up 13 minutes tracker
7.开启启动设置
    docker update --restart=always tracker
    docker update --restart=always storage





