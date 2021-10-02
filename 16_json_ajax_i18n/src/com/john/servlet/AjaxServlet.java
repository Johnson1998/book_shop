package com.john.servlet;

import com.google.gson.Gson;
import com.john.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John
 * @create 2021-10-018:35
 */
public class AjaxServlet extends BaseServlet{

    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("Ajax���������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }
    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("JQueryAjax == ��������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }

    protected void jQueryGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("JQueryGet == ��������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }
    protected void jQueryPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("JQueryPost == ��������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }
    protected void jQuerygetJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("JQuerygetJSON == ��������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }
    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("jQuerySerialize == ��������");
        Person person = new Person(1, "john");
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);


    }
}
