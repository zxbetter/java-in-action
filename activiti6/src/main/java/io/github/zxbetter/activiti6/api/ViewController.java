package io.github.zxbetter.activiti6.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图控制器
 *
 * @author zxbetter
 */
@Controller
public class ViewController {

    @GetMapping("/modeler")
    public String modeler() {
        return "/modeler";
    }
}
