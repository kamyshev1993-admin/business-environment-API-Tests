package test.java;
import com.jayway.restassured.specification.ResponseSpecification;
import io.qameta.allure.Description;
import main.java.model.JsonObjectFactory;
import main.java.model.ExpectedResponseFactory;
import main.java.model.RequestBuilderFactory;
import main.java.model.user.User;
import main.java.model.user.UserFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CreateUserTest extends Base {

    private User user = UserFactory.getOptionCorrectUser();

    @Test
    @Description("Добавление корректного пользователя")
    public void createCorrectUserOkTest1() {
        JSONObject jsonObject = JsonObjectFactory.getCorrectUserJson(user);
        execute(jsonObject.toString(), ExpectedResponseFactory.getSuccessResponse(String.valueOf(user.getId())));
    }

    @Test
    @Description("Добавление пользователя, передавая пустое тело запроса")
    public void tryCreateUserByEmptyBodyErrorTest2() {
        execute(new JSONObject().toString(), ExpectedResponseFactory.getWrongCreateResponseForUser());
    }

    @Test
    @Description("Добавление пользователя, передавая некорректное тело запроса")
    public void tryCreateByIncorrectBodyErrorTest3() {
        execute(RandomStringUtils.randomAlphabetic(10), ExpectedResponseFactory.getWrongCreateResponseForUser());
    }

    @Test
    @Description("Проверка, что пользователь не добавиться методом get")
    public void tryCreateUserByGetMethodErrorTest4() {
        JSONObject jsonObject = JsonObjectFactory.getCorrectUserJson(user);
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .body(jsonObject.toString())
                .when()
                .get()
                .then()
                .spec(ExpectedResponseFactory.getMethodNotAllowed());
    }

    private void execute(String body, ResponseSpecification responseSpecification) {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .body(body)
                .when()
                .post()
                .then()
                .spec(responseSpecification);
    }
}
