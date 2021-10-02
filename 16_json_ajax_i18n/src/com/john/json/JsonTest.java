package com.john.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.john.pojo.Person;
import org.junit.Test;
import org.junit.runners.model.FrameworkField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-3023:16
 */
public class JsonTest {
//    1.2.1 javaBean和json的互转
    @Test
    public void test1(){
        Person person = new Person(1, "dsadsa");

        Gson gson = new Gson();
//        toJson方法可以把java对象转化为Json字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);
        //frpmJson吧json字符串转化为java对象 第一i个是json字符串 第二个是java对象类型
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }
//    1.2.2 List 和json的互转
    @Test
    public void test2(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "123"));
        personList.add(new Person(2, "asdsadas"));

        Gson gson = new Gson();
        String s = gson.toJson(personList);
        System.out.println(s);
        List<Person> personList2 = gson.fromJson(s, new TypeToken<List<Person>>(){}.getType());

    }
//    1.2.3 map和json互转
    @Test
    public void test3(){
        Map<Integer, Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "dasdas"));
        personMap.put(2, new Person(2, "123456"));

        Gson gson = new Gson();
        String s = gson.toJson(personMap);
        System.out.println(s);
        Map<Integer, Person> personMap2  = gson.fromJson(s, new PersonMapType().getType());

        System.out.println( personMap.get(2));
    }

}
