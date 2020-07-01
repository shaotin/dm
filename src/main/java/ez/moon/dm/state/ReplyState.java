package ez.moon.dm.state;

import ez.moon.DContext;
import ez.moon.dm.action.Action;
import ez.moon.dm.action.IntentAction;
import ez.moon.dm.action.ReplyAction;
import ez.moon.event.Event;
import ez.moon.event.IntentEvent;
import ez.moon.model.IntentModel;

import java.util.List;

/**
 * 回复状态节点：只能后接意图节点， 根据意图的数目，可以有多个后续节点
 */
public class ReplyState extends AbstractState{
    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    //TODO 回复节点的原始节点数据
    private Object originalData;

    //回复话术
    private String replyContent;

    //识别出的意图
    private IntentModel recognizedIntent;

    /**
     * 创建节点：设置ID, 设置上下文对象
     * 但不初始化，初始化有上下文生命周期统一管理
     *
     * @param context
     * @param stateNodeId
     */
    public ReplyState(DContext context, String stateNodeId, Object originalData) {
        super(context, stateNodeId);
        this.originalData = originalData;

    }

    /**
     * 回复节点的初始化：
     * a. 后续节点的初始化
     * b. 话术的初始化
     */
    @Override
    public void init() {
        this.initFollowingNodes();
        this.initReplyContent();
    }

    protected void initReplyContent(){
        //TODO : 从originalData中获取回复话术, 设置到replyContent中
    }

    /**
     * 回复节点有多个后续意图节点
     */
    @Override
    protected void initFollowingNodes() {
        List<State> followingNodes = getFollowingNodes();
        for(State st: followingNodes){
            this.followingNodesMap.put(st.getId(), st);
        }
    }

    /**
     * 回复节点有多个后续意图节点
     * @return
     */
    private List<State> getFollowingNodes(){
        //TODO 从originalData中获取所有后续意图节点
        return  null;
    }

    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //*************************************************************************************************************


    @Override
    public void onEntry() {
        //回复消息给用户
        sendMessageToUser();
        //判断是否最后一个叶子节点，如果叶子节点，跳转到终止节点
        if(!hasFollowingNodes()){
            this.context.changeState(this.context.getTerminalNode());
            return;
        }
        //非叶子节点时，当前状态保持不变
    }

    @Override
    public void onEvent(Event event) {
        //调用意图识别接口识别意图
        IntentAction intentAction = new IntentAction((IntentEvent)event);
        IntentModel intent = (IntentModel)intentAction.doAction(null);

        //识别出后续的某个意图, 流转到识别出的后续意图状态
        this.recognizedIntent = intent;
        State nextIntentState = decideNextStateAtRuntime();
        this.context.changeState(nextIntentState);

        //TODO 回复状态节点：未识别出意图处理，待业务需求

        //TODO：回复状态节点， 识别出非当前后续节点意图处理，待业务需求
    }

    @Override
    protected State decideNextStateAtRuntime() {
        //TODO : 根据当前识别出的意图获取后续意图节点
        return null;
    }

    /**
     * 回复消息给用户
     */
    private void sendMessageToUser(){
        Action replyAction = new ReplyAction(this.replyContent);
        replyAction.doAction(null);
    }

    /**
     * 是否有后续节点，如果没有后续节点，直接跳转到终止节点
     * @return
     */
    private boolean hasFollowingNodes(){
        return this.followingNodesMap.size()>0;
    }
}
