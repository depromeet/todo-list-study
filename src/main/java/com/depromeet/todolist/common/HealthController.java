package com.depromeet.todolist.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Health Controller
 * <p>
 * It doesn't matter if you delete it.
 **/

@Controller
public class HealthController {
    @GetMapping({"/", "/health"})
    public String health() {
        return "Health Good";
    }
}
