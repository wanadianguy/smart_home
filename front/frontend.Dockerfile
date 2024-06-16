FROM node:latest

RUN npm install -g typescript

COPY . ./frontend

WORKDIR ./frontend

RUN npm run setup

CMD npm run start
