package in.cdac.hcdc.sanskritdictionary;

import in.cdac.hcdc.sanskritdictionary.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import in.cdac.hcdc.sanskritdictionary.models.Pages;
import in.cdac.hcdc.sanskritdictionary.service.DictionaryService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableMongoRepositories("in.cdac.hcdc.sanskritdictionary.repositories")
@ComponentScan(basePackages = {"in.cdac.hcdc.sanskritdictionary"})
@EnableAutoConfiguration
public class SanskritDictionaryApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    DictionaryRepository dictRepo;

    DictionaryService dictionaryService = new DictionaryService();

    public static void main(String[] args) {
        SpringApplication.run(SanskritDictionaryApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        
        // To save data in DB...
        File hocrFile = new File("C:\\Users\\Mahima\\Downloads\\data\\1519_1.hocr");
        List<Pages> pages = new ArrayList<>();
        pages = dictionaryService.parseHocrFile(hocrFile);
        Iterable<Pages> iterable = pages;
        dictRepo.saveAll(iterable);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SanskritDictionaryApplication.class);
    }

}
