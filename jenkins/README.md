# Jenkins

To use Jenkins pipelines, you need first of all to have an instance deployed into your K8S cluster, to do so you can use this command:

```
helm install --name jenkins stable/jenkins --tiller-namespace=<namespace>
```

## Docker Build and deploy

The jenkinsfile [clickcount-Docker-Build.Jenkinsfile](./clickcount-Docker-Build.Jenkinsfile) is responsable for:

* Building the application
* Building the docker image
* Pushing the docker image to [the hub](https://cloud.docker.com/repository/docker/hamdikh/clickcount)

## Helm Deploy

The jenkinsfile [clickcount-helm-deploy.Jenkinsfile](./clickcount-helm-deploy.Jenkinsfile) is responsable for:

* Init the helm client to use 
* Deploy clickcount app to Staging environement
* Deploy clickcount app to Production environment

NOTE: this deployment won't consider dependencies, you need to use tge Helm full deployment Pipeline to deploy every part of the application

## Helm Full deploy

The jenkinsfile [clickcount-deploy-with-redis.Jenkinsfile](./clickcount-deploy-with-redis.Jenkinsfile) is responsable for:

* Init the helm client to use 
* Deploy redis ( master/slave ) to Staging environement
* Deploy clickcount app to Staging environement
* Deploy redis ( master/slave ) to Production environement
* Deploy clickcount app to Production environment

