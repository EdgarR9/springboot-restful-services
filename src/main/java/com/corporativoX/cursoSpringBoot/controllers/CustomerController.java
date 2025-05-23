package com.corporativoX.cursoSpringBoot.controllers;
import domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yaml.snakeyaml.events.Event;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Rest controller brinda acceso a los endpoints
 * @RequestMapping("/clientes") resuelve la unificacion de rutas
 */
@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
       new Customer(123, "Gerardo lopez", "gerardo1", "contrasena123"),
       new Customer(456, "Alejandra Garcia", "alegarcia", "clave456"),
       new Customer(789, "Laura Sanchez", "lauras", "secreto789"),
       new Customer(234, "Carlos Martinez", "carlosm", "password234")
    ));

    /**
     * Nos muestra la lista completa de los clientes
     * @return
     */
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer(){


        return ResponseEntity.ok(customers);

    }

    /**
     * Nos muestra un cliente en específico
     * @param username
     * @return
     */

    //@RequestMapping(value = "/{username}", method = RequestMethod.GET) @RequestMapping a nivel metodo

    @GetMapping("/{username}")
    public ResponseEntity<?> gerCliente(@PathVariable String username){
        for (Customer c : customers){
            if (c.getUsername().equals(username)){
                return ResponseEntity.ok(c);

            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cliente no encontrado con username: " + username);

    }

    /**
     * Agrega clientes
     * @Requestbody se encarga de obtener los valores que estamos mandando en formato json
     * y decirle a Spring Boot que haga la conversion de ese formato json y lo almacene en la Lista List
     * @param customer
     * @return
     */
    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);
        //ServletUriComponentsBuilder indica la construcción del URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        //return ResponseEntity.created(location).build();
        return ResponseEntity.created(location).body(customer);
    }

    /**
     * Actualiza clientes ingresando todos los campos especificados en List<Customer> en formato json
     * @param customer
     * @return
     */
    @PutMapping
    public ResponseEntity <?> putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.ok("Cliente: " + customer.getID() + " actualizado exitosamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente: " + customer.getID() + " no encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for(Customer c : customers){
            if(c.getID() == id){
                customers.remove(c);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado satisfactoriamente " + id);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el id " + id);
    }


    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (c.getID() == customer.getID()){


                if (customer.getName() != null){
                    c.setName(customer.getName());
                }
                if (customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }

                return ResponseEntity.ok("Cliente modificado con id " + customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el id " + customer.getID());
    }
}


























