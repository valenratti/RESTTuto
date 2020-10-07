package payroll.services;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import payroll.domain.Car;
import payroll.domain.Employee;

public interface CarService {

    Employee addCarToEmployee(Employee employee, Car car);
    Employee removeCarFromEmployee(Employee employee, Long id);

}
