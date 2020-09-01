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

## SC LoadBalancer Configuration

- [Switching between Ribbon and SC LoadBalancer](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/main/resources/application.yml#L21)

- [A custom configuration](https://github.com/ryanjbaxter/s1p-2020/blob/master/shop/src/main/java/com/example/chaos/monkey/shopping/CustomLoadBalancerConfiguration.java)

- [Including custom configuration](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/main/java/com/example/chaos/monkey/shopping/shop/ShopApplication.java#L13) 

- [LoadBalancer cache configuration](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/main/resources/application.yml#L15)

- [Including caffeine cache dependency](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/pom.xml#L43)

## SC OpenFeign Configuration

- [A custom configuration](https://github.com/ryanjbaxter/s1p-2020/blob/master/shop/src/main/java/com/example/chaos/monkey/shopping/CustomFeignClientConfiguration.java)

- [Including custom configuration](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/main/java/com/example/chaos/monkey/shopping/shop/FashionBestsellerClient.java#L17)

- [Setting OpenFeign collection format](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/main/java/com/example/chaos/monkey/shopping/shop/FashionBestsellerClient.java#L25)

## SC Contract Configuration

- [SC Contract Verifier plugin configuration](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/bestseller-fashion/pom.xml#L68-L82)

- [Stubrunner AutoConfiguration setup](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/test/java/com/example/chaos/monkey/shopping/shop/FashionBestsellersIntegrationTests.java#L19-L20)

- [Stubrunner configuration in properties](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/shop/src/test/resources/application.yml#L1-L4)

- [Consumer-side runtime-generated stubs](https://github.com/ryanjbaxter/s1p-2020/blob/master/shop/src/test/java/com/example/chaos/monkey/shopping/shop/ShopNonExistentEndpointIntegrationTests.java)

- [Producer-side in-progress contract](https://github.com/ryanjbaxter/s1p-2020/blob/master/bestseller-fashion/src/test/resources/contracts/fashion/shouldReturnBestsellersOfTheYear.groovy)

- [Producer-side contracts in external repository](https://github.com/ryanjbaxter/s1p-2020/blob/ab0cf44a33d0628944f6104af602cba9d1a1f7f1/bestseller-toys/pom.xml#L70-L91)
