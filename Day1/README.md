# Docker Overview

## Boot Loaders
   - Boot loader system utility gets installed in MBR(Master Boot Record) of your Hard Disk
   - Whenever the system is booted, CPU kind of initiates BIOS and BIOS after system self-test
     it instructs the CPU to run the Boot Loader
   - Boot Loader then scans your hard disk looking for any OS
   - In case Boot Loader detects more than one OS installed on your system it then give a menu
     for you to choose which OS you wish to boot into
   GRUB
    version 1
    version 2
   LILO (Linux Loaders)

## Virtualization
  - Hypervisor
  - Hypervisor is a general term that refers to Virtualization software
  - with this technology 
      - you can boot many OS side by side on the same PC/Desktop/Workstation/Server
      - is a combination of Hardware + Software
  - Processors that supports Virtualization feature 
      Intel
        - VT-X
      AMD
        - AMD-V
  
  - Heavy weight
      - Virtual Machines or the Guest OS they require dedicated hardware resources
           - Dedicated CPU cores
           - Dedicated RAM
           - Dedicated Storage
  
  - If you have a Processor that support Virtualization, you need to ensure that the virtualization
    feature is enabled in the BIOS.

  - How many Virtual Machine(Guest OS)
      - is limited by the Hardware resources available in the system
      - Primary factor is Processor( no of cpu cores in the Processor )
      - Amount of Random Access Memory available
      - Storage ( Hard Disk capacity )
     
  - Some vendor spectific virtualization softwares
      VMWare
        - VMWare workstation Pro
        - VMWare Fusion ( Mac )
        - VMWare vSphere (Bare metal Hypervisor)
      Microsoft
        - Hyper-v ( Comes with Windows 10 Pro or later out of the box )
      Oracle
        - VirtualBox (Free)

      Parallels ( Mac )
      KVM
       - opensource and free
       - Kernel Virtual Manager


Before the Virtualization technology came into the Industry
  - Let's say your organization needs 10000 webserver running in its own OS
  - How many physical servers are required to setup 10000 web servers?


## Containerization
 - light weight virtualization technology
    - containers doesn't require dedicated hardwares resources unlike Virtualization technology
 - containers are application process
 - application virtualization
 - all the containers are normal application processess that share the same Host OS Kernel
 - containers are not Operating System
 - containers in many ways they appear like an OS/VM
 - containers has their shell file system
 - containers are allocated with IP address
 - containers has its own network stack ( 7 OSI Layers )
 - containers has its own NIC ( Network Interface Card - Software Defined Network )
 - is a Linux Technology
 - Linux Kernel supports
     1. Namespace - is used to separate or isolate containers from each other by letting them run it own namespace
     2. CGroups ( Control Groups )
         - resource quota allocation
         - i.e it help in putting some restriction on how much CPU resources, RAM and Storage a particular container can use
         - this is required to ensure that one single container does'nt take up all the H/W resources leaving other containers
           starve for H/W resources

## Checking the version of docker installed
```
docker --version
```
The expected output is
<pre>
(jegan@tektutor.org$)><b>docker --version</b>
Docker version 20.10.12, build e91ed57
</pre>

## Checking the status of Docker Enginer (Daemon/Service)
```
sudo systemctl status docker
```
The expected output is
<pre>
jegan@tektutor.org$)><b>sudo sytemctl status docker</b>
sudo: sytemctl: command not found
(jegan@tektutor.org$)>sudo systemctl status docker
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)
   Active: active (running) since Sun 2022-03-06 19:23:50 PST; 3h 42min ago
     Docs: https://docs.docker.com
 Main PID: 64145 (dockerd)
    Tasks: 14
   Memory: 40.5M
   CGroup: /system.slice/docker.service
           └─64145 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock

Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.110984142-08:00" level=i...rpc
Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.111009882-08:00" level=i...rpc
Mar 06 19:23:48 tektutor.org dockerd[64145]: time="2022-03-06T19:23:48.477701245-08:00" level=i...t."
Mar 06 19:23:49 tektutor.org dockerd[64145]: time="2022-03-06T19:23:49.915307132-08:00" level=i...ss"
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.201200737-08:00" level=i...ng"
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.462895927-08:00" level=i...e."
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.515173237-08:00" level=i....12
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.515686639-08:00" level=i...on"
Mar 06 19:23:50 tektutor.org systemd[1]: Started Docker Application Container Engine.
Mar 06 19:23:50 tektutor.org dockerd[64145]: time="2022-03-06T19:23:50.571898030-08:00" level=i...ck"
Hint: Some lines were ellipsized, use -l to show in full.
</pre>

### Downloading hello-world docker image from Docker Hub(Remote Registry) to Local Docker Registry
```
docker pull hello-world:latest
```

The expected ouput is
<pre>
jegan@tektutor:~$ <b>docker pull hello-world:latest</b>
latest: Pulling from library/hello-world
2db29710123e: Pull complete 
Digest: sha256:97a379f4f88575512824f3b352bc03cd75e239179eea0fecc38e597b2209f49a
Status: Downloaded newer image for hello-world:latest
docker.io/library/hello-world:latest
</pre>

### Listing docker images from your local docker registry
```
docker images
```

The expected output is
<pre>
jegan@tektutor:~$ <b>docker images</b>
REPOSITORY                                TAG       IMAGE ID       CREATED        SIZE
docker.bintray.io/jfrog/artifactory-oss   latest    547c6957fc54   4 weeks ago    993MB
sonarqube                                 latest    4ac4842c584e   5 weeks ago    520MB
<b>hello-world                               latest    feb5d9fea6a5   5 months ago   13.3kB</b>
</pre>

## Finding more details about your docker installation
```
docker infor
```

The expected output is
<pre>
jegan@tektutor:~$ <b>docker info</b>
Client:
 Context:    default
 Debug Mode: false

Server:
 Containers: 2
  Running: 2
  Paused: 0
  Stopped: 0
 Images: 3
 Server Version: 20.10.7
 Storage Driver: overlay2
  Backing Filesystem: extfs
  Supports d_type: true
  Native Overlay Diff: true
  userxattr: false
 Logging Driver: json-file
 Cgroup Driver: cgroupfs
 Cgroup Version: 1
 Plugins:
  Volume: local
  Network: bridge host ipvlan macvlan null overlay
  Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
 Swarm: inactive
 Runtimes: io.containerd.runc.v2 io.containerd.runtime.v1.linux runc
 Default Runtime: runc
 Init Binary: docker-init
 containerd version: 
 runc version: 
 init version: 
 Security Options:
  apparmor
  seccomp
   Profile: default
 Kernel Version: 5.4.0-100-generic
 Operating System: Ubuntu 18.04.6 LTS
 OSType: linux
 Architecture: x86_64
 CPUs: 48
 Total Memory: 125.6GiB
 Name: tektutor
 ID: I3XQ:RESC:AIUK:6RVT:T34U:3CP5:GHLH:QWET:J52D:UV3O:G5B6:3WGE
 Docker Root Dir: /var/lib/docker
 Debug Mode: false
 Registry: https://index.docker.io/v1/
 Labels:
 Experimental: false
 Insecure Registries:
  127.0.0.0/8
 Live Restore Enabled: false

WARNING: No swap limit support
</pre>
