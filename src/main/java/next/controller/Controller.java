package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 77loo on 2017-04-02.
 */
public interface Controller {
    String service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
