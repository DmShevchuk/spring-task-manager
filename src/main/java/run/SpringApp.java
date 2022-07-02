package run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Основной класс, точка входа в программу
 * */
@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "services", "utils", "commands", "tasks"})
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}
