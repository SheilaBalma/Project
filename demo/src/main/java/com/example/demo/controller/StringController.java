package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/string")

public class StringController {

    List<String> text = new ArrayList<>();

    @GetMapping
    public List<String> findAll() {
        return text;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody String resource) {
        text.add(resource);
        return text.size();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (id < 0 || id >= text.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        text.remove(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("id") int id, @RequestBody String newText) {
        if (id < 0 || id >= text.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        text.set(id, newText);
        return "Valor actualizado correctamente.";
    }
}
