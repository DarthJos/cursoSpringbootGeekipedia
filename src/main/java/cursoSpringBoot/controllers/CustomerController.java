package cursoSpringBoot.controllers;

import cursoSpringBoot.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")    // <-- Prefijo que unifica las rutas de los endpoints
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Josimar Reyes", "DarthJos", "contrasena123"),
            new Customer(345, "Giselle Rodriguez", "Givrosan", "clave123"),
            new Customer(567, "Luna Carolina Reyes", "LaLuna", "secreto123"),
            new Customer(890, "Irma Campos", "Chapis", "password123")
    ));

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers() {
        System.out.println("Solicitud ejecutada --> getCustomers()");
        //return customers
        return ResponseEntity.ok(customers);
    }

    //@GetMapping("/{username}")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getCliente (@PathVariable String username) {   //El ? indica que el tipo de dato puede variar, lo que permite devolver un ResponseEntity con con customer y otro con String
        System.out.println("Solicitud ejecutada --> getCliente()");

        for (Customer customer:customers) {
            if (customer.getUsername().equalsIgnoreCase(username)){
                System.out.println("\t* Usuario encontrado: " + customer.getName());
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCliente(@RequestBody Customer customer) {
        System.out.println("Solicitud ejecutada --> postCliente()");
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente agregado correctamente." + customer.getUsername());
    }

    //@PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putCliente(@RequestBody Customer modified_customer) {
        System.out.println("Solicitud ejecutada --> putCliente()");

        for (Customer customer: customers) {
            if (customer.getID() == modified_customer.getID()) {
                System.out.println("* Usuario encontrado... actualizando...");
                customer.setName(modified_customer.getName());
                customer.setUsername(modified_customer.getUsername());
                customer.setPassword(modified_customer.getPassword());

                return ResponseEntity.ok("Cliente modificado satisfactoriamente: "+customer.getID());
            }
        }
        System.out.println("* Cliente no encontrado...");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado: " + modified_customer.getID());
    }

    //@DeleteMapping("/{idToDelete}")
    @RequestMapping(value = "/{idToDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCliente(@PathVariable int idToDelete) {
        System.out.println("Solicitud ejecutada --> putCliente()");

        for (Customer customer: customers) {
            if (customer.getID() == idToDelete) {
                customers.remove(customer);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado satisfactoriamente: " + idToDelete);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado: " + idToDelete);
    }

    //@PatchMapping
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchCliente(@RequestBody Customer customerToPatch) {
        System.out.println("Solicitud ejecutada --> patchCliente()");

        for (Customer customer: customers) {
            if (customer.getID() == customerToPatch.getID()) {
                System.out.println("* Usuario encontrado... actualizando...");
                if (customerToPatch.getName() != null){
                    customer.setName(customerToPatch.getName());
                }
                if (customerToPatch.getUsername() != null){
                    customer.setUsername((customerToPatch.getUsername()));
                }
                if (customerToPatch.getPassword() != null){
                    customer.setPassword((customerToPatch.getPassword()));
                }
                return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + customer.getID());
            }
        }
        System.out.println("* No se encontró al usuario solicitado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customerToPatch.getID());
    }
}
