FROM bellsoft/liberica-openjdk-debian:17

RUN apt-get update && apt-get install -y bash

COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

WORKDIR /app

COPY target/EnergOs-0.0.1-SNAPSHOT.jar /app/EnergOs-0.0.1-SNAPSHOT.jar

CMD  [ "wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "EnergOs-0.0.1-SNAPSHOT.jar" ]

EXPOSE 8080
