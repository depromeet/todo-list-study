package com.depromeet.todolist.adaptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Health Controller
 * <p>
 * It doesn't matter if you delete it.
 **/
@Controller
public class HealthController {
    @GetMapping({"/", "/health"})
    @ResponseBody
    public String health() {
        return "Health Good";
    }
}
