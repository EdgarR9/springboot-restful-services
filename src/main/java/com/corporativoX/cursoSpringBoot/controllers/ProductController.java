package com.corporativoX.cursoSpringBoot.controllers;


import Service.ProductService;
import Service.ProductsServiceImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    // Conecta el controlador con el servicio esta instancia de clase

    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products = productsService.getProducts();

        return ResponseEntity.ok(products);
    }

}
