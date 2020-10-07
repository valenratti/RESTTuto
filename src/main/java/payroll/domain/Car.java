package payroll.domain;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long carID;

    private String model;
    private String color;
    private String year;

    @ManyToOne
    private Employee owner;

    public void setOwner(Employee employee){
        this.owner = employee;
    }

    public Long getCarID() {
        return carID;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getYear() {
        return year;
    }

}
