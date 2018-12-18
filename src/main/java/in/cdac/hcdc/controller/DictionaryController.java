/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.cdac.hcdc.controller;

import in.cdac.hcdc.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mahima
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryRepository repository;
}
