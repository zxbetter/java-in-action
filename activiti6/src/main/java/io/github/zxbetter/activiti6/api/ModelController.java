package io.github.zxbetter.activiti6.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * 模型控制器
 *
 * @author zxbetter
 */
@RestController
@RequestMapping("/models")
public class ModelController {

    /**
     * 日志常量
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Model> createModel() {
        Model model = this.repositoryService.newModel();

        String modelKey = UUID.randomUUID().toString();
        String modelName = String.format("name[%s]", modelKey);

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "");
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        model.setName(modelName);
        model.setKey(modelKey);
        model.setMetaInfo(modelNode.toString());

        this.repositoryService.saveModel(model);

        // model editor source
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        try {
            repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOGGER.error("创建模型时完善ModelEditorSource服务异常:", e);
        }

        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAll() {
        return ResponseEntity.ok(this.repositoryService.createModelQuery().list());
    }
}
