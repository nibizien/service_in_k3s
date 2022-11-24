# My initial config

```bash
uname -a
>> Linux nico 5.15.0-52-generic #58~20.04.1-Ubuntu SMP Thu Oct 13 13:09:46 UTC 2022 x86_64 x86_64 x86_64 GNU/Linux

```

# Install docker

https://docs.docker.com/engine/install/ubuntu/

## Build docker image

Register to Docker Hub

```bash
docker login nibizien
```
Run build and push to registry

```bash
mvn clean clean install dockerfile:build
mvn clean install dockerfile:tag
mvn clean install dockerfile:push
mvn clean install dockerfile:tag@tag-latest
```

Check image generated

```bash
docker images
```
Check docker Hub

https://hub.docker.com/repository/docker/nibizien/docker_pod_info


## Run docker container

```bash
docker run -p 9080:9080 -t nibizien/docker_pod_info  --name docker_pod_info
```
Check with url

http://localhost:9080/getpod

# Install k3s

https://docs.k3s.io/installation

## Docker commandes

```bash
docker inspect $(docker ps | grep -i docker_pod_info | cut -d " " -f1) | grep -i IPAddress
docker inspect $(docker ps | grep -i docker_pod_info | cut -d " " -f1) | grep -i hostname
```

# Deploy on k3s

```bash
kubectl apply -f ./k8s/
```

And get ip of service
```bash
kubectl get svc | grep -i svc-getpod
>> svc-getpod   ClusterIP   10.43.93.137   <none>        9080/TCP   60s
```

Run url : http://10.43.93.137:9080/getpod

Check pods are created if deleted

```bash
kubectl delete po --all
```


## Kubernetes commandes

kubectl delete all --all
kubectl get all
kubectl get pods -o wide
kubectl get deployment deploy_name -o yaml > result_deploy.yaml


# install helm
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh

# Deploy helm chart

```bash
helm create demo
helm template demo
helm lint demo
helm install demo --debug --dry-run ./helm
helm install demo ./helm
helm list -a
```

Change replicaset in git and commit

```bash
helm upgrade demo .
helm rollback demo 1
helm delete demo
```

# install argocd

```bash
helm repo add argo-cd https://argoproj.github.io/argo-helm
helm dep update argocd/argocd/
```

```bash
helm install argo-cd argocd/argocd
kubectl apply -f argocd/argocd/manifests/argocd-nodeport.yaml
```

# Log to argocd
Check
localhost:30007

Log as admin

To get password
```bash
kubectl get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
```

log with cli
```bash
argocd version
>>argocd: v2.5.2+148d8da
  BuildDate: 2022-11-07T17:06:04Z
  GitCommit: 148d8da7a996f6c9f4d102fdd8e688c2ff3fd8c7
  GitTreeState: clean
  GoVersion: go1.18.7
  Compiler: gc
  Platform: linux/amd64
```

```bash
argocd login localhost:30007
>>'admin:login' logged in successfully
Context 'localhost:30007' updated
```

```bash
argocd version
>>argocd: v2.5.2+148d8da
  BuildDate: 2022-11-07T17:06:04Z
  GitCommit: 148d8da7a996f6c9f4d102fdd8e688c2ff3fd8c7
  GitTreeState: clean
  GoVersion: go1.18.7
  Compiler: gc
  Platform: linux/amd64
argocd-server: v2.3.2+ecc2af9
  BuildDate: 2022-03-23T00:40:57Z
  GitCommit: ecc2af9dcaa12975e654cde8cbbeaffbb315f75c
  GitTreeState: clean
  GoVersion: go1.17.6
  Compiler: gc
  Platform: linux/amd64
  Kustomize Version: v4.4.1 2021-11-11T23:36:27Z
  Helm Version: v3.8.0+gd141386
  Kubectl Version: v0.23.1
  Jsonnet Version: v0.18.0
```


# Create app argocd

With cli 

argocd app create demo --repo https://github.com/nibizien/servive_in_k3s.git --path argocd/apps --dest-namespace default --dest-server https://kubernetes.default.svc
