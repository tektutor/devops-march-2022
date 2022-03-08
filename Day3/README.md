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

## Creating a user to access Kubernetes Dashboard
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


