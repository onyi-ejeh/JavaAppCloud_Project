package ng.victoriaejeh.javaappawscloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class JavaAppAwsCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAppAwsCloudApplication.class, args);
    }

}
