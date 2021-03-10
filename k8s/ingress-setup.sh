#!/bin/sh

kubectl apply -f k8s/06-ingress.yml
kubectl --namespace desarrollo annotate ingress 06-ingress kubernetes.io/ingress.class=ambassador
