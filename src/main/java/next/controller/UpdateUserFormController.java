package next.controller;

import core.db.DataBase;
import next.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 77loo on 2017-04-02.
 */
public class UpdateUserFormController implements Controller {
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = DataBase.findUserById(request.getParameter("userId"));

        if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        request.setAttribute("user", user);
        return "/user/updateForm.jsp";
    }
}
