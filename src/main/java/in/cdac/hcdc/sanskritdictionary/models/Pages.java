/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Mahima
 */
@Document(collection = "dictionary")
public class Pages {
    
    @Id
    private String pageId;
    private List<Dictionary> wordDetails;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<Dictionary> getWordDetails() {
        return wordDetails;
    }

    public void setWordDetails(List<Dictionary> wordDetails) {
        this.wordDetails = wordDetails;
    }

    @Override
    public String toString() {
        return "Pages{" + "pageId=" + pageId + ", wordDetails=" + wordDetails + '}';
    }
    
    
}
