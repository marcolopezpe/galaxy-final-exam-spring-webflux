#!/bin/bash

echo '###########################################################'
echo 'Creacion de la red para todos los contenedores "licenciapp"'
echo '###########################################################'

docker network create --driver=bridge --subnet=172.20.0.1/16 licenciapp-net

echo '###########################################################'
echo 'Creacion del stack de contenedores'
echo '###########################################################'

docker-compose -f 01-mongo-audit.yml \
				-p licenciapp \
				up -d

echo '###########################################################'
echo 'Open mongo-express on "http://localhost:8081/"'
echo '###########################################################'