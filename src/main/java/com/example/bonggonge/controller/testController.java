package com.example.bonggonge.controller;


import com.example.bonggonge.domain.entity.EmployeeEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping(value = "/template")
public class testController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MongoTemplate mongoTemplate;

    public testController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllUsers() {
        logger.info("Getting all Employees.");

        // Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
        String url = "https://www.ted.com/talks?language=ko&page=25"; //크롤링할 url지정
        Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
        Elements element = doc.select(".ga-link");
        String href = element.attr("href");

        System.out.println("href = " +href);

        //Iterator을 사용하여 하나씩 값 가져오기
        Iterator<Element> ie1 = element.select("strong.rank").iterator();

        while (ie1.hasNext()) {
            System.out.println(ie1.next().text());
        }

        System.out.println("============================================================");
        return "0";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeEntity getEmployee(@PathVariable long id) {
        logger.info("Getting Employee with ID: {}.", id);
        EmployeeEntity employeeModel = mongoTemplate.findById(id, EmployeeEntity.class);
        return employeeModel;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public EmployeeEntity add(@RequestBody EmployeeEntity employeeModel) {
        logger.info("Saving Employee.");
        return mongoTemplate.save(employeeModel);
    }
}