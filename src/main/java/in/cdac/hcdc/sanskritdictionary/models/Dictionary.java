/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Mahima
 */
@Document(collection = "dictionary")
public class Dictionary {
    
    @Id
    ObjectId id;
    private String pageId;
    private String word;
    private String transliteration;
    private String posTag;
    private String meaning;

    public Dictionary() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId _id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "Dictionary{" + "id=" + id + ", pageId=" + pageId + ", word=" + word + ", transliteration=" + transliteration + ", posTag=" + posTag + ", meaning=" + meaning + '}';
    }

    
}
