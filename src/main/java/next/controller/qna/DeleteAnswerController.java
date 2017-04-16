package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by loopin on 2017-04-17.
 */
public class DeleteAnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long answerId = Long.parseLong(req.getParameter("answerId"));
        log.debug("answerId : {}", answerId);

        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.print(mapper.writeValueAsString("success"));
        return null;
    }
}
