package payroll.dto;

import lombok.Data;


public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private Long carsQty;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public Long getCarsQty() {
        return carsQty;
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

    public void setCarsQty(Long carsQty) {
        this.carsQty = carsQty;
    }
}
