#!/bin/bash

echo '###########################################################'
echo 'Eliminacion del stack de contenedores"'
echo '###########################################################'

docker-compose -f 01-mongo-audit.yml \
				-p licenciapp \
				down

echo '###############################################################'
echo 'Eliminacion de la red para todos los contenedos "licencium-net"'
echo '###############################################################'

# docker network rm licenciapp-net
