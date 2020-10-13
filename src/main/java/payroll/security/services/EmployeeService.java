package payroll.security.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import payroll.domain.Car;
import payroll.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(); // /employees
    List<Employee> getEmployees(String name);
    List<Employee> getEmployeesByPages(int page, int size);
    Employee createEmployee(Employee newEmployee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee newEmployee, Long id);
    void deleteEmployee(Long id);
    boolean containsCar(Employee employee, Car car);
}
