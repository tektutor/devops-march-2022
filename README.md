# DevOps Lab setup

## What is the reason to choose CentOS 7.7 while CentOS 8.x is available
As CentOS 8.x has reached End of Life by 31st Dec 2021,  RedHat stopped software updates from 31st Jan 2022. Hence, I would suggest you to use CentOS 7.x as it will reach its End of Life only by 30th June 2024.

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
sudo usermod -aG docker $USER
newgrp docker
```

#### Issuing docker commands as non-root user
```
docker images
```

The expected ouput is
<pre>

</pre>
