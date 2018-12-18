/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import in.cdac.hcdc.models.Dictionary;

/**
 *
 * @author Mahima
 */
public interface DictionaryRepository extends MongoRepository<Dictionary, String> {

}
