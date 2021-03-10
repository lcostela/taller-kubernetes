#!/bin/sh

kind delete cluster
container_id="$(docker ps -aqf "name=kind-registry")"
docker rm -f ${container_id}
