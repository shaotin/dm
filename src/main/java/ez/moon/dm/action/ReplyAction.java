package ez.moon.dm.action;

import java.util.Map;

/**
 * 回复状态节点执行的行为： 回复消息给用户
 */
public class ReplyAction implements  Action{

    private String message;
    public ReplyAction(String message){
        this.message = message;
    }
    @Override
    public Object doAction(Map<String, Object> parameters) {
        //TODO 回复消息状态行为 发送消息给用户

        return null;
    }
}
