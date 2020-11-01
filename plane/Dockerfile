FROM      openjdk:11
MAINTAINER VSaldanya <vsaldanya@gmail.com>

WORKDIR /app

COPY ./target/plane.jar .
COPY src/main/resources/application*.properties ./

EXPOSE 8081

CMD java -Duser.timezone=UTC -Dserver.port=8081 -Dspring.config.location=file:/app/ -Dapp.home.dir=/app -Dfile.encoding=UTF-8 -Xms64m -Xmx512m -server -jar /app/plane.jar
