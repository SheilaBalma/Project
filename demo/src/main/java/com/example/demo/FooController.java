package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foos")
class FooController {

    List <String> values=new ArrayList<>();

    // Te trae todos los valores
    @GetMapping
    public List<String> findAll() {
        return values;
    }

    // Te trae los valores con ese id
    @GetMapping(value = "/{id}")
    public String findById(@PathVariable("id") int id) {
        return values.get(id);
    }

    // Crea un nuevo valor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody String resource) {
         values.add(resource);
         return values.size();
    }
}
