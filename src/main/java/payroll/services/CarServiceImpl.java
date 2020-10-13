package payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.config.CarNotFoundException;
import payroll.config.EmployeeModelAssembler;
import payroll.domain.Car;
import payroll.domain.Employee;
import payroll.repositories.CarRepository;
import payroll.security.services.EmployeeService;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    final EmployeeService employeeService;

    private final CarRepository repository;
    private final EmployeeModelAssembler assembler;

    @Autowired
    CarServiceImpl(CarRepository repository, EmployeeModelAssembler assembler, EmployeeService employeeService) {
        this.repository = repository;
        this.assembler = assembler;
        this.employeeService = employeeService;
    }

    @Override
    public Employee addCarToEmployee(Employee employee, Car car) {
        employee.addCar(car);
        repository.save(car);
        return employee;
    }

    @Override
    public Employee removeCarFromEmployee(Employee employee, Long id) {
        Optional<Car> toDelete = repository.findByCarID(id);

        if (toDelete.isPresent()) {
            Car car = toDelete.get();
            employee.removeCar(car);
            repository.deleteById(id);
            return employee;
        } else throw new CarNotFoundException(id);
    }

}
