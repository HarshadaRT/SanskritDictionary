/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.sanskritdictionary.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Harshada
 */
@Service
public class FileRenderService {
    
    private String dictionaryLocation="D:\\StudyMaterial\\Sanskrit-Project\\data";
    
    @RequestMapping(value="/")
    public ModelAndView index(){
        System.out.println("********inside base controller *********");
        System.out.println(" loc "+ dictionaryLocation);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        getImageFiles();
        return modelAndView;
    }
    
    public List<String> getFileList(){
        File dir = new File(dictionaryLocation);
        List<String> fileList = new ArrayList<>();
        if(dir.isDirectory()){
          File fileNames[] = dir.listFiles();
            for (File fileName : fileNames) {
                fileList.add(fileName.getName());
            }
        }
        return fileList;
    }
    
    public List<String> getImageFiles(){
        List<String> fileList=getFileList();
        List<String> imageFiles = new ArrayList<>();
        List<String> hocrFiles = new ArrayList<>();
        for (String filename : fileList) {
            String ext = FilenameUtils.getExtension(filename);
            if(ext.equals("jpg")){
                imageFiles.add(filename);
            } else {
                hocrFiles.add(filename);
            }
        }
        System.out.println(" files " + imageFiles);
        System.out.println(" files " + hocrFiles);
        return imageFiles;
    }
    
    
}
