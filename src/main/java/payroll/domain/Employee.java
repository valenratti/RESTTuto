package payroll.domain;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Employee {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;
    private String firstName;
    private String lastName;
    private String role;

    @OneToMany()
    private Set<Car> ownedCars;

    public Employee() {}

    Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        if(name == null)
            return this.firstName + " " + this.lastName;
        else return name;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public Set<Car> getOwnedCars(){
        return this.ownedCars;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addCar(Car car){
        car.setOwner(this);
        this.ownedCars.add(car);
    }

    public void removeCar(Car car){
        this.ownedCars.remove(car);
    }

    public boolean hasCar(Car car){
        return ownedCars.contains(car);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", role='" + this.role + '\'' + '}';
    }
}