# Spring Boot PayPal integration

This is a demo example of how to integrate paypal payment method on your application.

### PayPal maven dependencies

```xml
<dependencies>
    <dependency>
        <groupId>com.paypal.sdk</groupId>
        <artifactId>paypal-core</artifactId>
        <version>1.7.2</version>
    </dependency>

    <dependency>
        <groupId>com.paypal.sdk</groupId>
        <artifactId>rest-api-sdk</artifactId>
        <version>1.14.0</version>
    </dependency>
</dependencies>
```

### Setting up PayPal configuration
In order to use PayPal integration in your project in development mode you have to set
`mode = sandbox` in your .properties or .yml file

Also, you have to generate the `clientId` and the `clientSecret` by login in with your PayPal account at 
https://developer.paypal.com

![paypal-sandbox](https://github.com/amadr-95/paypal-spring-boot-integration/assets/122611230/e960ed18-b1cb-4d0c-b5bc-ee3d9a9e7408)

![paypal-keys](https://github.com/amadr-95/paypal-spring-boot-integration/assets/122611230/17730659-eda7-4693-b207-611aa7af1892)


Once you have the generated keys, copy them in the .properties or .yml file.

### Some screenshots

![ui](https://github.com/amadr-95/paypal-spring-boot-integration/assets/122611230/4270119e-6b34-44c5-b82c-ab8c6b766fdb)

![payment](https://github.com/amadr-95/paypal-spring-boot-integration/assets/122611230/d310d751-9273-4139-bb91-86a73083c83e)
