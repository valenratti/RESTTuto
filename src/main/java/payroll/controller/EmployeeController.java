package payroll.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import payroll.domain.Car;
import payroll.dto.EmployeeDto;
import payroll.services.CarService;
import payroll.services.EmployeeService;
import payroll.domain.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class EmployeeController {

    final EmployeeService employeeService;

    final CarService carService;

    final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CarService carService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    // Aggregate root

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/carsQty")
    public List<EmployeeDto> allCarsQty() {
        return employeeService.getEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/employees/{page}/{pagesize}")
    public List<Employee> allByPages(@PathVariable int page, @PathVariable int pagesize){
        return employeeService.getEmployeesByPages(page,pagesize);
    }

    //By name
    @GetMapping("/employees/name/{name}")
    List<Employee> byName(@PathVariable String name){
        return employeeService.getEmployees(name);
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

    private EmployeeDto convertToDto(Employee employee){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setId(employee.getId());
        employeeDto.setRole(employee.getRole());
        employeeDto.setCarsQty((long) employee.getOwnedCars().size());
        return employeeDto;
    }

}
