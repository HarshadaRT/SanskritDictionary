package in.cdac.hcdc.sanskritdictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"in.cdac.hcdc.sanskritdictionary"})
@EnableAutoConfiguration
public class SanskritDictionaryApplication extends SpringBootServletInitializer {
    
        @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SanskritDictionaryApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SanskritDictionaryApplication.class, args);
	}

}

