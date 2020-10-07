package payroll.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import payroll.domain.Car;
import payroll.domain.Employee;

public interface EmployeeService {

    public abstract CollectionModel<EntityModel<Employee>> getEmployees(); // /employees
    public abstract CollectionModel<EntityModel<Employee>> getEmployeesByName(String name);
    public abstract ResponseEntity<?> createEmployee(Employee newEmployee);
    public abstract EntityModel<Employee> getEmployeeById(Long id);
    public abstract ResponseEntity<?> updateEmployee(Employee newEmployee, Long id);
    public abstract ResponseEntity<?> deleteEmployee(Long id);
    public boolean containsCar(Employee employee, Car car);
}
