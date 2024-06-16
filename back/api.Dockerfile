FROM maven:latest as build_api

COPY ./ /api/

WORKDIR /api/

RUN mvn clean install package -DskipTests

FROM maven:latest as api

COPY --from=build_api ./api/target/smart-home-controller-0.0.1-SNAPSHOT.jar ./

CMD java -jar smart-home-controller-0.0.1-SNAPSHOT.jar
