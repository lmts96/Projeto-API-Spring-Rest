package br.radixeng.services;

import br.radixeng.model.Grafo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@Service
public class JsonServices {

    public ArrayList<Grafo> getListaByJson(LinkedHashMap jsonGrafo){
        ArrayList<Grafo> listaNovosGrafos = new ArrayList<Grafo>();

        //Caso não venha uma lista de Grafos, ele salva o unico que ele recebeu através da função adicionaGrafo
        if(jsonGrafo.get("data").equals(null)){
            adicionaGrafo(listaNovosGrafos,jsonGrafo);
        }else{
        //Vindo uma lista, é feito um for each para pegar todos os Grafos recebidos através da função adicionaGrafo
            ArrayList<LinkedHashMap> listaGrafosJson = new ArrayList<LinkedHashMap>( );
            listaGrafosJson.addAll((Collection<? extends LinkedHashMap>) jsonGrafo.get("data"));
            for (LinkedHashMap json : listaGrafosJson) {
                adicionaGrafo(listaNovosGrafos,json);
            }
        }
        return listaNovosGrafos;
    }

    private ArrayList<Grafo> adicionaGrafo (ArrayList<Grafo> listaGrafos,LinkedHashMap json)
    {
        //Adiciona um grafo que estava no json em uma lista de grafos
        Grafo novoGrafo = new Grafo();
        novoGrafo.setSource(json.get("source").toString());
        novoGrafo.setTarget(json.get("target").toString());
        novoGrafo.setDistance(json.get("distance").hashCode());
        listaGrafos.add(novoGrafo);
        return listaGrafos;
    }
}
