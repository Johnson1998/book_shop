package com.john;

import java.util.List;
import java.util.Map;

/**
 * @author John
 * @create 2021-09-2019:56
 */
public class Person {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Person(int id, String name, List<String> cities, Map<String, Object> map) {
        this.id = id;
        this.name = name;
        this.cities = cities;
        this.map = map;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "com.john.Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                ", map=" + map +
                '}';
    }

    private int id;
    private String name;
    private List<String> cities;
    private Map<String, Object> map;
}
