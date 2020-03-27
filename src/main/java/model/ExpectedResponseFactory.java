package main.java.model;

import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.ResponseSpecification;
import main.java.model.user.User;

import static org.hamcrest.CoreMatchers.equalTo;

public class ExpectedResponseFactory {

    private static String badInputMessage = "bad input";
    private static String notFoundMessage = "User not found";
    private static String unknownType = "unknown";
    private static String errorType = "error";

    public static ResponseSpecification getSuccessResponse(String message) {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("code", equalTo(200))
                .expectBody("type", equalTo(unknownType))
                .expectBody("message", equalTo(message))
                .build();
    }

    public static ResponseSpecification getWrongCreateResponseForUser() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("code", equalTo(400))
                .expectBody("type", equalTo(unknownType))
                .expectBody("message", equalTo(badInputMessage))
                .build();
    }

    public static ResponseSpecification getSuccessResponseWithUserInfo(User user) {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", equalTo(user.getId()))
                .expectBody("username", equalTo(user.getUserName()))
                .expectBody("firstName", equalTo(user.getFirstName()))
                .expectBody("lastName", equalTo(user.getLastName()))
                .expectBody("email", equalTo(user.getEmail()))
                .expectBody("password", equalTo(user.getPassword()))
                .expectBody("phone", equalTo(user.getPhone()))
                .expectBody("userStatus", equalTo(user.getUserStatus()))
                .build();
    }

    public static ResponseSpecification getNotFoundResponseWithBody() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectBody("code", equalTo(1))
                .expectBody("type", equalTo(errorType))
                .expectBody("message", equalTo(notFoundMessage))
                .build();
    }

    public static ResponseSpecification getNotFoundResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    public static ResponseSpecification getMethodNotAllowed() {
        return new ResponseSpecBuilder()
                .expectStatusCode(405)
                .build();
    }
}
