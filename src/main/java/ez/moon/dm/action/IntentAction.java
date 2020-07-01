package ez.moon.dm.action;

import ez.moon.event.IntentEvent;
import ez.moon.model.IntentModel;

import java.util.Map;

/**
 * 意图识别行为
 */
public class IntentAction implements Action{

    //意图事件：
    private IntentEvent event;

    public IntentAction(IntentEvent event){
        this.event = event;
    }

    @Override
    public Object doAction(Map<String, Object> parameters) {
        IntentModel intent = null;

        //TODO 调用意图识别接口，获取真实意图

        return intent;
    }
}
