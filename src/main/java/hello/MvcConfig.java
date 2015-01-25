package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("top");
        registry.addViewController("/top").setViewName("top");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/page1").setViewName("page1");
        registry.addViewController("/page2").setViewName("page2");
    }

}