#!/bin/sh

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install kube-prometheus-stack -f k8s/prometheus_scrape_options.yml prometheus-community/kube-prometheus-stack
