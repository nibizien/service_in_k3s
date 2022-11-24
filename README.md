# servive_in_k3s
Simple demo SpringBoot deployed in k3s with helm and argocd

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
