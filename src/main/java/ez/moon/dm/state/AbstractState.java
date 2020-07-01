package ez.moon.dm.state;

import ez.moon.DContext;
import ez.moon.event.Event;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractState implements State{

    //*************************************************************************************************************
    //以下部分，和节点创建相关
    //*************************************************************************************************************
    //状态节点ID
    protected String stateNodeId;

    //节点所属对话上下文
    protected DContext context;

    //当前节点后续节点map, 节点ID为key
    protected Map<String, State> followingNodesMap;

    /**
     * 创建节点：设置ID, 设置上下文对象
     * 但不初始化，初始化有上下文生命周期统一管理
     * @param context
     * @param stateNodeId
     */
    public AbstractState(DContext context, String stateNodeId){
        this.stateNodeId = stateNodeId;
        this.context = context;
        this.followingNodesMap = new HashMap<String, State>();
    }

    //初始化后续节点
    protected abstract void initFollowingNodes();

    @Override
    public void destroy() {
        // 销毁 操作 子节点中实现
    }
    //*************************************************************************************************************
    //以下部分，和运行时状态流转相关
    //*************************************************************************************************************


    @Override
    public String getId() {
        return this.stateNodeId;
    }

    @Override
    public void onEntry() {

    }

    @Override
    public void onEvent(Event event) {
    }

    /**
     *  运行时动态获取下一个节点，不同的节点类型有不同的获取策略
     * @return
     */
    protected abstract State decideNextStateAtRuntime();

}
