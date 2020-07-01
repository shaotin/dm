package ez.moon;

/**
 * 流程状态异常
 */
public class IllegalFlowStateException extends RuntimeException{
    public IllegalFlowStateException(String message){
        super(message);
    }
}
