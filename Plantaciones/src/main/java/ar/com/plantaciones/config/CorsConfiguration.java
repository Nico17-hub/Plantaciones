package ar.com.plantaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//establece una "conexion" entre el consumidor o la app con el proveedor
@Configuration
public class CorsConfiguration //Nunca cambia copiar y pegar
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                    registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("*");

            }

        };
    }
}
