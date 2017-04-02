package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 77loo on 2017-04-02.
 */
public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if(forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null, 이동할 URL을 입력하세요.");
        }

        this.forwardUrl = forwardUrl;
    }

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return forwardUrl;
    }
}
