package io.github.zxbetter.activiti6.api;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模型控制器
 *
 * @author zxbetter
 */
@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private RepositoryService repositoryService;

    @PostMapping
    public ResponseEntity<Model> createModel() {
        Model model = this.repositoryService.newModel();
        model.setKey("process-key");
        model.setName("process-name");
        this.repositoryService.saveModel(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAll() {
        return ResponseEntity.ok(this.repositoryService.createModelQuery().list());
    }
}
