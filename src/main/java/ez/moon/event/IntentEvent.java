package ez.moon.event;

/**
 * 意图事件： 封装的使用户输入，还未识别出真实意图
 */
public class IntentEvent implements  Event{
    //用户说的内容
    private String content;

    public String getData(){
        return content;
    }

}
