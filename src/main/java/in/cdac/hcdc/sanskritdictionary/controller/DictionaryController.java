/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
import javax.json.JsonObject;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject populateDictionary() throws JsonProcessingException {//@Valid @RequestBody Dictionary dictionary) {
        System.out.println("inside populate");
        JSONObject jsonObject = new JSONObject();
        List<Dictionary> dictionary = repository.findAll();
        List js = new ArrayList();
        for (Dictionary dic : dictionary) {
            js.add(dic.getWord());
        }

        Gson gsonObj = new Gson();
        String json = gsonObj.toJson(js);

        jsonObject.put("idAndWord", json);
        System.out.println("check 1 " + json);
        return jsonObject;
    }

    @RequestMapping(value = "/fetchMeaning/{word}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMeaning(@PathVariable String word) {//@Valid @RequestBody Dictionary dictionary) {
        System.out.println("inside meaning "+word);
        JSONObject jsonObject = new JSONObject();
        Dictionary odct = repository.findByWord(word);
        Gson gsonObj = new Gson();
        System.out.println("dic "+odct);
        String json = gsonObj.toJson(odct);
        jsonObject.put("meaning", json);
        System.out.println("json "+json);
//        Dictionary odct = repository.findById("5c1a1a2a4c3fca2114f05c46").orElse(null);
        return jsonObject;
    }
}
