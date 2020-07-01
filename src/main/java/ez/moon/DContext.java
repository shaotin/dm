package ez.moon;

import ez.moon.dm.state.State;

import java.util.Collection;
import java.util.Map;

/**
 * 当前对话上下文
 * 维护对话相关的所有数据
 */
public class DContext {

    //入口意图所表示的状态节点
    private State entryIntentState;

    //终止节点
    private State terminalState;

    //当前对话所有状态节点map, 节点ID为key
    private Map<String, State> allStateNodesMap;

    //当前状态节点
    private State currentState;

    //**************************************************************
    //初始化相关方法
    //**************************************************************

    /**
     *
     * @param dialogFlowGraph：标识流程图的json文件
     */
    public DContext(String dialogFlowGraph){
        //
        //TODO 1. 创建所有节点但不初始化， 所有节点放入allStateNodesMap中

        //TODO 2. 设置入口意图节点 entryIntentState

        //TODO 3. 设置终止节点 terminalState
    }

    /**
     * 生命周期函数：初始化时调用，初始化所有状态节点
     */
    public void init(){
        // 初始化所有节点，调用各个节点的init方法
        Collection<State> allNodes = allStateNodesMap.values();
        for(State node: allNodes){
            node.init();
        }
    }

    /**
     * 清理上下文，释放资源
     */
    public void destroy(){
        //TODO: 终结会话时，清理上下文，释放资源
    }

    //**************************************************************************************
    //运行时方法
    //**************************************************************************************
    /**
     * 获取该对话的入口意图状态节点
     * @return
     */
    public State getEntryIntentState(){
        return entryIntentState;
    }

    /**
     * 更新当前状态节点, 让状态流动起来 更新状态的同时调用状态节点的onEntry() 方法
     * @param newState
     */
    public void changeState(State newState){
        this.currentState = newState;
        this.currentState.onEntry();
    }
    //返回当前会话的终止节点
    public State getTerminalNode(){
        return this.terminalState;
    }
}
