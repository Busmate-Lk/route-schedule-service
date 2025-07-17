package com.busmate.routeschedule.controller;

import com.busmate.routeschedule.security.UserPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "Debug", description = "APIs for debugging and development purposes")
public class DebugController {

    @GetMapping("/whoami")
    public String whoAmI() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserPrincipal user) {
            System.out.println("👤 Username: " + user.getUsername());
            System.out.println("🛡️ Roles: " + user.getAuthorities());

            return "Hello, " + user.getUsername() + " with roles " + user.getAuthorities();
        }

        return "Anonymous or invalid user";
    }
}
