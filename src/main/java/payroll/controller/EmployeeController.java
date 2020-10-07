package payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.domain.Car;
import payroll.services.CarService;
import payroll.services.EmployeeService;
import payroll.domain.Employee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CarService carService;

    // Aggregate root

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
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
    public EntityModel<Employee> one(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateEmployee(newEmployee, id);
    }

    @PutMapping("/employees/{id}/cars")
    ResponseEntity<?> addCar(@RequestBody Car car, @PathVariable Long id){
        return carService.addCarToEmployee(employeeService.getEmployeeById(id).getContent(),car);
    }

    @DeleteMapping("/employees/{id}/cars")
    ResponseEntity<?> removeCar(@RequestBody Car car, @PathVariable Long id){
        return carService.removeCarFromEmployee(employeeService.getEmployeeById(id).getContent(),id);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}
