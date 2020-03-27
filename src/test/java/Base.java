package test.java;

import io.qameta.allure.Step;
import main.java.model.ExpectedResponseFactory;
import main.java.model.JsonObjectFactory;
import main.java.model.RequestBuilderFactory;
import main.java.model.user.User;
import main.java.model.user.UserFactory;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.jayway.restassured.RestAssured.given;

public abstract class Base {

    protected User randomUser;
    protected User existUser = UserFactory.getExistUser();

    @BeforeMethod(onlyForGroups = "with creating random user before")
    @Step("Создание случайного пользователя перед выполнением Update/Delete")
    public void createCorrectRandomUser() {
        randomUser = UserFactory.getOptionCorrectUser();
        JSONObject jsonObject = JsonObjectFactory.getCorrectUserJson(randomUser);
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .body(jsonObject.toString())
                .when()
                .post();
    }

    @AfterMethod(onlyForGroups = "with checking random user after")
    @Step("Проверка, что случайный удаленный/измененный пользователь отсутствует")
    public void tryGetRandomUser() {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .get("/" + randomUser.getUserName())
                .then()
                .spec(ExpectedResponseFactory.getNotFoundResponse());
    }

    @Step("Проверка, что пользователь существует")
    public void getCorrectUser(User user) {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .get("/" + user.getUserName())
                .then()
                .spec(ExpectedResponseFactory.getSuccessResponseWithUserInfo(user));
    }
}
