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

## Installing the Chart

To install the chart with the release name `click-count-<env>`:

```console
$ helm install --name click-count-<env> . --tiller-namespace <namespace> --set redis.config.url=http://redis-<env>-slave:6379 --set redis.config.secret.name=redis-<env>
```

## Configuration


|             Parameter             |              Description                 |               Default               |
|-----------------------------------|------------------------------------------|-------------------------------------|
| `imagePullSecret`                 | The name of the secret to use if pulling from a private registry | `nil`       |
| `image.pullPolicy`                | Container pull policy                    | `IfNotPresent`                      |
| `image.repository`                | Container image to use                   | `hamdikh/clickcount`                             |
| `image.tag`                       | Container image tag to deploy            | `xebia`                            |
