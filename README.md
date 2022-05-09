# pingpang-platform

pingpang-platform: 10010
mysql: 3306

mysql部署命令:
    docker run -d -p3306:3306 --net pingpang-network -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql --name mysql -e MYSQL_ROOT_PASSWORD=l@0x1@n123 mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

打包：
    docker rmi -f weikaisen/pingpang-platform:1.0
    docker build -t weikaisen/pingpang-platform:1.0 .

推送：
    docker push weikaisen/pingpang-platform:1.0

部署：
    docker run -d --name pingpang-admin-1 -p 8080:10010 --net pingpang-network weikaisen/pingpang-platform:1.0

