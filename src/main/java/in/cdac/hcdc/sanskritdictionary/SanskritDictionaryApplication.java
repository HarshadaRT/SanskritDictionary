package in.cdac.hcdc.sanskritdictionary;

import in.cdac.hcdc.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import in.cdac.hcdc.models.Dictionary;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableMongoRepositories("in.cdac.hcdc.repositories")
@ComponentScan(basePackages = {"in.cdac.hcdc.sanskritdictionary"})
@EnableAutoConfiguration
public class SanskritDictionaryApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    DictionaryRepository dictRepo;

    public static void main(String[] args) {
        System.out.println("In main");
        SpringApplication.run(SanskritDictionaryApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Dictionary dict = new Dictionary();
        dict.setId(ObjectId.get());
        dict.setWord("अधर्मासूचक");
        dict.setTransliteration("adharmasicaka");
        dict.setPosTag("adj.");
        dict.setMeaning("not disclosing (i. e, conceal—ण्ह) अण्णा १५ अधर्मासूचकश्चापि राजानिष्टमुपेक्षकः 4(1). 106.");

        Dictionary dictionary = dictRepo.save(dict);
        System.out.println("saved in DB " + dictionary);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SanskritDictionaryApplication.class);
    }

}
