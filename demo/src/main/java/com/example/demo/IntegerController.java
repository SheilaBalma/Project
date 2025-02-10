package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/integer")
public class IntegerController {

    // Lista que almacena valores enteros
    List<Integer> numbers = new ArrayList<>();

    // Método para obtener todos los valores
    @GetMapping
    public List<Integer> findAll() {
        return numbers;
    }

    // Método para crear un nuevo valor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Integer resource) {
        numbers.add(resource);
        return numbers.size(); // Retorna el tamaño de la lista después de agregar el elemento
    }

    // Método para eliminar un valor por ID
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (id < 0 || id >= numbers.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        numbers.remove(id); // Elimina el elemento en la posición dada
    }

    // Método para actualizar un valor en la lista por ID
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("id") int id, @RequestBody Integer newValue) {
        if (id < 0 || id >= numbers.size()) {
            throw new IndexOutOfBoundsException("El ID proporcionado no es válido.");
        }
        numbers.set(id, newValue); // Actualiza el elemento en la posición dada
        return "Valor actualizado correctamente.";
    }
}
