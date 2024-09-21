package com.app.config;

import com.app.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // SIN ANOTACIÓN
//    @Bean // este metodo que manejaría el security filter chain
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(http -> {
//                    // configurar los endpoint publicos
//                    http.requestMatchers(HttpMethod.GET,"/auth/hello").permitAll();
//                    // configurar los endpoint privados
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("CREATE");
//                    http.requestMatchers(HttpMethod.POST, "auth/post).hasAnyAuthority("CREATE", "READ")";
//                    http.requestMatchers(HttpMethod.POST, "auth/post).hasRole("ADMIN")";
//                    http.requestMatchers(HttpMethod.POST, "auth/post).hasAnyRole("ADMIN", "DEVELOPER")";
//                    // configurar los endpoint NO ESPECIFICADOS
//                    http.anyRequest().denyAll(); // esto indica que si no están especificados arriba rechaza todos (aconsejado)
//                    http.anyRequest().authenticated(); // esto indica que el resto de req  que no están especificados arriba podrán acceder solo estando autenticados, es decir, con su username y password
//                })
//                .build();
//    }

    // CON ANOTACIÓN - @EnableMethodSecurity
    // se quita el authorizeHttpRequest(), donde se especifica las autorizaciones, y se hace en los controller directamente
    @Bean // este metodo que manejaría el security filter chain
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    @Bean // este medoto manejaría el authentication manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) { // esto definiría el Authentication Manager definiendo (en este caso) el Authentication Provider A
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

/*
    @Bean
    public UserDetailsService userDetailsService() { // esto simularía la búsqueda de los datos de la DB para la authentication
        // simulando el pedido de info a una base de datos
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("pepe")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build()
        );
        userDetailsList.add(User.withUsername("tito")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build()
        );
        return new InMemoryUserDetailsManager(userDetailsList);
    }
*/
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // este elemento está deprecado y sólo es conveniente usarlo en desarrollo
        // el elemento new BCryptPasswordEncoder() es el que se usa y es el que encripta las contraseñas, se usa en producción
    }

 */
    @Bean
    public PasswordEncoder passwordEncoder() { // esto se encarga de encriptar las contraseñas y de ver si son correctas
        return new BCryptPasswordEncoder();
    }


/* este médoto se realizó para sacar el código de la contraseña encriptada del valor 1234 */
//    public  static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
//    }

}
