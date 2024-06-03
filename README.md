# Spring Boot PayPal integration

This is a demo example of how to integrate paypal payment method on your application.

### Paypal maven dependencies

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

[paypal-sandbox]

[paypal-keys]

Once you have the generated keys, copy them in the .properties or .yml file.