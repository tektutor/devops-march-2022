## Setting up a 3 Node Kubernetes Cluster using kubeadm
#### Disable Virtual Memory (swap parition) in Master and Worker Nodes
```
sudo swapoff -a
```
To permanently disable swap partition,  edit  the /etc/fstab file root user and comment the swap partition.
```
sudo vim /etc/fstab
sudo systemctl daemon-reload
```

#### Disable SELINUX in Master and Worker Nodes
``` 
setenforce 0
```

To permanently disable  SELINUX, you need to edit /etc/selinux/config file and change enforcing to disabled.
```
sudo systemctl daemon-reload
```
Configure the hostnames of master and all worker nodes
In Master Node
```
sudo hostnamectl set-hostname master
```

In worker1 Node
```
sudo hostnamectl  set-hostname worker1
```

In worker2 Node
```
sudo hostnamectl set-hostnamme worker2
```

### In the master node, type the below command to find the IP Address
```
ifconfig ens33
```
Note down the IP of master node as we need to add this in the /etc/hosts later.

### In the worker1 node, type the below command to find the IP Address
```
ifconfig ens33
```
Note down the IP of worker1 node as we need to add this in the /etc/hosts later.

### In the worker2 node, type the below command to find the IP Address
```
ifconfig ens33
```
Note down the IP of worker2 node as we need to add this in the /etc/hosts later.

### Configure /etc/hosts file
Append the IPAddresses of master, worker1 and worker2 as shown below in /etc/hosts files. This should be done in master, worker1 and worker2 nodes.
```
192.168.254.129 master 
192.168.254.130 worker1
192.168.254.131 worker2
```
The actual IP addresses might vary in your system, hence you need to replace above IP with your master, worker1 and worker2 IP addresses accordingly.

### Firewall configurations
For summary of ports that must be opened, refer official Kubernetes documention https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

#### Open the below ports in Master Node as root user
```
sudo firewall-cmd --permanent --add-port=6443/tcp
sudo firewall-cmd --permanent --add-port=2379-2380/tcp
sudo firewall-cmd --permanent --add-port=10250-10252/tcp
sudo firewall-cmd --permanent --add-port=10255/tcp
sudo firewall-cmd --permanent --add-port=30000-32767/tcp
sudo firewall-cmd --permanent --add-masquerade
sudo firewall-cmd --permanent --zone=trusted  --add-source=192.168.0.0/16 
sudo modprobe br_netfilter
sudo systemctl daemon-reload
sudo systemctl restart firewalld
sudo systemctl status firewalld
sudo firewall-cmd --list-all
```

#### Open the below ports in Worker Nodes as root user
```
sudo firewall-cmd --permanent --add-port=10250/tcp
sudo firewall-cmd --permanent --add-port=30000-32767/tcp
sudo firewall-cmd --permanent --add-masquerade
sudo firewall-cmd --permanent --zone=trusted  --add-source=192.168.0.0/16 
sudo modprobe br_netfilter
sudo systemctl daemon-reload
sudo systemctl restart firewalld
sudo systemctl status firewalld
sudo firewall-cmd --list-all
```

#### Install Docker CE in Master and Worker Nodes
```
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install -y docker-ce
sudo usermod -aG docker $USER
sudo su $USER
```

### Configure Docker Engine to use systemd driver in Master and Worker Nodes

Before editing the daemon.json, let's enable and start docker so that it will create /etc/docker folder.
```
sudo systemctl enable docker
sudo systemctl start docker
```

sudo vim /etc/docker/daemon.json

```
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
     "max-size": "100m"
  },
  "storage-driver": "overlay2",
  "storage-opts": [
    "overlay2.override_kernel_check=true"
  ]
}
```

Apply the above config changes
```
sudo mkdir -p /etc/systemd/system/docker.service.d
sudo systemctl daemon-reload
sudo systemctl enable docker && sudo systemctl start docker
```

#### Configure IPTables to see bridge traffic in Master and Worker Nodes
```
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
br_netfilter
EOF

cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sudo sysctl --system
```

### Install kubectl kubeadm and kubelet on Master & Worker nodes
```
cat <<EOF | sudo tee /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-\$basearch
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
exclude=kubelet kubeadm kubectl
EOF

sudo yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes
```

### Configure kubelet in Master and Worker Nodes
```
sudo vim /etc/sysconfig/kubelet
KUBELET_EXTRA_ARGS=--runtime-cgroups=/systemd/system.slice --kubelet-cgroups=/systemd/system.slice

```
You may enable the kubelet service as shown below
```
sudo systemctl enable --now kubelet
```

### Restart Docker and Kubelet
```
sudo systemctl daemon-reload
sudo systemctl restart docker
sudo systemctl restart kubelet
```

### Bootstrapping Master Node as root user
```
kubeadm init --pod-network-cidr=192.168.0.0/16
```

Do the below steps as rps user
```
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

If you wish to run kubectl commands as non-admin user then you need to append the below line to $HOME/.bashrc file
```
export KUBECONFIG=~/.kube/config
```
In order to apply the export changes done in $HOME/.bashrc file you need to run the below command
```
source ~/.bashrc
```

In order to access the cluster without issues after machine reboots, add the below to /root/.bashrc
Do the below as root user
```
export KUBECONFIG=/etc/kubernetes/admin.conf
```
In order to apply the export changes done in the /root/.bashrc, you need to manually run this
```
source /root/.bashrc
```

Save your join token in a file on the Master Node, the token varies on every system and every time you type kubeadm init, hence you need to save your join token for your reference before you clear your terminal screen.
```
vim token
kubeadm join 192.168.154.128:6443 --token 5zt7tp.2txcmgnuzmxtgnl \
        --discovery-token-ca-cert-hash sha256:27758d146627cfd92079935cbaff04cb1948da37c78b2beb2fc8b15c2a5adba
```

#### In case you forgot to save your join token and cleared the terminal screen, no worries try this on Master Node
```
kubeadm token create --print-join-command
```

#### In Master Node
```
kubectl get nodes
kubectl get po -n kube-system -w
```
Press Ctrl+C to come out of watch mode.

#### Installing Calico CNI in Master Node
To learn more about Calico CNI, refer https://docs.projectcalico.org/getting-started/kubernetes/self-managed-onprem/onpremises 
```
curl https://docs.projectcalico.org/manifests/calico.yaml -O
kubectl apply -f calico.yaml
```

#### In Master Node watch the pod creation after installing Calico
```
kubectl get po -n kube-system -w
```
![Control Plane Pods](https://github.com/tektutor/kubernetes-may-2021/blob/master/Day2/3NodeClusterSetup/K8s-controlplane-pods.png)

Press Ctrl+C to come out of watch mode.

#### In Worker Node
```
kubeadm join 192.168.154.128:6443 --token 5zt7tp.2txcmgnuzmxtgnl \
        --discovery-token-ca-cert-hash sha256:27758d146627cfd92079935cbaff04cb1948da37c78b2beb2fc8b15c2a5adba
```
![Worker Join Command](https://github.com/tektutor/kubernetes-may-2021/blob/master/Day2/3NodeClusterSetup/worker1-join.png)

#### In Master Node
At this point,  you are supposed to see 3 nodes in ready state.
```
kubectl get nodes
```
![Node List](https://github.com/tektutor/kubernetes-may-2021/blob/master/Day2/3NodeClusterSetup/node-list.png)

If you see similar output on your system, your 3 node Kubernetes cluster is all set !!!


#### In case you had trouble setting up master, you could reset and try init as shown below (on master and worker nodes)
```
kubeadm reset
```

You need to manually remove the below folder
```
rm -rf /etc/cni/net.d
rm -rf /etc/kubernetes
rm -rf $HOME/.kube
```

## Kubernetes Overview
- Container Orchestration Platform
- platform that manages different types of containers
- it support LXC, CRI-O(Podman), etc.,
- gives an eco-system when you are applications can be deployed with K8s cluster and make it Highly Available
- has in-built controllers that constantly monitors the health of your application, if required it repairs your application
  by replacing unhealth Pod instance of your application with an healthy Pod instance
- self-healing platform
- it supports doing rolling update i.e helps in upgrading your appliction from one version to the other without any downtime
- it helps in scaling up/down the number of instances of your microservices/applications running within K8s cluster
- kubectl is the client tools that is used majority of the times to interact with the Kubernetes cluster
- it is thru kubectl 
    - you will deploy applications
    - you will scale up/down the number of instances running within K8s cluster
    - you will perform rolling update
    - you will exposed your deployment as services either for internal or external
    - this tool is generally used in master, but can also used in workers nodes optionally
   
 - kubeadm
     - is an administrative tool
     - used to bootstrap the master
     - is also used to join the worker nodes to the K8s cluster
     - is also used to unjoin a worker node from the K8s cluster
     - is also used to reset(uninstall) K8s cluster setup
     - is available on master as well as worker nodes
    
 - kubelet
     - is the Kubernetes Container Agent that runs on every node ( master and workers)
     - this is the daemon/service that actually interacts with the Container Runtime like Docker, CRI-O, etc.,
     - kubelet is the component that pulls the required container images from Docker Hub or any of your private registries
     - kubelet also constantly monitors the health of the Pods that are running in the worker node and keeps reporting
       the status of those Pods to the master node(API Server)
     - kubelet is the one which creates the Pods on the worker node
     - kubelet is the one which creates the Control Plane components 
     
 - Control Plane components ( Typically runs on master node )
     1. API Server
     3. Scheduler
     4. Controller Managers
     5. etc ( key/value datastore )

 - API Server
      - implements all the Kubernetes features as REST API
      - is the component that all other K8s components will interact with
      - no components are allowed to talk to each other directly. i.e all communication should happen via API Server only
      - API servers store the K8s cluster status into the etcd datastore
      - API Server is the only components which will access the etcd datastore directly
      
 - Scheduler
      - scheduler is the one which identies a healthy node where your application Pods can be deployed

  - Controller Managers
      - a collection of many Controllers that are responsible for High Availability of your applications
      - Node Controller
      - Endpoint Controller
      - Deployment Controller
      - ReplicaSet Controller
      - Replication Controller
      - monitors the health of cluster as well as the application Pods and take action in case any Pods are not healthy
  
 - etcd
     - key/value pair dictionary style database
     - third-party database developed as a separate opensource project which is used by Kubernetes


## Kubernetes Jargons

Pod
  - is a Kubernetes Object
  - a group of related containers
  - your applications runs inside a container within a Pod
  - this is the smallest unit that can be deployed in a K8s cluster

ReplicaSet
  - is a Kubernetes object
  - ReplicaSet in turn manages Pod(s)
  - ReplicaSet may have one to many Pods
  - this capture the number of Pods that needs to run at any point in time
  - support scale up/down

Deployment
  - applications are in general created as a Deployment
  - Deployment in turn creates ReplicaSet(s)
  - There can one to many ReplicaSets per Deployment
  - Each version of your application there will be one ReplicaSet
  - Also supports Rolling update
  - You can initiate the Scale up/down only via Deployment
  - The Deployment internally will communicate with ReplicaSet to scale up/down the number of Pods
  - this is generally used to deploy stateless applications

DaemonSet
  - If you want to collect let's say Performance metrics from each node, then instead creating a Deployment you can create
    a DaemonSet
  - DaemonSet ensures one Pod of your application runs per node
  - As more number of nodes are added to the K8s cluster, DaemonSet will automatically create one Pod of your application on the newly joined node
  - If a node is removed from the Cluster, the DaemonSet will automatically ensure the Pod that was created by the DaemonSet is disposed

Job
 - is meant for one time activity like taking backup once in a week

StatefulSet
 - This is used to deploy stateful applications

Service
 - this helps access Pods via a service abstraction without knowing the specific Pod details
 - as Pods come and go i.e created when scale up happens, destroyed when scale down happens
 - it is not a good idea to connect to Pods directory
 - the recommended practice to access a group of LoadBalanced Pods via a Service
 - Service is of 2 types
   1. Internal Service
       - ClusterIP Service ( Used for database )
       - accessible only within the K8s Cluster
   2. External Service
       - accessible outside the K8s Cluster
        eg:
       - NodePort Service
       - LoadBalancer Service

## ⛹️‍♂️ Lab - Creating your first Deployment in Kubernetes
```
kubectl create deploy nginx --image=nginx:1.18
```

The expected output is
<pre>
[jegan@master ~]$ <b>kubectl create deployment nginx --image=nginx:1.18</b>
deployment.apps/nginx created
</pre>

Now let's list the deployment that we created just now
```
kubectl get deployments
kubectl get deployment
kubectl get deploy
```
The expected output is
<pre>
[jegan@master ~]$ kubectl get deploy
NAME    READY   UP-TO-DATE   AVAILABLE   AGE
nginx   0/1     1            0           6s
</pre>

Now let's list the replicaset that was created as part of deployment
```
kubectl get replicasets
kubectl get replicaset
kubectl get rs
```

The expected output is
<pre>
[jegan@master ~]$ kubectl get rs
NAME               DESIRED   CURRENT   READY   AGE
nginx-6888c79454   1         1         0       15s
</pre>

Now let's list the Pods that were created as part of the deployment
```
kubectl get pods
kubectl get pod
kubectl get po
```

The expected ouput is
<pre>
[jegan@master ~]$ kubectl get po
NAME                     READY   STATUS              RESTARTS   AGE
nginx-6888c79454-2cpfx   0/1     ContainerCreating   0          18s
</pre>

You may also list many objects at one shot
```
kubectl get deploy,rs,po
```

The expected output is
<pre>
[jegan@master ~]$ kubectl get deploy,rs,po
NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/nginx   1/1     1            1           33s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/nginx-6888c79454   1         1         1       33s

NAME                         READY   STATUS    RESTARTS   AGE
pod/nginx-6888c79454-2cpfx   1/1     Running   0          33s
</pre>
