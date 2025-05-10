package com.security.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @GetMapping("/get")
    public String helloGet() {
        return "Hello from GET";
    }

    @PostMapping("/post")
    public String helloPost() {
        return "Hello from POST";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hello from PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello from  DELETE";
    }

    @PatchMapping("/patch")
    public String helloPatch() {
        return "Hello from PATCH";
    }

}
