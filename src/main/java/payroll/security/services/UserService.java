package payroll.security.services;

import payroll.security.exceptions.UserAlreadyExistException;
import payroll.security.domain.User;
import payroll.security.dto.UserDto;

public interface UserService {

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;

}
