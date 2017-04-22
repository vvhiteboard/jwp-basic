package core.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by 77loo on 2017-04-22.
 */
public class JspView implements View {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private String targetView;

    public JspView(String targetView) {
        if (targetView == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
        this.targetView = targetView;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (targetView.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            resp.sendRedirect(targetView.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }

        Set<String> keys = model.keySet();
        for ( String key : keys) {
            req.setAttribute(key, model.get(key));
        }

        RequestDispatcher rd = req.getRequestDispatcher(targetView);
        rd.forward(req, resp);
    }
}
