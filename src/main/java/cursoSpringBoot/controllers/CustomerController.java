package cursoSpringBoot.controllers;

import cursoSpringBoot.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Josimar Reyes", "DarthJos", "contrasena123"),
            new Customer(123, "Giselle Rodriguez", "Givrosan", "clave123"),
            new Customer(123, "Luna Carolina Reyes", "LaLuna", "secreto123"),
            new Customer(123, "Irma Campos", "Chapis", "password123")
    ));

    @GetMapping("/clientes")
    public List<Customer> getCustomers() {
        System.out.println("Solicitud ejecutada --> getCustomers()");
        return customers;
    }

    @GetMapping("/clientes/{username}")
    public Customer getCliente(@PathVariable String username) {
        System.out.println("Solicitud ejecutada --> getCliente()");
        for (Customer customer:customers) {
            if (customer.getUsername().equalsIgnoreCase(username)){
                System.out.println("\t* Usuario encontrado: " + customer.getName());
                return customer;
            }
        }
        return null;
    }

    @PostMapping("/clientes")
    public Customer postCliente(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }
}
