package com.example.demo.dao;

import java.util.List;

public interface StringDao {

    List<String> findAll();

    void add(String resource);

    int size();

    void remove(int id);

    void set(int id, String newText);

}
