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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class EmployeeController {

    final EmployeeService employeeService;

    final CarService carService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CarService carService) {
        this.employeeService = employeeService;
        this.carService = carService;
    }

    // Aggregate root

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeService.getEmployees();
    }

    //By name
    @GetMapping("/employees/name/{name}")
    List<Employee> byName(@PathVariable String name){
        return employeeService.getEmployeesByName(name);
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    // Single item


    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateEmployee(newEmployee, id);
    }

    @PutMapping("/employees/{id}/cars")
    Employee addCar(@RequestBody Car car, @PathVariable Long id){
        return carService.addCarToEmployee(employeeService.getEmployeeById(id),car);
    }

    @DeleteMapping("/employees/{id}/cars/{carid}")
    Employee removeCar(@RequestBody Car car, @PathVariable Long id, @PathVariable Long carid){
        return carService.removeCarFromEmployee(employeeService.getEmployeeById(id),carid);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
