package com.training.server.main.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.training.server.main.model.SomeBean;
import com.training.server.main.model.SomeBean1;
import com.training.server.main.model.SomeBean2;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @RequestMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("Amit", "9999999999", "39000");
    }

    //returning a list of SomeBeans as response
    @RequestMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans() {
        return Arrays.asList(new SomeBean("Saurabh", "8888888888", "20000"), new SomeBean("Devesh", "1111111111", "34000"));
    }

    //returning a list of SomeBeans as response
    @RequestMapping("/filtering-list1")
    public List<SomeBean1> retrieveListOfSomeBeans1() {
        return Arrays.asList(new SomeBean1("Saurabh1", "8888888888", "20000"), new SomeBean1("Devesh1", "1111111111", "34000"));
    }

    //returning a single bean as response
//values to send name and salary
    @RequestMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveSomeBeanDynamic() {
        SomeBean2 someBean2 = new SomeBean2("Amit", "9999999999", "39000");
        //invoking static method filterOutAllExcept()
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "salary");
        //creating filter using FilterProvider class
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        //constructor of MappingJacksonValue class  that has bean as constructor argument
        MappingJacksonValue mapping = new MappingJacksonValue(someBean2);
        //configuring filters
        mapping.setFilters(filters);
        return mapping;
    }

    //returning a list of SomeBeans as response
    //values to send name and phone
    @RequestMapping("/dynamic-filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeansDynamic() {
        List<SomeBean2> list = Arrays.asList(new SomeBean2("Saurabh", "8888888888", "20000"), new SomeBean2("Devesh", "1111111111", "34000"));
        //invoking static method filterOutAllExcept()
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "phone");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        //constructor of MappingJacksonValue class that has list as constructor argument
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        //configuring filter
        mapping.setFilters(filters);
        return mapping;
    }
}
