package ez.moon.dm.state;

import ez.moon.event.Event;

/**
 * 状态节点
 */
public interface State extends LifeCycle{

    /**
     * 节点被设置为当前状态时调用
     */
    void onEntry();

    /**
     * 事件发生时被调用，用于驱动状态流转
     */
    void onEvent(Event event);

    String getId();

}
