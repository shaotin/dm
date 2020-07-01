package ez.moon.dm.state;

import ez.moon.DContext;

/**
 * 服务调用状态节点：
 * 此节点是一个瞬时状态节点，被设置为当前状态后，会立即流转到下一个状态
 * 服务调用状态节点只有唯一的后续节点（后续节点可以为意图节点外的其他任意类型节点），不能后接意图节点
 */
public class CallState extends AbstractState{

    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    //TODO 调用节点的原始节点数据
    private Object originalData;

    //后续节点ID, 调用节点的后续节点唯一
    private String followingNodeId;

    /**
     * 创建节点：设置ID, 设置上下文对象
     * 但不初始化，初始化有上下文生命周期统一管理
     *
     * @param context
     * @param stateNodeId
     */
    public CallState(DContext context, String stateNodeId, Object originalData) {
        super(context, stateNodeId);
        this.originalData = originalData;
    }

    @Override
    public void init() {
        this.initFollowingNodes();
        this.initCallData();
    }

    @Override
    public void destroy() {
        //TODO: 调用节点销毁 操作 待定
    }

    @Override
    protected void initFollowingNodes() {
        State followingNode = this.getFollowingNode();
        this.followingNodeId = followingNode.getId();
        this.followingNodesMap.put(followingNode.getId(), followingNode);
    }
    private State getFollowingNode(){
        //TODO 获取该服务调用节点的后续节点, 从 originalDataz中获取
        return null;
    }

    private void initCallData(){
        //TODO: 初始化服务调用相关的数据结构， 从originalData中获取
    }

    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //****


    /**
     * 调用状态时，直接流转到后续节点
     */
    @Override
    public void onEntry() {
        this.doCall();
        State nextNode = this.decideNextStateAtRuntime();
        this.context.changeState(nextNode);
    }

    @Override
    protected State decideNextStateAtRuntime() {
        return this.followingNodesMap.get(this.followingNodeId);
    }

    private void doCall(){
        //TODO: 执行服务调用，待实现
    }
}
