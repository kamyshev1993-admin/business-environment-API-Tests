package main.java.model;
import main.java.model.user.User;
import org.json.JSONObject;

public class JsonObjectFactory {

    public static JSONObject getCorrectUserJson(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("username", user.getUserName());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("phone", user.getPhone());
        jsonObject.put("userStatus", user.getUserStatus());
        return jsonObject;
    }
}
