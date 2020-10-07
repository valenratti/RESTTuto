package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Aggregate root

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {
        return employeeService.getEmployees();
    }

    //By name
    @GetMapping("/employees/name/{name}")
    CollectionModel<EntityModel<Employee>> byName(@PathVariable String name){
        return employeeService.getEmployeesByName(name);
    }

    @PostMapping("/employees") //ESTO VER
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    // Single item


    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateEmployee(newEmployee, id);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}
