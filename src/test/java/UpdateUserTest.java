package test.java;

import com.jayway.restassured.specification.ResponseSpecification;
import io.qameta.allure.Description;
import main.java.model.ExpectedResponseFactory;
import main.java.model.JsonObjectFactory;
import main.java.model.RequestBuilderFactory;
import main.java.model.user.User;
import main.java.model.user.UserFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UpdateUserTest extends Base {

    @Test(groups = {"with creating random user before", "with checking random user after"})
    @Description("Изменение существующего пользователя")
    public void updateExistUserOkTest1() {
        User newRandomUser = UserFactory.getOptionCorrectUser();
        JSONObject jsonObject = JsonObjectFactory.getCorrectUserJson(newRandomUser);
        ResponseSpecification responseSpecification = ExpectedResponseFactory.getSuccessResponse(String.valueOf(newRandomUser.getId()));
        execute(randomUser.getUserName(), jsonObject.toString(), responseSpecification);
        getCorrectUser(newRandomUser);
    }

    @Test
    @Description("Попытка изменить несуществующего пользователя")
    public void tryUpdateNotExistUserErrorTest2() {
        execute(RandomStringUtils.randomAlphabetic(15), getCorrectBody(), ExpectedResponseFactory.getNotFoundResponse());
    }

    @Test
    @Description("Попытка изменить пользователя без указания username")
    public void tryUpdateUserWithoutTypingUserNameErrorTest3() {
        execute("", getCorrectBody(), ExpectedResponseFactory.getMethodNotAllowed());
    }

    @Test(groups = "with creating random user before")
    @Description("Попытка изменить пользователя без указания тела запроса")
    public void tryUpdateUserWithoutBodyErrorTest4() {
        execute(randomUser.getUserName(), "", ExpectedResponseFactory.getMethodNotAllowed());
    }

    @Test(groups = "with creating random user before")
    @Description("Попытка изменить пользователя передавая пустое тело запроса")
    public void tryUpdateUserWithoutBodyErrorTest5() {
        execute(randomUser.getUserName(), new JSONObject().toString(), ExpectedResponseFactory.getMethodNotAllowed());
    }

    @Test(groups = "with creating random user before")
    @Description("Попытка изменить пользователя передавая пустое тело запроса и без указания userName")
    public void tryUpdateUserWithoutBodyAndUserNameErrorTest6() {
        execute("", new JSONObject().toString(), ExpectedResponseFactory.getMethodNotAllowed());
    }

   private String getCorrectBody() {
       User newRandomUser = UserFactory.getOptionCorrectUser();
       return JsonObjectFactory.getCorrectUserJson(newRandomUser).toString();
   }

    private void execute(String userName, String body, ResponseSpecification responseSpecification) {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .body(body)
                .when()
                .put("/" + userName)
                .then()
                .spec(responseSpecification);
    }
}
