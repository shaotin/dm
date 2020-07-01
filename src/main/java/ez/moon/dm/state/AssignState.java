package ez.moon.dm.state;

import ez.moon.DContext;

/**
 * 赋值状态节点：
 * 此节点是一个瞬时状态节点，被设置为当前状态后，会立即流转到下一个状态
 * 赋值状态节点只有唯一的后续节点（后续节点可以为意图节点外的其他任意类型节点），不能后接意图节点
 */
public class AssignState extends AbstractState{

    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    //TODO 赋值节点的原始节点数据
    private Object originalData;

    //后续节点ID, 赋值节点的后续节点唯一
    private String followingNodeId;

    /**
     * 创建节点：设置ID, 设置上下文对象
     * 但不初始化，初始化有上下文生命周期统一管理
     *
     * @param context
     * @param stateNodeId
     */
    public AssignState(DContext context, String stateNodeId, Object originalData) {
        super(context, stateNodeId);
        this.originalData = originalData;
    }

    /**
     * 赋值节点：
     * 需要初始化后续节点
     * 需要初始化 赋值操作相关的信息，赋值操作相关信息从originalDate中获取，包括 源数据，目标变量
     */
    @Override
    public void init() {
        this.initFollowingNodes();
        this.initAssignmentData();
    }

    @Override
    public void destroy() {
        //TODO: 赋值节点销毁 操作 待定
    }

    /**
     * 赋值节点后续节点唯一， 类型可以为除意图外的任意类型
     */
    @Override
    protected void initFollowingNodes() {
        State followingNode = this.getFollowingNode();
        this.followingNodeId = followingNode.getId();
        this.followingNodesMap.put(followingNode.getId(), followingNode);
    }

    private State getFollowingNode(){
        //TODO 获取该赋值节点的后续节点, 从 originalDataz中获取
        return null;
    }

    private void initAssignmentData(){
        //TODO: 初始化赋值相关的数据结构， 从originalData中获取
    }


    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //****

    /**
     * 赋值状态时，直接流转到后续节点
     */
    @Override
    public void onEntry() {
        this.doAssignment();
        State nextNode = this.decideNextStateAtRuntime();
        this.context.changeState(nextNode);
    }

    @Override
    protected State decideNextStateAtRuntime() {
        return this.followingNodesMap.get(this.followingNodeId);
    }

    private void doAssignment(){
        //TODO: 执行赋值操作， 待实现
    }
}
