FROM      openjdk:11
MAINTAINER VSaldanya <vsaldanya@gmail.com>

WORKDIR /app

COPY ./target/plane-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/application*.properties ./

EXPOSE 8081

CMD java -Duser.timezone=UTC -Dspring.config.location=file:/app/ -Dapp.home.dir=/app -Dfile.encoding=UTF-8 -Xms64m -Xmx512m -XX:MaxPermSize=256m -server -jar /app/plane-0.0.1-SNAPSHOT.jar
