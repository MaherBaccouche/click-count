# Azure 

## Create a K8S Cluster ( AKS )

```
az aks create -g kubernetes -n hkhelil-k8s --generate-ssh-keys
```

## Get Credentials

```
az aks get-credentials --resource-group kubernetes --ame hkhelil-k8s
```

## Test Kubernetes 

```
kubectl get no 
```

## Test po creation

```
kubectl run hello-world-app --image=gcr.io/google-samples/node-hello:1.0 --port=8080 
```

## Test svc 

```
kubectl expose deploy hello-world-app --type=LoadBalancer --name hello-service

kubectl get svc -w 

curl http://IP_ADR:8080
```
