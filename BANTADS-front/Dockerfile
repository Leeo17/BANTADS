FROM node:latest as build
WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN npm install --silent
RUN npm run build

FROM nginx:latest

COPY --from=build /usr/local/app/dist/bantads-front /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf