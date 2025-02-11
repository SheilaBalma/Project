package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Data;
import com.example.demo.model.DataResponse;

import java.util.ArrayList;
import java.util.List;

// NUNCA ERROR 500

@RestController
@RequestMapping("/data")
public class DataController {

    // Valor maximo por defecto
   private int maxDef=2;

    // Valor maximo por variable
   private int maxElements;

    // Traigo el valor de la variable de entorno o uso el valor determinado
    public DataController () {
        String maxVar = System.getenv("MAX_ELEMENT");
        if (maxVar != null) {
            this.maxElements = Integer.parseInt(maxVar);
        }else {
            this.maxElements=maxDef;
        }

    }

    List <Data> data=new ArrayList<>();

    // Trae todos los elementos
    @GetMapping
    public List<Data> findAll() {
        return data;
    }

    // Crear un nuevo elemento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <DataResponse> create(@RequestBody Data newdata) {
        DataResponse response =new DataResponse();

        // Verifica el tamaño
       if(data.size()>=maxElements) {
           response.setError("Espacio completado. Maximo " + maxElements);
          return ResponseEntity.badRequest().body(response);
       }

       // Verifica si hay otra key igual
        for (Data item : data) {
            if (item.getKey().equals(newdata.getKey())) {
                response.setError("Ya existe un elemento con la clave: " + newdata.getKey());
                return ResponseEntity.badRequest().body(response);
            }
        }

       // Lo agrega
        data.add(newdata);
        response.setMessage("Elemento agregado correctamente.");
        response.setData(newdata);
        return ResponseEntity.ok().body(response);
    }

    // Actualizar elemento
    @PatchMapping("/{key}")
    public ResponseEntity<DataResponse> update(@PathVariable String key, @RequestBody Data updatedData) {
        DataResponse response =new DataResponse();
        for (Data item : data) {
            if (item.getKey().equals(key)) {
                if (updatedData.getName() != null) {
                    item.setName(updatedData.getName());
                }
                if (updatedData.getDescription() != null) {
                    item.setDescription(updatedData.getDescription());
                }
                if (updatedData.getValues() != null) {
                    item.setValues(updatedData.getValues());
                }
                response.setMessage("Elemento acutalizado correctamente.");
                response.setData(item);
                return ResponseEntity.ok().body(response);
            }
        }
        response.setError("No se encontro elemento con " + key);
        return ResponseEntity.badRequest().body(response);
    }

    // Eliminar elemento
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity <DataResponse> delete(@PathVariable String key) {
        DataResponse response =new DataResponse();
        for(Data item : data) {
            if (item.getKey().equals(key)) {
                data.remove(item);
                response.setMessage("Elemento borrado correctamente");
                return ResponseEntity.ok(response);
            }
        }
        response.setError("No se encontro elemento para eliminar con " + key);
        return ResponseEntity.badRequest().body(response);
    }
}
