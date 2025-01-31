package cursoSpringBoot.controllers;

import cursoSpringBoot.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return customers;
    }
}
