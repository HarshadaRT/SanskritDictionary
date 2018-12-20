package in.cdac.hcdc.sanskritdictionary;

import in.cdac.hcdc.sanskritdictionary.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import in.cdac.hcdc.sanskritdictionary.models.Dictionary;
import in.cdac.hcdc.sanskritdictionary.service.DictionaryService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableMongoRepositories("in.cdac.hcdc.sanskritdictionary.repositories")
@ComponentScan(basePackages = {"in.cdac.hcdc.sanskritdictionary"})
@EnableAutoConfiguration
public class SanskritDictionaryApplication extends SpringBootServletInitializer {//implements CommandLineRunner {

    @Autowired
    DictionaryRepository dictRepo;

    DictionaryService dictionaryService = new DictionaryService();

    public static void main(String[] args) {
        SpringApplication.run(SanskritDictionaryApplication.class, args);
    }

//    @Override
//    public void run(String... strings) throws Exception {
//        File hocrFile = new File("C:\\Users\\Mahima\\Downloads\\data\\1519_1.hocr");
//        List<Dictionary> dict = new ArrayList<>();
//        HashMap<ObjectId, String> idAndWord = new HashMap<ObjectId, String>();
//        
//        dict = dictionaryService.parseHocrFile(hocrFile);
//        Iterable<Dictionary> iterable = dict;
//        
////        dictRepo.saveAll(iterable);
//        List<Dictionary> dictionary = dictRepo.findAll();
//        for(Dictionary dct : dictionary){
//            idAndWord.put(dct.getId(), dct.getWord());
//        }
//        Dictionary odct = dictRepo.findByWord("ञधर्मानुबन्ध");
//        System.out.println("dict "+odct.toString());
//
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SanskritDictionaryApplication.class);
    }

}
