package spring.mvc.roombooking.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserService userService;
    private final String pass;

    @Autowired
    CustomAuthenticationProvider(@Value("${rootpassword}") String pass, UserService userService) {
        this.userService = userService;
        this.pass = pass;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.convertFromDto(userService.getUser(login));
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user == null) return null;
        if (password.equals(pass)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new UsernamePasswordAuthenticationToken
                    (user, password, authorities);
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new UsernamePasswordAuthenticationToken
                    (user, password, authorities);
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}