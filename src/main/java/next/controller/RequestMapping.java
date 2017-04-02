package next.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 77loo on 2017-04-02.
 */
public class RequestMapping {
    private Map<String, Controller> requestMap = new HashMap<>();

    public void init() {
        requestMap.put("/", new HomeController());
        requestMap.put("/users", new ListUserController());
        requestMap.put("/users/create", new CreateUserController());
        requestMap.put("/users/login", new LoginController());
        requestMap.put("/users/logout", new LogoutController());
        requestMap.put("/users/profile", new ProfileController());
        requestMap.put("/users/update", new UpdateUserController());
        requestMap.put("/users/updateForm", new UpdateUserFormController());
        requestMap.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        requestMap.put("/users/form", new ForwardController("/user/form.jsp"));
    }

    public Controller getController(String url) {
        return requestMap.get(url);
    }

    public void setController(String url, Controller newController) {
        requestMap.put(url, newController);
    }
}
