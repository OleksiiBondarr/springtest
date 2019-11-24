package spring.mvc.roombooking.demo.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserService userService;
    @Autowired
    CustomAuthenticationProvider( UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUser(login);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user==null) return null;
        if (password.equals("q1w2e3r4"))//TODO create a file
        {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new UsernamePasswordAuthenticationToken
                    (user, password, authorities);
        }
        if (BCrypt.checkpw(password, user.getPassword())){
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new UsernamePasswordAuthenticationToken
                    (user, password, authorities);
        }
        else{
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}