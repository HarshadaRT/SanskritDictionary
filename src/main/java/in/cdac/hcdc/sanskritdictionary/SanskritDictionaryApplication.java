package in.cdac.hcdc.sanskritdictionary;

import in.cdac.hcdc.sanskritdictionary.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import in.cdac.hcdc.sanskritdictionary.models.Dictionary;
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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
        //Uncomment this to save data in mongodb
//        getDictionaryById("1519_1", "046ead24-5d65-4b7c-8027-2910ce9b5ab6");
//        File hocrFile = new File("C:\\Users\\Mahima\\Downloads\\data\\1519_1.hocr");
//        List<Pages> pages = new ArrayList<>();
//        pages = dictionaryService.parseHocrFile(hocrFile);
//        Iterable<Pages> iterable = pages;
//        List<Pages> pgs = dictRepo.saveAll(iterable);
//        System.out.println("saved in DB " + pgs);
        
        
         // Uncomment this to test findByword functionality
//        for(Dictionary dct : dictionary){
//            idAndWord.put(dct.getId(), dct.getWord());
//        }
//        Dictionary odct = dictRepo.findByWord("ञधर्मानुबन्ध");
//        System.out.println("dict "+odct.toString());
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private MongoOperations mongoOperation;

    public Dictionary getDictionaryById(String pageId, String wordId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is("1519_1"));
////        query.addCriteria(Criteria.where("_id").is("0"));
//        query.addCriteria(Criteria.where("wordDetails").elemMatch(Criteria.where("wordId").is("ba4a9a41-5902-479e-a498-3d694bb54faf")));
//        System.out.println("mongoTemplate ==> " + mongoTemplate);
//        List<Pages> find = mongoTemplate.find(query, Pages.class);

//        BasicQuery query1 = new BasicQuery("{'_id':'1519_1'},{'_id':'0', wordDetails : {$elemMatch: {'wordId': '083ab528-8b00-46fe-8b3b-8c8c64f3b66f'}}})");
//        Pages page = mongoOperation.findOne(query1, Pages.class);
        
//        List<Pages> pgs = repository.saveAll(iterable);

//        System.out.println("finding... " + page);
        return null;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SanskritDictionaryApplication.class);
    }
}
