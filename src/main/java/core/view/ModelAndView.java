package core.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 77loo on 2017-04-22.
 */
public class ModelAndView {
    private View view;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView addObject(String attributeKey, Object attributeValue) {
        model.put(attributeKey, attributeValue);
        return this;
    }

    public Map<String, Object> getModel() {
        // model 객체를 수정하지 못하도록 unmodifiableMap을 리턴
        // 수정할 수 있는 메소드 호출 시 예외를 던진다 ( UnsupportedOperator )
        return Collections.unmodifiableMap(model);
    }

    public View getView() {
        return view;
    }
}
