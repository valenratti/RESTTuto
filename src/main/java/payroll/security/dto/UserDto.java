package payroll.security.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDto implements Serializable {

    @javax.validation.constraints.NotNull
    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @javax.validation.constraints.NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty(message = "Email name can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password can not be empty")
    @javax.validation.constraints.NotNull
    @NotEmpty
    private String password;

    @javax.validation.constraints.NotNull
    private String matchingPassword;

    private boolean isUsing2FA;

    private Integer role;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean using2FA) {
        isUsing2FA = using2FA;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=")
                .append(firstName)
                .append(", lastName=")
                .append(lastName)
                .append(", email=")
                .append(email)
                .append(", isUsing2FA=")
                .append(isUsing2FA)
                .append(", role=")
                .append(role).append("]");
        return builder.toString();
    }
}
