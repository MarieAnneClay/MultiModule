//package security;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import model.User;
//import service.UserService;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = (String) authentication.getCredentials();
//
//        User user = (User) userService.loadUserByUsername(username);
//
//        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
//            throw new BadCredentialsException("Username not found.");
//        }
//
//        if (!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Wrong password.");
//        }
//
//        Collection<? extends GrantedAuthority> authorities = ((Authentication) user).getAuthorities();
//
//        return new UsernamePasswordAuthenticationToken(user, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> arg0) {
//        return true;
//    }
//
//}
