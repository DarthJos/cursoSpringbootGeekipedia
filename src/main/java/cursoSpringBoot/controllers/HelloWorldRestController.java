package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping({"/holaMundo", "/helloWorld", "/hw"})
    public String helloWorld() {
        System.out.println("helloWorld() --> Solicitud ejecutada!");
        return "Hola Mundo!";
    }
}
