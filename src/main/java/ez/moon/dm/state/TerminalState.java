package ez.moon.dm.state;

import ez.moon.DContext;
import ez.moon.IllegalFlowStateException;

/**
 * 终止节点：清理上下文，释放资源
 * 一个上线问只有唯一的终止节点
 * 流程途中所有没有后续节点的节点都将此节点作为后续节点
 */
public class TerminalState extends AbstractState {
    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    public TerminalState(DContext context, String stateNodeId){
        super(context, stateNodeId);
    }

    @Override
    protected void initFollowingNodes() {
        // 此节点么有后续节点， do nothing
    }
    @Override
    public void init() {
        //终止节点没有初始化操作
    }

    @Override
    public void destroy() {
        //终止节点没有什么需要销毁
    }

    //*************************************************************************************************************
    //以下部分，和运行时节点流转相关
    //*************************************************************************************************************
    @Override
    protected State decideNextStateAtRuntime() {
        throw new IllegalFlowStateException("终止节点没有后续节点");
    }

    @Override
    public void onEntry() {
        this.context.destroy();
    }


}
