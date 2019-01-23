# ClickCount

## Requirements 

### Helm & Tiller

You need to have tiller configured and installed on your namespace
to do so you might need to run 

Configure the correct service account, role and rolebinding on your namespace

```console
$ kubectl create sa <service-account-name> --namespace  <namespace>
```

finally you have your service account configured you can run

```console
$ helm init --tiller-namespace <namespace> --service-account <service-account>
```

### Redis

```console
$ helm install --name redis-<env> stable/redis --tiller-namespace <namespace> --namespace <namespace> --set usePassword=false
```

NOTE: 
If you have persistant volumes claims already present in your namespace, you mignt need to upgrade the chart instead of installing it normally, so to do so you should use:

```console
$ helm upgrade --install redis-<env> stable/redis --tiller-namespace <namespace> --namespace <namespace> --set usePassword=false
```

## Installing the Chart

To install the chart with the release name `click-count-<env>`:

```console
$ helm install --name click-count-<env> . --tiller-namespace <namespace> --set redis.config.host=redis-<env>-[slave|master] --set redis.config.port=6379
```

## Configuration


|             Parameter             |              Description                 |               Default               |
|-----------------------------------|------------------------------------------|-------------------------------------|
| `replicaCount`                    | The number of replicas                   | `1`                                 |
| `image.pullPolicy`                | Container pull policy                    | `IfNotPresent`                      |
| `image.repository`                | Container image to use                   | `hamdikh/clickcount`                |
| `image.tag`                       | Container image tag to deploy            | `latest`                            |
| `service.type`                    | The service type to be generated ( LoadBalencer, NodePort, ClusterIp ..) | `LoadBalencer`       |
| `service.port`                    | The service port to be used              | `8080`                              |
| `ingress.enabled`                 | Enables Ingress                          | `true`                              |
| `ingress.annotations`             | Ingress annotations                      | `{}`                                |
| `ingress.labels`                  | Custom labels                            | `{}`                                |
| `ingress.host`                    | Ingress accepted hostname | `clickcount-hkhelil-paas-local.eastus.cloudapp.azure.com` |                                             
| `ingress.tls.host`          | Ingress TLS accepted hostname   | `clickcount-hkhelil-paas-local.eastus.cloudapp.azure.com` |
| `redis.config.host`               | Redis accepted hostname/service name     | `redis`                             |
| `redis.config.port`               | Redis accepted service port              | `6379`                              |
| `resources`                       | CPU/Memory resource requests/limits      | `{}`                                |
