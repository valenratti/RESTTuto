package payroll.services;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import payroll.domain.Car;
import payroll.domain.Employee;

public interface CarService {

    public abstract ResponseEntity<EntityModel<Employee>> addCarToEmployee(Employee employee, Car car);
    public abstract ResponseEntity<EntityModel<Employee>> removeCarFromEmployee(Employee employee, Long id);

}
