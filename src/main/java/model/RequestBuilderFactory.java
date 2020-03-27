package main.java.model;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class RequestBuilderFactory {

    private static String baseUrl = "https://petstore.swagger.io/v2/user";

    public static RequestSpecification getBaseRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUrl)
                .build();
    }
}
