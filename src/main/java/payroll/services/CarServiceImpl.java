package payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import payroll.config.CarNotFoundException;
import payroll.config.EmployeeModelAssembler;
import payroll.domain.Car;
import payroll.domain.Employee;
import payroll.repositories.CarRepository;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CarService carService;

    private final CarRepository repository;
    private final EmployeeModelAssembler assembler;


    CarServiceImpl(CarRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<EntityModel<Employee>> addCarToEmployee(Employee employee, Car car) {
        employee.addCar(car);
        repository.save(car);
        EntityModel<Employee> entityModel = assembler.toModel(employee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @Override
    public ResponseEntity<EntityModel<Employee>> removeCarFromEmployee(Employee employee, Long id) {
        Optional<Car> toDelete = repository.findByCarID(id);

        if (toDelete.isPresent()) {
            Car car = toDelete.get();
            employee.removeCar(car);
            repository.deleteById(id);

            EntityModel<Employee> entityModel = assembler.toModel(employee);
            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        } else throw new CarNotFoundException(id);
    }

}
