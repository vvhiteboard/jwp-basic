package next.controller.qna;

import core.mvc.AbstractController;
import core.view.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        ModelAndView modelAndView = jspView("/qna/show.jsp");
        modelAndView.addObject("question", questionDao.findById(questionId));
        modelAndView.addObject("answers", answerDao.findAllByQuestionId(questionId));

        return modelAndView;
    }
}
