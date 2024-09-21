package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // por defecto negar el acceso a todo
public class TestAuthController {

  /*  @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecured() {
        return "hello world secured";
    }

    @GetMapping("/hello-secured2")
    public String helloSecured2() {
        return "hello world secured2";
    }
   */
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello Word - GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    public String helloPost() {
        return "Hello Word - POST";
    }

    @PutMapping("/put")
    public String helloPut(){
        return "Hello Word - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete(){
        return "Hello Word - DELETE";
    }

    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch(){
        return "Hello Word - PATCH";
    }
}
