## ⛹️‍♀️ Lab - Creating Kubernetes Dashboard
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml
```

The expected output is
<pre>
[jegan@master etcd-operator]$ <b>kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml</b>
namespace/kubernetes-dashboard created
serviceaccount/kubernetes-dashboard created
service/kubernetes-dashboard created
secret/kubernetes-dashboard-certs created
secret/kubernetes-dashboard-csrf created
secret/kubernetes-dashboard-key-holder created
configmap/kubernetes-dashboard-settings created
role.rbac.authorization.k8s.io/kubernetes-dashboard created
clusterrole.rbac.authorization.k8s.io/kubernetes-dashboard created
rolebinding.rbac.authorization.k8s.io/kubernetes-dashboard created
clusterrolebinding.rbac.authorization.k8s.io/kubernetes-dashboard created
deployment.apps/kubernetes-dashboard created
service/dashboard-metrics-scraper created
deployment.apps/dashboard-metrics-scraper created
</pre>

#### Creating an user to access Kubernetes Dashboard
```
cd ~/devops-march-2022
git pull
cd Day3/k8s-dashboard

kubectl apply -f service-ac.yml
kubectl apply -f cluster-role-binding.yml
```
The expected output is

<pre>
jegan@master k8s-dashboard]$ ls
cluster-role-binding.yml  service-ac.yml
[jegan@master k8s-dashboard]$ pwd
/home/jegan/devops-march-2022/Day3/k8s-dashboard
[jegan@master k8s-dashboard]$ <b>kubectl apply -f service-ac.yml</b>
serviceaccount/admin-user created
[jegan@master k8s-dashboard]$ <b>kubectl apply -f cluster-role-binding.yml</b>
clusterrolebinding.rbac.authorization.k8s.io/admin-user created
</pre>

#### Port-forward to access the Dashboard URL
```
kubectl proxy
```

The expected output is

<pre>
[jegan@master etcd-operator]$ <b>kubectl proxy</b>
Starting to serve on 127.0.0.1:8001
</pre>

#### Create a bearer token to login 
```
kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}"
```

The expected output is
<pre>
[jegan@master k8s-dashboard]$ kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}"
eyJhbGciOiJSUzI1NiIsImtpZCI6Ikt6elV0ZkU4TmNQX2hTRF9aemdFREdlemNzZWJrNGc1NmlvajEtUmhleWMifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLTVzaGM0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI1ZDI2MmQ4Ni00ZTVhLTQyNzEtYTI1Yi1lYzVjZDhhNzIwNWYiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6YWRtaW4tdXNlciJ9.DxK3ckF6eGy_cZr6uPHY8JC0e8S-11TUxSGixUBGeTBTfD2fm05MhFqaPFZ45SzuWQjTyna9ytIwxKB0gKBo06LwX4paYd1kcOH6fjgB0sxLLobn8hN6eNEnrCMbQjeMTwuqjx-HGftJLxjrulksoNaMdwu6cEMzK0NSOt3XgmnTWEv4kbdeGwLAC0zO8UV2uOG-shxV7I0BETWxF49cGCgRGB_4wtjWU44a6TTmY84ONn535Toumo_KL4e-SaXE2WTsmOmSeMrmXZdTUIEZCt3z3CDT-LfRxR-ZiAq9Q8_W-gIcXplqSp8ww5KCkOgjbhJRk6RCWlYUZinZtb_Z5g[jegan@master k8s-dashboard]$ 
</pre>

#### Launching your Kubernetes Dashboard from Google Chrome browser
```
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login
```

You will get a page like shown in the screenshot below
![dashboard](dashboard.png)

Now copy and paste the bearer token to login as shown in the screenshot below
![dashboard](dashboard-token.png)

If all went butter smooth, you will get a similar page as shown below
![dashboard](dashboard-final.png)

## ⛹️‍♀️ Lab - Installing Helm Kubernetes package manager
```
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh
```

## ℹ️ Kubernetes Operators Hub
This portal has many open source ready made Kubernetes Operators that can be installed into your Kubernetes Cluster.
```
https://operatorhub.io/
```

## ⛹️‍♀️ Lab - Installing minikube kubernetes
For detailed installation instructions, you may refer the official documentation @ https://minikube.sigs.k8s.io/docs/start/

```
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```
The expected output is
<pre>
jegan@tektutor.org ~]$ <b>curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64</b>
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100 69.2M  100 69.2M    0     0  19.7M      0  0:00:03  0:00:03 --:--:-- 19.7M
[jegan@tektutor.org ~]$ <b>sudo install minikube-linux-amd64 /usr/local/bin/minikube</b>
</pre>

Starting minikube cluster
```
minikube start
```

<pre>
[jegan@tektutor.org ~]$ <b>minikube start</b>
😄  minikube v1.25.2 on Centos 7.9.2009
✨  Automatically selected the docker driver. Other choices: none, ssh
👍  Starting control plane node minikube in cluster minikube
🚜  Pulling base image ...
💾  Downloading Kubernetes v1.23.3 preload ...
    > preloaded-images-k8s-v17-v1...: 505.68 MiB / 505.68 MiB  100.00% 13.33 Mi
    > gcr.io/k8s-minikube/kicbase: 379.06 MiB / 379.06 MiB  100.00% 5.48 MiB p/
🔥  Creating docker container (CPUs=2, Memory=7900MB) ...
🐳  Preparing Kubernetes v1.23.3 on Docker 20.10.12 ...
    ▪ kubelet.housekeeping-interval=5m
    ▪ Generating certificates and keys ...
    ▪ Booting up control plane ...
    ▪ Configuring RBAC rules ...
🔎  Verifying Kubernetes components...
    ▪ Using image gcr.io/k8s-minikube/storage-provisioner:v5
🌟  Enabled addons: default-storageclass, storage-provisioner
🏄  Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default
</pre>

You may now try listing the nodes in the minikube cluster
```
jegan@minikube ~]$ <b>kubectl get nodes</b>
NAME       STATUS   ROLES                  AGE   VERSION
minikube   Ready    control-plane,master   33s   v1.23.3
```

## ⛹️‍♂️ Lab - Installing mysql Kubernetes Operator in the minikube K8s cluster

```
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-crds.yaml
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-operator.yaml
```

The expected output is
<pre>
[jegan@tektutor.org ~]$ <b>kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-crds.yaml</b>
customresourcedefinition.apiextensions.k8s.io/innodbclusters.mysql.oracle.com created
customresourcedefinition.apiextensions.k8s.io/mysqlbackups.mysql.oracle.com created
customresourcedefinition.apiextensions.k8s.io/clusterkopfpeerings.zalando.org created
customresourcedefinition.apiextensions.k8s.io/kopfpeerings.zalando.org created
</pre>

Let's check the deploy status
```
kubectl get deployment -n mysql-operator mysql-operator
```

The expected output is
<pre>
jegan@tektutor.org ~]$ <b>kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-operator.yaml</b>
serviceaccount/mysql-sidecar-sa created
clusterrole.rbac.authorization.k8s.io/mysql-operator created
clusterrole.rbac.authorization.k8s.io/mysql-sidecar created
clusterrolebinding.rbac.authorization.k8s.io/mysql-operator-rolebinding created
clusterkopfpeering.zalando.org/mysql-operator created
namespace/mysql-operator created
serviceaccount/mysql-operator-sa created
deployment.apps/mysql-operator created
</pre>

Let's configure mysql root password as a Kubernetes secrets
```
kubectl create secret generic mypwds \
        --from-literal=rootUser=root \
        --from-literal=rootHost=% \
        --from-literal=rootPassword="root"
```

The expected output is
<pre>
[jegan@tektutor.org ~]$ <b>kubectl create secret generic mypwds \
>         --from-literal=rootUser=root \
>         --from-literal=rootHost=% \
>         --from-literal=rootPassword="root"</b>
secret/mypwds created
</pre>

Let's create the sample mysql cluster now
```
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/samples/sample-cluster.yaml
```

The expected output is
<pre>
[jegan@tektutor.org ~]$ <b>kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/samples/sample-cluster.yaml</b>
innodbcluster.mysql.oracle.com/mycluster created
</pre>

Let's watch the mysql cluster creation status
```
kubectl get innodbcluster --watch
```

The expected output is
<pre>
jegan@tektutor.org ~]$ kubectl get innodbcluster --watch
NAME        STATUS    ONLINE   INSTANCES   ROUTERS   AGE
mycluster   PENDING   0        3           1         10s
mycluster   PENDING   0        3           1         84s
mycluster   INITIALIZING   0        3           1         84s
mycluster   INITIALIZING   0        3           1         84s
mycluster   INITIALIZING   0        3           1         84s
mycluster   INITIALIZING   0        3           1         85s
mycluster   INITIALIZING   0        3           1         89s
mycluster   ONLINE         1        3           1         89s
</pre>

Let's check the cluster service
```
kubectl get service mycluster
kubectl describe service mycluster
```

The expected output is
<pre>
[jegan@tektutor.org ~]$ <b>kubectl get service mycluster</b>
NAME        TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                               AGE
mycluster   ClusterIP   10.108.210.32   <none>        6446/TCP,6448/TCP,6447/TCP,6449/TCP   2m14s
[jegan@minikube ~]$ <b>kubectl describe service mycluster</b>
Name:              mycluster
Namespace:         default
Labels:            mysql.oracle.com/cluster=mycluster
                   tier=mysql
Annotations:       <none>
Selector:          component=mysqlrouter,mysql.oracle.com/cluster=mycluster,tier=mysql
Type:              ClusterIP
IP Family Policy:  SingleStack
IP Families:       IPv4
IP:                10.108.210.32
IPs:               10.108.210.32
Port:              mysql  6446/TCP
TargetPort:        6446/TCP
Endpoints:         172.17.0.5:6446
Port:              mysqlx  6448/TCPAs the Kubernetes mysql operator isn't officially tested/supported in latest version of Kubernetes v1.24
100

TargetPort:        6448/TCP
Endpoints:         172.17.0.5:6448
Port:              mysql-ro  6447/TCP
TargetPort:        6447/TCP
Endpoints:         172.17.0.5:6447
Port:              mysqlx-ro  6449/TCP
TargetPort:        6449/TCP
Endpoints:         172.17.0.5:6449
Session Affinity:  NoneAs the Kubernetes mysql operator isn't officially tested/supported in latest version of Kubernetes v1.24
100

Events:            <none>
</pre>

