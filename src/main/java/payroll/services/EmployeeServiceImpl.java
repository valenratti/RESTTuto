package payroll.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import payroll.config.EmployeeModelAssembler;
import payroll.config.EmployeeNotFoundException;
import payroll.controller.EmployeeController;
import payroll.domain.Car;
import payroll.domain.Employee;
import payroll.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    EmployeeServiceImpl(EmployeeRepository repository, EmployeeModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean containsCar(Employee employee, Car car) {
        return employee.hasCar(car);
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll().stream().collect(Collectors.toList());

    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return repository.findAllByFirstName(name);
    }

    @Override
    public Employee createEmployee(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employee;
    }

    @Override
    public Employee updateEmployee(Employee newEmployee, Long id) {
        Employee updatedEmployee = repository.findById(id) //
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        return updatedEmployee;
    }


}
