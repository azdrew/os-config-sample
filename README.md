# os-config-sample
OpenShift Config with Spring Cloud Kubernetes.

This is a simple app that reads properties from a ConfigMap and Secret. It is deployed and tested with MiniShift, but likely works with MiniKube.

For reading Config Maps, the service account needs view permission.

```bash
oc policy add-role-to-user view system:serviceaccount:$(oc project -q):default -n $(oc project -q)
```

For reading Secrets via API, the service account needs a role that includes secrets.
The ClusterRole `openshift/view-plus-secrets.yml` is a copy of the view ClusterRole that adds secrets. 

```bash
oc create -f ./openshift/roles/view-plus-secrets-role.yml

oc policy add-role-to-user view-plus-secrets system:serviceaccount:$(oc project -q):default -n $(oc project -q)
```

To deploy, run 

```bash
 mvn -Pfabric8 -Popenshift fabric8:deploy
```

The root path `/` returns two property values, `demo.property` and `demo.secret`. Also the `/actuator/env` endpoint can be queried to validate the Property Sources.

If running properly, the properties should be read from the resources in the `src/main/fabric8` folder.

