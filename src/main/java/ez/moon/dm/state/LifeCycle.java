package ez.moon.dm.state;

/**
 * 生命周期接口, 由上下文统一管理
 */
public interface LifeCycle {

    /**
     * 初始化接口，由上下文统一管理
     */
    void init();

    /**
     * 销毁接口
     */
    void destroy();
}
