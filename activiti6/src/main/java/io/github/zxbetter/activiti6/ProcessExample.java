package io.github.zxbetter.activiti6;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流程示例
 *
 * @author zxbetter
 */
public class ProcessExample {
    /**
     * 日志常量
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExample.class);

    private final ProcessEngine engine;

    private final ObjectMapper objectMapper;

    {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
        engineConfiguration.setIdBlockSize(1);
        engine = engineConfiguration.buildProcessEngine();
        ProcessEngines.setInitialized(true);

        objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        ProcessExample example = new ProcessExample();
        example.deploy();
    }

    /**
     * 部署流程
     */
    public void deploy() {
        RepositoryService repositoryService = this.engine.getRepositoryService();
        String modelId = "5001";

        Model modelData = repositoryService.getModel(modelId);
        if (modelData == null) {
            LOGGER.error("部署[{}]: 流程模型不存在!", modelId);
            return;
        }

        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null) {
            LOGGER.error("部署[{}]: 流程模型数据为空，请先设计流程并成功保存，再进行发布!", modelId);
            return;
        }

        JsonNode modelNode;
        try {
            modelNode = this.objectMapper.readTree(bytes);
        } catch (Exception e) {
            LOGGER.error("部署[{}]: 解析流程模型异常!", modelId, e);
            return;
        }

        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addBpmnModel(modelData.getKey() + ".bpmn20.xml", bpmnModel)
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        LOGGER.info("部署[{}]: 成功!", modelId);
    }
}
