package ez.moon.dm.state;

import ez.moon.DContext;

/**
 * 开始状态节点，对话被创建时，被设置为该状态
 * 此节点是一个瞬时状态节点，被设置为当前状态后，会立即流转到入口意图状态
 */
public class StartState extends AbstractState {

    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    public StartState(DContext context, String stateNodeId){
        super(context, stateNodeId);
    }


    /**
     * 开始节点的初始化：只需设置后续节点，后续节点为入口意图节点
     */
    @Override
    public void init() {
        this.initFollowingNodes();
    }

    @Override
    public void destroy() {
        //初始化接口销毁时，do nothing
    }

    /**
     * 初始状态的后续节点唯一，就是入口意图节点状态
     */
    @Override
    protected void initFollowingNodes() {
        State entryIntentState = this.context.getEntryIntentState();
        this.followingNodesMap.put(entryIntentState.getId(), entryIntentState);
    }


    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //*************************************************************************************************************

    /**
     * 此节点被设置为当前状态时被调用，
     * 此节点是一个瞬时节点，被设置为当前状态后。 立即流转到入口意图状态
     * 从上下文中获取入口意图，并将入口意图对应的意图节点设置为当前状态
     */
    @Override
    public void onEntry() {
        State entryState = decideNextStateAtRuntime();
        this.context.changeState(entryState);
    }

    /**
     * 初始状态节点的下一个状态节点获取策略是：入口意图所表示的状态节点
     * @return
     */
    @Override
    protected State decideNextStateAtRuntime() {
        return this.context.getEntryIntentState();
    }

}
