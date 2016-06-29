Run the demo
----------------
----------------
git clone https://github.com/ddc496601562/websocket-tail-web.git

cd websocket-tail-web/

vim src/main/resources/dev/log.tail.properties(alter configuration)



针对linux服务器
----------------
mvn install 打包

上传tar.gz 包，解压

cd  ${home_dir}

sh bin/start-tail.sh  




针对window + eclipse  
----------------
maven导入，JettyServerMain启动


访问url  
----------------
http://${hostName}:8013/index.jsp  test page, 不停的刷一句话

http://${hostName}:8013/tail.jsp?logFile=${file_path}  tail -f ${log.tail.file.root.dir}/${file_path} 文件，log.tail.file.root.dir是在配置文件中配置的



关于这个项目
----------------

参考git项目 https://github.com/davidmoten/websockets-log-tail

由于这个项目在服务端部署上有很多不方便，对于tomcat、jetty-web兼容的不是很好，在服务器端无法部署，所以重写了一下，提取出了一些变量




