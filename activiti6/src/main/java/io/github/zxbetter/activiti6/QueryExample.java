package io.github.zxbetter.activiti6;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * 数据查询示例
 *
 * @author zxbetter
 */
public class QueryExample {
    public static void main(String[] args) {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");

        // engineConfiguration.setHistoryLevel(HistoryLevel.ACTIVITY);
        ProcessEngine engine = engineConfiguration.buildProcessEngine();

        System.out.println(engine.getName());
    }
}
