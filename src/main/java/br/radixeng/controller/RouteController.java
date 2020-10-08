package br.radixeng.controller;


import br.radixeng.model.Grafo;
import br.radixeng.model.Rota;
import br.radixeng.services.GrafoServices;
import br.radixeng.services.JsonServices;
import br.radixeng.services.RotaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/routes")
public class RouteController {

    @Autowired
    private GrafoServices grafoServices;
    @Autowired
    private RotaServices rotaServices;

    @RequestMapping(value = "/{graphId}/from/{town1}/to/{town2}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody public ResponseEntity buscaRotas(
            @PathVariable Integer graphId,
            @PathVariable String town1,
            @PathVariable String town2,
            @RequestParam(defaultValue = "0") Integer maxStops ){
        ResponseEntity response;
        ArrayList<Rota> rotasEncontradas = new ArrayList<Rota>();
        ArrayList<Grafo> grafos = new ArrayList<Grafo>();

        //Busca o grupo de grafos selecionado para trazer as rotas possíveis
        grafos = grafoServices.findById(graphId);

        //Se o grafo não for encontrado, ele retorna um response de NotFound
        if(grafos.size() == 0){
            response = ResponseEntity.notFound().build();
        }else {
            //Busca as rotas possíveis para o grafo que foi buscado acima
            rotasEncontradas = rotaServices.buscaRotas(grafos, town1, town2);

            //Limita as rotas somente a quantidade de paradas selecionada
            if (!(maxStops == 0)) {
                rotasEncontradas = rotaServices.limitaParadas(rotasEncontradas, maxStops);
            }
            response = ResponseEntity.status(200).body(rotasEncontradas);
        }





        return response;
    }
}

