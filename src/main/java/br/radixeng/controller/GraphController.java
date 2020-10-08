package br.radixeng.controller;


import br.radixeng.model.Grafo;
import br.radixeng.services.GrafoServices;
import br.radixeng.services.JsonServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 */
@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GrafoServices grafoServices;

    @Autowired
    private JsonServices jsonServices;

    @GetMapping("/{id}")
    public ResponseEntity getGraph(@PathVariable Integer id){
        ResponseEntity response;
        ArrayList<Grafo> grafo = new ArrayList<Grafo>();

            grafo = grafoServices.findById(id);
            //Se o grafo não for encontrado, ele retorna um response de NotFound
            if(grafo.size() == 0){
                response = ResponseEntity.notFound().build();
            }else{
                response = ResponseEntity.ok(grafo);
            }

        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public ResponseEntity insertGrafo(@RequestBody LinkedHashMap jsonValues){
        ResponseEntity response;
        ArrayList<Grafo> grafosJson = new ArrayList<Grafo>();
        grafosJson = jsonServices.getListaByJson(jsonValues);
        Integer novoGrupo = grafoServices.atribuiGrupo(grafosJson.get(1));
        for (Grafo grafo : grafosJson){
            grafo.setIdGroup(novoGrupo);
            grafoServices.salvaGrafo(grafo);
        }
        response = ResponseEntity.status(201).body(grafosJson);

    return response;
    }
}

