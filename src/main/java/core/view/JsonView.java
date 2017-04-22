package core.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 77loo on 2017-04-22.
 */
public class JsonView implements View {

    @Override
    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(mapper.writeValueAsString(model));
        return;
    }
}
