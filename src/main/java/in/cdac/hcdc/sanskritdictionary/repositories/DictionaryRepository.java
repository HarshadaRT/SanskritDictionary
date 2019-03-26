/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import in.cdac.hcdc.sanskritdictionary.models.Pages;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Mahima
 */
public interface DictionaryRepository extends MongoRepository<Pages, String> {
//   Pages findByWord(String word);

     @Query(value = "{ 'pageId' : ?0, 'wordDetails.wordId' : ?1 }", fields = "{ 'wordDetails.wordId' : 1, 'wordDetails.transliteration' : 2, 'wordDetails.posTag' : 3, 'wordDetails.meaning' : 4 }")
     List<Pages> findByPageIdAndWordDetailsWordId(String pageId, String wordId);
}