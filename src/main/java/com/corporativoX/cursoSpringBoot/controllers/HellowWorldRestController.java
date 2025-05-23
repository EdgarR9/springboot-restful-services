package com.corporativoX.cursoSpringBoot.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldRestController {

    @GetMapping("/hello") // la ruta hace referencia a lo que va a retornar el metodo
    public String hellowWorld(){
        System.out.println("Solicitud ejecutada");
        return "Hellow, World";
    }

}
