package core.web.dispatcher;

import next.controller.Controller;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 77loo on 2017-04-02.
 */
/*
loadOnStartUp : 톰캣이 실행 될 때 init을 호출하는 순서 지정
0 또는 양수 : 숫자가 작을수록  먼저 로딩 (init 호출)
음수 또는 미지정 : 최초로 서블릿 호출이 발생했을 때 로딩 (최초로 url에 접근했을 때 init 호출)
 */
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;
    private final static String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init(ServletConfig config) throws ServletException {
        requestMapping = new RequestMapping();
        requestMapping.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Controller controller = requestMapping.getController(uri);

        String viewPath = controller.service(req, resp);

        viewNavigate(viewPath, req, resp);
    }

    private void viewNavigate(String viewPath, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isRedirect(viewPath)) {
            resp.sendRedirect(viewPath.substring(REDIRECT_PREFIX.length()));
            return;
        }

        req.getRequestDispatcher(viewPath).forward(req, resp);
    }

    private boolean isRedirect(String viewPath) {
        return StringUtils.startsWith(viewPath, REDIRECT_PREFIX);
    }
}
