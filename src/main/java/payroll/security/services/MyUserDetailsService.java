package payroll.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import payroll.security.domain.User;
import payroll.security.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final User user = userRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException("No user found with username: " + email));
        Collection<GrantedAuthority> authorities = user.getRoles().stream().map( r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
        //return new MyUserDetails(user.getEmail(),user.getPassword(),authorities);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
}
