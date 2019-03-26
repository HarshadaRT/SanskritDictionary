/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import in.cdac.hcdc.sanskritdictionary.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import in.cdac.hcdc.sanskritdictionary.models.Pages;
import in.cdac.hcdc.sanskritdictionary.models.Dictionary;
import in.cdac.hcdc.sanskritdictionary.service.DictionaryService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<Pages> createDictionary(@PathVariable String imagePath) {
        File hocrFile = new File(imagePath); //"C:\\Users\\Mahima\\Downloads\\data\\1519_1.hocr");
        List<Pages> pages = new ArrayList<>();
        pages = dictionaryService.parseHocrFile(hocrFile);
        Iterable<Pages> iterable = pages;
        List<Pages> pgs = repository.saveAll(iterable);
        System.out.println("saved in DB " + pgs);
        return pgs;
    }

    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject populateDictionary() throws JsonProcessingException {
        System.out.println("inside populate");
        HashMap<String, String> idAndWord = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject();
        List<Pages> pages = repository.findAll();
        for (Pages pgs : pages) {
            List<Dictionary> dictionary = pgs.getWordDetails();
            for (Dictionary dict : dictionary) {
                idAndWord.put(pgs.getPageId() + "/" + dict.getWordId(), dict.getWord());
            }
        }
        Gson gsonObj = new Gson();
        String json = gsonObj.toJson(idAndWord);
        jsonObject.put("idAndWord", json);
        return jsonObject;
    }
 
    @RequestMapping(value = "/fetchMeaning/{pageId}/{wordId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMeaning(@PathVariable String pageId, @PathVariable String wordId) {
        System.out.println("inside meaning " + pageId + " ======> " + wordId);
        JSONObject jsonObject = new JSONObject();
        List<Pages> page = repository.findByPageIdAndWordDetailsWordId(pageId, wordId);
        for (Pages pg : page) {
            List<Dictionary> d = pg.getWordDetails(); // This should be your question.
            for (Dictionary dict : d) {
                if (dict.getWordId().equalsIgnoreCase(wordId)) {
                    Gson gsonObj = new Gson();
                    String json = gsonObj.toJson(dict);
                    jsonObject.put("meaning", json);
                }
            }
        }
        return jsonObject;
    }
}
