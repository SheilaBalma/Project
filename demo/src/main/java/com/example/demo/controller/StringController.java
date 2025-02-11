package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.StringDao;

import java.util.List;

@RestController
@RequestMapping("/string")
public class StringController {

    private StringDao dao;

    public StringController(StringDao dao){
        this.dao = dao;
    }

    @GetMapping
    public List<String> findAll() {
        return dao.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody String resource) {
        dao.add(resource);
        return dao.size();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (id < 0 || id >= dao.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        dao.remove(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("id") int id, @RequestBody String newText) {
        if (id < 0 || id >= dao.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        dao.set(id, newText);
        return "Valor actualizado correctamente.";
    }
}
