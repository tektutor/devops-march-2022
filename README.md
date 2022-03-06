# DevOps Lab setup

## Installing Docker Community Edition in CentOS 7.7
```
sudo su -
yum install -y yum-utils
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install docker-ce docker-ce-cli containerd.io
```

#### Starting docker engine service
```
sudo systemctl enable docker
sudo systemctl start docker
sudo systemctl status docker
sudo usermod -aG docker $USER```
newgrp docker
```

#### Issuing docker commands as non-root user
```
docker images
```

The expected ouput is
<pre>
</pre>
