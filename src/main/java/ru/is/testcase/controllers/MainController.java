package ru.is.testcase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.is.testcase.model.Test;
import ru.is.testcase.repositories.TestRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MainController {

    @Autowired
    private TestRepository repository;


    @GetMapping("/get")
    public String test(){
        Test test = new Test();
        test.setTestName("test: "+(Math.random()*((100-1)+1))+1);
        repository.save(test);
        return "Готов";
    }

    @GetMapping("/gettest")
    public List<Test> test1(){

        return repository.findAll();
    }
}
