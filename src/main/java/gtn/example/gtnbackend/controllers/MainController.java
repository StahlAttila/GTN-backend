package gtn.example.gtnbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/asd")
  public String helloAsd() {
    return "Hello ASD";
  }

  @GetMapping("/asd-restricted")
  public String helloAsdrest() {
    return "Hello ASD rest";
  }
}
