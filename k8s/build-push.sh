#!/bin/sh

cd k8sSpringBootApp
./mvnw clean package
cd ..
docker build --tag localhost:5000/k8s-springboot-app:latest k8sSpringBootApp
docker push localhost:5000/k8s-springboot-app:latest

