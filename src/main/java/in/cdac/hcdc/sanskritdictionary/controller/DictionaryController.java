/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.controller;

import in.cdac.hcdc.sanskritdictionary.repositories.DictionaryRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import in.cdac.hcdc.sanskritdictionary.models.Dictionary;
import in.cdac.hcdc.sanskritdictionary.service.DictionaryService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mahima
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryRepository repository;

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(value = "/{imagePath}", method = RequestMethod.POST)
    public List<Dictionary> createDictionary(@PathVariable String imagePath) {//@Valid @RequestBody Dictionary dictionary) {
        File hocrFile = new File(imagePath);//"C:\\Users\\Mahima\\Downloads\\data\\1519_1.hocr");
        List<Dictionary> dict = new ArrayList<>();
        dict = dictionaryService.parseHocrFile(hocrFile);
        Iterable<Dictionary> iterable = dict;
        List<Dictionary> dictionary = repository.saveAll(iterable);
        System.out.println("saved in DB " + dictionary);
        return dictionary;
    }

    @RequestMapping(value = "/populate", method = RequestMethod.POST)
    public HashMap<ObjectId, String> populateDictionary() {//@Valid @RequestBody Dictionary dictionary) {
        ModelAndView mav = new ModelAndView();
        HashMap<ObjectId, String> idAndWord = new HashMap<ObjectId, String>();
        List<Dictionary> dictionary = repository.findAll();
        for (Dictionary dct : dictionary) {
            idAndWord.put(dct.getId(), dct.getWord());
        }
        mav.addObject("idAndWord", idAndWord);
        mav.setViewName("index");
        return idAndWord;
    }

    @RequestMapping(value = "/fetchMeaning", method = RequestMethod.POST)
    public Dictionary getMeaning() {//@Valid @RequestBody Dictionary dictionary) {
        Dictionary odct = repository.findById("5c1a1a2a4c3fca2114f05c46").orElse(null);
        return odct;
    }
}
