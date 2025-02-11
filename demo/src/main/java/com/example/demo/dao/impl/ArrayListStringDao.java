package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dao.StringDao;

@Component
public class ArrayListStringDao implements StringDao{
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    public List<String> findAll() {
        return this.strings;
    }

    @Override
    public void add(String resource) {
        this.strings.add(resource);
    }

    @Override
    public int size() {
        return this.strings.size();
    }

    @Override
    public void remove(int index) {
        this.strings.remove(index);
    }

    @Override
    public void set(int index, String newText) {
        this.strings.set(index, newText);
    }

}
