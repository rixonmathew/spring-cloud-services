Collection of spring cloud infrastructure services

## Eureka Server
* Run standalone or clustered eureka server

## Config Server
* Run a set of configuration servers using github as backgroud

## Vault Server
* Run cluster of vault servers to get secrets

## Gateway
*  Spring cloud gateway that acts as api gateway

### Generating self-signed certificates for local testing

* Ensure certificate to be used is generated on one machine and copied over to all the hosts that are part of the cluster. On mac hosts use keychain for importing and granting trust

````
keytool -keystore server.jks -storepass password -deststoretype pkcs12 -genkeypair -keyalg RSA -alias localhost -validity 3650 -dname "CN=localhost,ou=it,o=self,c=in" -ext "SAN=dns:rixp330,dns:rixm1mini,dns:rixmbp,dns:localhost"

#export cert
keytool -export -alias localhost -file localhost.cer -keystore server.jks -storepass password

#import into cacerts
keytool -import -alias localhost -file localhost.cer -cacerts

#add localhost.cer via chrome settings (manage certificates)
````
