package ez.moon.dm.action;

import java.util.Map;

/**
 * 行为，各个状态节点执行的行为
 */
public interface Action {
    Object doAction(Map<String, Object> parameters);
}
