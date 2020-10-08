package br.radixeng.controller;


import br.radixeng.model.Distancia;
import br.radixeng.model.Grafo;
import br.radixeng.model.Rota;
import br.radixeng.services.DistanciaServices;
import br.radixeng.services.GrafoServices;
import br.radixeng.services.RotaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 *
 */
@RestController
@RequestMapping(value = "/distance")
public class DistanceController {

    @Autowired
    private GrafoServices grafoServices;
    @Autowired
    private RotaServices rotaServices;
    @Autowired
    private DistanciaServices distanciaServices;

    @RequestMapping(value = "/{graphId}/from/{town1}/to/{town2}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody public ResponseEntity buscaRotas(
            @PathVariable Integer graphId,
            @PathVariable String town1,
            @PathVariable String town2){

        ResponseEntity response = ResponseEntity.notFound().build();
        ArrayList<Rota> rotasEncontradas = new ArrayList<Rota>();
        ArrayList<Grafo> grafos = new ArrayList<Grafo>();
        ArrayList<Distancia>  distancia = new ArrayList<Distancia>();
        Distancia distanciaDummy = new Distancia();

        //Busca o grupo de grafos selecionado para trazer as rotas possíveis
        grafos = grafoServices.findById(graphId);

        //Se o grafo não for encontrado, ele retorna um response de NotFound
        if(grafos.size() == 0){
            response = ResponseEntity.notFound().build();
        }else {
            //Se o ponto de partida e o ponto de chegada forem o mesmo, o sistema ja considera a distancia como 0
            if (town1.equals(town2)) {
                distanciaDummy.setDistance(0);
                distanciaDummy.setPath(town1.concat(town2));
                distancia.add(distanciaDummy);
            } else {
                //Busca as rotas possíveis para o grafo que foi buscado acima
                rotasEncontradas = rotaServices.buscaRotas(grafos, town1, town2);
                if (rotasEncontradas.size() == 0) {
                    response = ResponseEntity.status(200).body(-1);
                } else {
                    //Busca a menor distancia baseada nas rotas recebidas
                    distancia = distanciaServices.buscaMenorDistancia(rotasEncontradas, grafos);
                }
            }
        }

        if(distancia.size() == 0){
        }else{
            response = ResponseEntity.status(200).body(distancia);
        }


        return response;
    }
}

