package br.radixeng.services;

import br.radixeng.model.Distancia;
import br.radixeng.model.Grafo;
import br.radixeng.model.Rota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DistanciaServices {

    public ArrayList<Distancia> buscaMenorDistancia(ArrayList<Rota> rotasEncontradas, ArrayList<Grafo> grafos) {
        ArrayList<Distancia> distancias = new ArrayList<Distancia>();
        ArrayList<Distancia> distanciaRetorno = new ArrayList<Distancia>();
        Distancia menorDistancia = new Distancia();
        Integer menorDistanciaValor = 0;

        for(Rota rota : rotasEncontradas){
            menorDistancia.setDistance(descobrirDistancia(rota.getRoute(),grafos));
            menorDistancia.setPath(rota.getRoute());
            distancias.add(menorDistancia);
            menorDistancia = new Distancia();
        }
        menorDistancia = distancias.get(0);
        menorDistanciaValor = distancias.get(0).getDistance();

        for (Distancia distancia : distancias)
        {
            if (distancia.getDistance()<menorDistancia.getDistance()){
                menorDistanciaValor = distancia.getDistance();
                menorDistancia = distancia;
            }
        }
        for (Distancia distancia : distancias)
        {
            if (distancia.getDistance() == menorDistanciaValor){
                distanciaRetorno.add(distancia);
            }
        }
        return distanciaRetorno;
    }
    public Integer descobrirDistancia(String possivelRota, ArrayList<Grafo> grafos) {
        Integer total = 0;
        Integer x = 1;
        Character source;
        Character target;
        for (int i = 0; i < possivelRota.length() - 1; i++, x++) {
            source = possivelRota.charAt(possivelRota.length() - possivelRota.length() + i);
            target = possivelRota.charAt(possivelRota.length() - possivelRota.length() + x);
            total = total + buscarDistancia(source.toString(), target.toString(), grafos);

        }
        return total;
    }

    private Integer buscarDistancia(String source, String target, ArrayList<Grafo> grafos) {
        Integer distancia = 0;
        for (Grafo grafo : grafos) {
            if (grafo.getSource().equals(source) && grafo.getTarget().equals(target)) {
                distancia = grafo.getDistance();
            }
        }
        return distancia;
    }


}