package io.github.zxbetter.activiti6;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Model;

import java.util.List;

/**
 * 数据查询示例
 *
 * @author zxbetter
 */
public class QueryExample {
    public static void main(String[] args) {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");

        ProcessEngine engine = engineConfiguration.buildProcessEngine();

        // #1 查询示例
        List<Model> list = engine.getRepositoryService().createModelQuery()
                // 每次调用orderByXX()，紧跟着要调用排序规则(生序或降序)，否则后面的orderBy会覆盖前面的orderBy
                .orderByModelId().asc()
                .orderByModelName().desc()
                .list();
        System.out.println(list.size());

        // #2 原生SQL查询示例
        List<Model> list1 = engine.getRepositoryService().createNativeModelQuery()
                // 由于最终调用查询的是MyBatis的SqlSession，因此参数需要使用#{}
                .sql("select * from act_re_model where name_ = #{name}")
                .parameter("name", "process-key")
                .list();

        System.out.println(list1.size());
    }
}
