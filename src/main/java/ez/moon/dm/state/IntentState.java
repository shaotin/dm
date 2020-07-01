package ez.moon.dm.state;

import ez.moon.DContext;

/**
 * 意图状态节点
 * 此节点是一个瞬时状态节点，被设置为当前状态后，会立即流转到下一个状态
 * 意图状态节点只有唯一的后续节点（后续节点可以为意图节点外的其他任意类型节点），不能后接意图节点
 */
public class IntentState extends AbstractState {
    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    //TODO 意图节点的原始节点数据
    private Object originalData;

    //后续节点ID, 意图节点的后续节点唯一
    private String followingNodeId;

    public IntentState(DContext context, String stateNodeId, Object originalData){
        super(context, stateNodeId);
        this.originalData = originalData;
    }

    /**
     * 意图状态节点：初始化只需要设置后续节点，而且后续节点唯一， 无其他初始化操作
     */
    @Override
    public void init() {
        this.initFollowingNodes();
    }

    @Override
    public void destroy() {
        //TODO: 意图节点销毁 操作 待定
    }

    /**
     * 意图节点后续节点唯一， 类型可以为除意图外的任意类型
     */
    @Override
    protected void initFollowingNodes() {
        State followingNode = this.getFollowingNode();
        this.followingNodeId = followingNode.getId();
        this.followingNodesMap.put(followingNode.getId(), followingNode);
    }

    private State getFollowingNode(){
        //TODO 获取该意图节点的后续节点, 从 originalDataz中获取
        return null;
    }

    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //*************************************************************************************************************

    /**
     * 意图状态时，直接流转到后续节点
     */
    @Override
    public void onEntry() {
        State nextNode = this.decideNextStateAtRuntime();
        this.context.changeState(nextNode);
    }

    @Override
    protected State decideNextStateAtRuntime() {
        return this.followingNodesMap.get(this.followingNodeId);
    }

}
