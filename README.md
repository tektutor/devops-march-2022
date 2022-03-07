# DevOps Lab setup

## What is the reason to choose CentOS 7.7 while CentOS 8.x is available
As CentOS 8.x has reached End of Life by 31st Dec 2021,  RedHat stopped software updates from 31st Jan 2022. Hence, I would suggest you to use CentOS 7.x as it will reach its End of Life only by 30th June 2024.

## Installing Docker Community Edition in CentOS 7.7
```
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io
```

The expected output is
<pre>
(jegan@tektutor.org$)><b>sudo yum install -y yum-utils</b>
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: centos.excellmedia.net
 * epel: mirror.01link.hk
 * extras: centos.excellmedia.net
 * updates: centos.excellmedia.net
Package yum-utils-1.1.31-54.el7_8.noarch already installed and latest version
Nothing to do

(jegan@tektutor.org$)><b>sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo</b>
Loaded plugins: fastestmirror, langpacks
adding repo from: https://download.docker.com/linux/centos/docker-ce.repo
grabbing file https://download.docker.com/linux/centos/docker-ce.repo to /etc/yum.repos.d/docker-ce.repo
repo saved to /etc/yum.repos.d/docker-ce.repo

(jegan@tektutor.org$)><b>sudo yum install docker-ce docker-ce-cli containerd.io</b>
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: centos.excellmedia.net
 * epel: epel.mirror.angkasa.id
 * extras: centos.excellmedia.net
 * updates: centos.excellmedia.net
Resolving Dependencies
--> Running transaction check
---> Package containerd.io.x86_64 0:1.4.13-3.1.el7 will be installed
--> Processing Dependency: container-selinux >= 2:2.74 for package: containerd.io-1.4.13-3.1.el7.x86_64
---> Package docker-ce.x86_64 3:20.10.12-3.el7 will be installed
--> Processing Dependency: docker-ce-rootless-extras for package: 3:docker-ce-20.10.12-3.el7.x86_64
---> Package docker-ce-cli.x86_64 1:20.10.12-3.el7 will be installed
--> Processing Dependency: docker-scan-plugin(x86-64) for package: 1:docker-ce-cli-20.10.12-3.el7.x86_64
--> Running transaction check
---> Package container-selinux.noarch 2:2.119.2-1.911c772.el7_8 will be installed
---> Package docker-ce-rootless-extras.x86_64 0:20.10.12-3.el7 will be installed
--> Processing Dependency: fuse-overlayfs >= 0.7 for package: docker-ce-rootless-extras-20.10.12-3.el7.x86_64
--> Processing Dependency: slirp4netns >= 0.4 for package: docker-ce-rootless-extras-20.10.12-3.el7.x86_64
---> Package docker-scan-plugin.x86_64 0:0.12.0-3.el7 will be installed
--> Running transaction check
---> Package fuse-overlayfs.x86_64 0:0.7.2-6.el7_8 will be installed
--> Processing Dependency: libfuse3.so.3(FUSE_3.2)(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
--> Processing Dependency: libfuse3.so.3(FUSE_3.0)(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
--> Processing Dependency: libfuse3.so.3()(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
---> Package slirp4netns.x86_64 0:0.4.3-4.el7_8 will be installed
--> Running transaction check
---> Package fuse3-libs.x86_64 0:3.6.1-4.el7 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

============================================================================================================================================
 Package                                 Arch                 Version                                  Repository                      Size
============================================================================================================================================
Installing:
 containerd.io                           x86_64               1.4.13-3.1.el7                           docker-ce-stable                28 M
 docker-ce                               x86_64               3:20.10.12-3.el7                         docker-ce-stable                23 M
 docker-ce-cli                           x86_64               1:20.10.12-3.el7                         docker-ce-stable                30 M
Installing for dependencies:
 container-selinux                       noarch               2:2.119.2-1.911c772.el7_8                extras                          40 k
 docker-ce-rootless-extras               x86_64               20.10.12-3.el7                           docker-ce-stable               8.0 M
 docker-scan-plugin                      x86_64               0.12.0-3.el7                             docker-ce-stable               3.7 M
 fuse-overlayfs                          x86_64               0.7.2-6.el7_8                            extras                          54 k
 fuse3-libs                              x86_64               3.6.1-4.el7                              extras                          82 k
 slirp4netns                             x86_64               0.4.3-4.el7_8                            extras                          81 k

Transaction Summary
============================================================================================================================================
Install  3 Packages (+6 Dependent packages)

Total download size: 94 M
Installed size: 381 M
Is this ok [y/d/N]: y
Downloading packages:
(1/9): container-selinux-2.119.2-1.911c772.el7_8.noarch.rpm                                                          |  40 kB  00:00:00     
warning: /var/cache/yum/x86_64/7/docker-ce-stable/packages/containerd.io-1.4.13-3.1.el7.x86_64.rpm: Header V4 RSA/SHA512 Signature, key ID 621e9f35: NOKEY
Public key for containerd.io-1.4.13-3.1.el7.x86_64.rpm is not installed
(2/9): containerd.io-1.4.13-3.1.el7.x86_64.rpm                                                                       |  28 MB  00:00:01     
(3/9): docker-ce-cli-20.10.12-3.el7.x86_64.rpm                                                                       |  30 MB  00:00:01     
(4/9): docker-ce-rootless-extras-20.10.12-3.el7.x86_64.rpm                                                           | 8.0 MB  00:00:00     
(5/9): docker-ce-20.10.12-3.el7.x86_64.rpm                                                                           |  23 MB  00:00:03     
(6/9): fuse-overlayfs-0.7.2-6.el7_8.x86_64.rpm                                                                       |  54 kB  00:00:00     
(7/9): slirp4netns-0.4.3-4.el7_8.x86_64.rpm                                                                          |  81 kB  00:00:00     
(8/9): docker-scan-plugin-0.12.0-3.el7.x86_64.rpm                                                                    | 3.7 MB  00:00:00     
(9/9): fuse3-libs-3.6.1-4.el7.x86_64.rpm                                                                             |  82 kB  00:00:00     
--------------------------------------------------------------------------------------------------------------------------------------------
Total                                                                                                        22 MB/s |  94 MB  00:00:04     
Retrieving key from https://download.docker.com/linux/centos/gpg
Importing GPG key 0x621E9F35:
 Userid     : "Docker Release (CE rpm) <docker@docker.com>"
 Fingerprint: 060a 61c5 1b55 8a7f 742b 77aa c52f eb6b 621e 9f35
 From       : https://download.docker.com/linux/centos/gpg
Is this ok [y/N]: y
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Installing : docker-scan-plugin-0.12.0-3.el7.x86_64                                                                                   1/9 
  Installing : 1:docker-ce-cli-20.10.12-3.el7.x86_64                                                                                    2/9 
  Installing : 2:container-selinux-2.119.2-1.911c772.el7_8.noarch                                                                       3/9 
  Installing : containerd.io-1.4.13-3.1.el7.x86_64                                                                                      4/9 
  Installing : slirp4netns-0.4.3-4.el7_8.x86_64                                                                                         5/9 
  Installing : fuse3-libs-3.6.1-4.el7.x86_64                                                                                            6/9 
  Installing : fuse-overlayfs-0.7.2-6.el7_8.x86_64                                                                                      7/9 
  Installing : docker-ce-rootless-extras-20.10.12-3.el7.x86_64                                                                          8/9 
  Installing : 3:docker-ce-20.10.12-3.el7.x86_64                                                                                        9/9 
  Verifying  : containerd.io-1.4.13-3.1.el7.x86_64                                                                                      1/9 
  Verifying  : fuse3-libs-3.6.1-4.el7.x86_64                                                                                            2/9 
  Verifying  : 1:docker-ce-cli-20.10.12-3.el7.x86_64                                                                                    3/9 
  Verifying  : fuse-overlayfs-0.7.2-6.el7_8.x86_64                                                                                      4/9 
  Verifying  : docker-scan-plugin-0.12.0-3.el7.x86_64                                                                                   5/9 
  Verifying  : slirp4netns-0.4.3-4.el7_8.x86_64                                                                                         6/9 
  Verifying  : 2:container-selinux-2.119.2-1.911c772.el7_8.noarch                                                                       7/9 
  Verifying  : docker-ce-rootless-extras-20.10.12-3.el7.x86_64                                                                          8/9 
  Verifying  : 3:docker-ce-20.10.12-3.el7.x86_64                                                                                        9/9 

Installed:
  containerd.io.x86_64 0:1.4.13-3.1.el7          docker-ce.x86_64 3:20.10.12-3.el7          docker-ce-cli.x86_64 1:20.10.12-3.el7         

Dependency Installed:
  container-selinux.noarch 2:2.119.2-1.911c772.el7_8                    docker-ce-rootless-extras.x86_64 0:20.10.12-3.el7                   
  docker-scan-plugin.x86_64 0:0.12.0-3.el7                              fuse-overlayfs.x86_64 0:0.7.2-6.el7_8                               
  fuse3-libs.x86_64 0:3.6.1-4.el7                                       slirp4netns.x86_64 0:0.4.3-4.el7_8                                  

Complete!
</pre>


#### Starting docker engine service
```
sudo systemctl enable docker
sudo systemctl start docker
sudo systemctl status docker
sudo usermod -aG docker $USER
newgrp docker
```
The expected ouput is
<pre>
(jegan@tektutor.org$)><b>sudo systemctl enable docker</b>
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
(jegan@tektutor.org$)><b>sudo systemctl start docker</b>
(jegan@tektutor.org$)><b>sudo systemctl status docker</b>
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)
   Active: active (running) since Sun 2022-03-06 19:23:50 PST; 9min ago
     Docs: https://docs.docker.com
 Main PID: 64145 (dockerd)
   CGroup: /system.slice/docker.service
           └─64145 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock

Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.110984142-08:00" level=info msg="ccResolverWrapper: sending...le=grpc
Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.111009882-08:00" level=info msg="ClientConn switching balan...le=grpc
Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.477701245-08:00" level=info msg="Loading containers: start."
Mar 06 19:23:49 tektutor.org dockerd[64145]: time="2022-03-06T19:23:49.915307132-08:00" level=info msg="Default bridge (docker0) i...ddress"
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.201200737-08:00" level=info msg="Firewalld: interface docke...urning"
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.462895927-08:00" level=info msg="Loading containers: done."
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.515173237-08:00" level=info msg="Docker daemon" commit=459d...0.10.12
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.515686639-08:00" level=info msg="Daemon has completed initialization"
Mar 06 19:23:50 tektutor.org systemd[1]: Started Docker Application Container Engine.
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.571898030-08:00" level=info msg="API listen on /var/run/docker.sock"
Hint: Some lines were ellipsized, use -l to show in full.
(jegan@tektutor.org$)><b>newgrp docker</b>
</pre>


#### Issuing docker commands as non-root user
```
docker images
```

The expected ouput is
<pre>
(jegan@tektutor.org$)>docker images
REPOSITORY   TAG       IMAGE ID   CREATED   SIZE
(jegan@tektutor.org$)>
</pre>
