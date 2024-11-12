package fei.uv.mx.deliveryapp.Security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Aquí deberías obtener el usuario desde tu base de datos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            // Contraseña encriptada (password en texto plano es "password")
            String encodedPassword = passwordEncoder.encode("password");
            return User.withUsername("user")
                    .password(encodedPassword)
                    .authorities(new ArrayList<>())
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
        }
    }
}