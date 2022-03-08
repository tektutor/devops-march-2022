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

## Installing Helm Kubernetes package manager
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


## ⛹️‍♂️ Lab - Installing etcd Kubernetes Operator from Operator Hub

Let's install Operator Lifecycle Manager (OLM) before installing any K8s operator

```
curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.20.0/install.sh | bash -s v0.20.0
```

The expected output is
<pre>
[jegan@master Day3]$ <b>curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.20.0/install.sh | bash -s v0.20.0</b>

customresourcedefinition.apiextensions.k8s.io/catalogsources.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/clusterserviceversions.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/installplans.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/olmconfigs.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/operatorconditions.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/operatorgroups.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/operators.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/subscriptions.operators.coreos.com created
customresourcedefinition.apiextensions.k8s.io/catalogsources.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/clusterserviceversions.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/installplans.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/olmconfigs.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/operatorconditions.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/operatorgroups.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/operators.operators.coreos.com condition met
customresourcedefinition.apiextensions.k8s.io/subscriptions.operators.coreos.com condition met
namespace/olm created
namespace/operators created
serviceaccount/olm-operator-serviceaccount created
clusterrole.rbac.authorization.k8s.io/system:controller:operator-lifecycle-manager created
clusterrolebinding.rbac.authorization.k8s.io/olm-operator-binding-olm created
olmconfig.operators.coreos.com/cluster created
deployment.apps/olm-operator created
deployment.apps/catalog-operator created
clusterrole.rbac.authorization.k8s.io/aggregate-olm-edit created
clusterrole.rbac.authorization.k8s.io/aggregate-olm-view created
operatorgroup.operators.coreos.com/global-operators created
operatorgroup.operators.coreos.com/olm-operators created
clusterserviceversion.operators.coreos.com/packageserver created
catalogsource.operators.coreos.com/operatorhubio-catalog created
Waiting for deployment "olm-operator" rollout to finish: 0 of 1 updated replicas are available...
deployment "olm-operator" successfully rolled out
Waiting for deployment "catalog-operator" rollout to finish: 0 of 1 updated replicas are available...
deployment "catalog-operator" successfully rolled out
Package server phase: Installing
Package server phase: Succeeded
deployment "packageserver" successfully rolled out
</pre>

Let's now install etcd operator
```
kubectl create -f https://operatorhub.io/install/etcd.yaml
```

The expected output is
<pre>
[jegan@master etcd-k8s-opertors]$ <b>kubectl create -f https://operatorhub.io/install/etcd.yaml</b>
namespace/my-etcd created
operatorgroup.operators.coreos.com/operatorgroup created
subscription.operators.coreos.com/my-etcd created
</pre>

Let's watch the Operators pods
```
kubectl get csv -n my-etcd
```

The expected output is
<pre>

</pre>