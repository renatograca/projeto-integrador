FROM openjdk

WORKDIR /app

COPY ./target/projeto-integrador-0.0.1-SNAPSHOT.jar /app/projeto-integrador.jar

ENTRYPOINT ["java", "-jar", "projeto-integrador.jar"]