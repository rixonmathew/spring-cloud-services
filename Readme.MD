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

* Ensure certificate to be used is generated on one machine and copied over to all the hosts that are part of the cluster. 
* On mac hosts use keychain for importing and granting trust

````
keytool -keystore server.jks -storepass password -deststoretype pkcs12 -genkeypair -keyalg RSA -alias localhost -validity 3650 -dname "CN=localhost,ou=it,o=self,c=in" -ext "SAN=dns:rixp330,dns:rixm1mini,dns:rixmbp,dns:localhost,dns:rixmacmini2011,dns:rixpi1,dns:rixpi2,dns:rixpi3,dns:rixpi4,dns:rixpi5,dns:rixpi6,dns:rixpi7"

#export cert
keytool -export -alias localhost -file localhost.cer -keystore server.jks -storepass password

# if certs already exists then remove it first before adding
keytool -delete -alias localhost -cacerts

#import into cacerts
keytool -import -alias localhost -file localhost.cer -cacerts

#add localhost.cer via chrome settings (manage certificates)
````
