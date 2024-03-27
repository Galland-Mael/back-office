package project.backoffice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    // All web routes are redirected to the root route to be handled by the frontend
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("forward:/");
        registry.addViewController("/logout").setViewName("forward:/");
        registry.addViewController("/push").setViewName("forward:/");
        registry.addViewController("/products").setViewName("forward:/");
        registry.addViewController("/reset-password").setViewName("forward:/");

    }
}
