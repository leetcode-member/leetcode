echo '进入 leetcode 项目文件'
cd /docker/leetcode01/leetcode/leetcode
echo "pull代码"
git pull origin dev:dev
echo "打包编译"
mvn package -f pom.xml
echo "构建镜像"
docker build -t leetcode-app:`git rev-parse HEAD` .
echo "删除容器"
docker rm -f `docker ps -q -f name=leetcode-app`
echo "启动容器"
docker run -d -p 9001:8080 --name leetcode-app leetcode-app:`git rev-parse HEAD`
