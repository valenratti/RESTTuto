package payroll.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import payroll.security.exceptions.UserAlreadyExistException;
import payroll.security.domain.User;
import payroll.security.dto.UserDto;
import payroll.security.repositories.RoleRepository;
import payroll.security.repositories.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (checkIfUserExist(userDto.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userDto.getEmail());
        }
        return userRepository.save(dtoToEntity(userDto));
    }

    private boolean checkIfUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User dtoToEntity(UserDto dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setUsing2FA(dto.isUsing2FA());
        user.setEnabled(true);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return user;
    }

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
