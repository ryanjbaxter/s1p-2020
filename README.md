# Unleash The True Power Of Spring Cloud

## Requirements

* Eureka server running on port 8761
* RabbitMQ server running on port 5672

## To Build

`$ ./mvnw clean package`

## Config Server

`CustomEnvironmentRepository` - https://github.com/ryanjbaxter/s1p-2020/blob/master/config-server/src/main/java/com/example/chaos/monkey/shopping/config/ChaosConfigServerApplication.java#L32

## Bus

Custom Event Publishing - https://github.com/ryanjbaxter/s1p-2020/blob/master/hotdeals-updater/src/main/java/org/example/chaos/monkey/shopping/hotdeals/updater/HotDealsUpdaterApplication.java
Event Listener - https://github.com/ryanjbaxter/s1p-2020/blob/master/hot-deals/src/main/java/com/example/chaos/monkey/shopping/hotdeals/HotDealsRestController.java
Event Registration - https://github.com/ryanjbaxter/s1p-2020/blob/master/hot-deals/src/main/java/com/example/chaos/monkey/shopping/hotdeals/HotDealsApplication.java#L19

## CircuitBreaker Customization

https://github.com/ryanjbaxter/s1p-2020/blob/master/gateway/src/main/java/com/example/chaos/monkey/shopping/gateway/GatewayApplication.java#L87

## Custom Gateway Filter

https://github.com/ryanjbaxter/s1p-2020/blob/master/gateway/src/main/java/com/example/chaos/monkey/shopping/gateway/GatewayApplication.java#L122 


