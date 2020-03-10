#!/bin/bash
CONTAINER_NAME=mariadb-runescape
docker rm -f $CONTAINER_NAME
docker volume create "${CONTAINER_NAME}-volume"
docker run -it \
  --restart=always \
  --name=$CONTAINER_NAME \
  -p 3306:3306 \
  -e MARIADB_DATABASE="runescape" \
  -e MARIADB_ROOT_PASSWORD=123 \
  -e MARIADB_USER=runescape \
  -e MARIADB_PASSWORD=123 \
  -v "${CONTAINER_NAME}-volume:/bitnami/mariadb" \
  bitnami/mariadb:latest
