FROM mongo:latest

ENV MONGO_INITDB_ROOT_USERNAME=admin
ENV MONGO_INITDB_ROOT_PASSWORD=password

COPY ./init-database.js /docker-entrypoint-initdb.d/init-database.js

RUN chmod +r /docker-entrypoint-initdb.d/init-database.js
