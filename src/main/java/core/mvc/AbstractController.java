package core.mvc;

import core.view.JsonView;
import core.view.JspView;
import core.view.ModelAndView;

/**
 * Created by 77loo on 2017-04-22.
 */
public abstract class AbstractController implements Controller {
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
