package br.radixeng.services;

import br.radixeng.model.Grafo;
import br.radixeng.model.Rota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RotaServices {

    public ArrayList<Rota> buscaRotas(ArrayList<Grafo> grafos, String town1, String town2) {
        ArrayList<Rota> rotasEncontradas = new ArrayList<Rota>();
        rotasEncontradas = encontraRotas(town1, town2, grafos, rotasEncontradas);
        return rotasEncontradas;
    }

    private ArrayList<Rota> encontraRotas(String town1, String town2, ArrayList<Grafo> grafos, ArrayList<Rota> rotasEncontradas) {
        Rota rotaEncontrada = new Rota();
        String possivelRota = town1;
        String ultimaParada = town2;
        List<String> rotasSalvas = new ArrayList();
        List<String> rotasTestadas = new ArrayList();


        for (int i = 0; i < grafos.size(); i++){
                possivelRota = proximaRota(grafos, possivelRota, rotasSalvas,rotasTestadas,ultimaParada);

            rotasTestadas.add(possivelRota);
            if (possivelRota.substring(possivelRota.length() - 1).equals(ultimaParada) && !rotasSalvas.contains(possivelRota)) {
                rotasSalvas.add(possivelRota);
                rotaEncontrada = new Rota();
                rotaEncontrada.setId(null);
                rotaEncontrada.setRoute(possivelRota);
                rotaEncontrada.setStops(possivelRota.length() - 1);
                if(!validaRota(rotasEncontradas,rotaEncontrada)) {
                    rotasEncontradas.add(rotaEncontrada);
                }
                possivelRota = possivelRota.substring(0,possivelRota.length() - 1);
                i=0;
            }
        }

        return rotasEncontradas;
    }

    private String proximaRota(ArrayList<Grafo> grafos, String possivelRota, List<String> rotasSalvas, List<String> rotasTestadas, String ultimaParada) {
        boolean encontrouUmaRota = false;
        for (int i = 0; i < grafos.size(); i++)
        {
            //Se o ultimo ponto da rota for igual ao source, o target nao existir nessa rota e ele não tiver testado essa rota ele segue em frente
            if (possivelRota.substring(possivelRota.length()-1).equals(grafos.get(i).getSource())
                    && !(possivelRota.contains(grafos.get(i).getTarget()))
                    && !(rotasTestadas.contains(possivelRota.concat(grafos.get(i).getTarget())))) {
                possivelRota = possivelRota.concat(grafos.get(i).getTarget());
                encontrouUmaRota = true;
            }
            //Caso a rota ja possua a ultima parada, o sistema sai do loop e salva essa rota
            if(possivelRota.substring(possivelRota.length()-1).equals(ultimaParada) && !(rotasSalvas.contains(possivelRota))){
                break;
            }

            //Se uma rota for encontrada o FOR volta para o inicio no intuito de achar outra conexao
            if (encontrouUmaRota){ i = 0;}
            encontrouUmaRota = false;
            if(i == grafos.size()-1){
                if(!(possivelRota.length() == 1)){
                    rotasTestadas.add(possivelRota);
                    possivelRota = possivelRota.substring(0,possivelRota.length() - 1);
                }else{
                    break;
                }
            }
        }
        return possivelRota;
    }


    public ArrayList<Rota> limitaParadas(ArrayList<Rota> rotasEncontradas, Integer maxStops) {
        ArrayList<Rota> rotasFiltradas = new ArrayList<Rota>();
        //Aqui é feita a validação de paradas vendo se alguma rota tem parada maior que o valor inserido e se tiver, não é adicionada nas rotas filtradas
        for (Rota rota : rotasEncontradas) {
            if (rota.getStops() <= maxStops) {
                rotasFiltradas.add(rota);
            }
        }
        return rotasFiltradas;
    }

    private boolean validaRota(ArrayList<Rota> rotasEncontradas, Rota rotaEncontrada) {
        boolean jaExiste = false;
        //aqui é feita a validação para ver se a rota ja foi salva para ser entregue nessa pesquisa
        for(Rota rota: rotasEncontradas){
            if(rota.getRoute().equals(rotaEncontrada.getRoute())){
                jaExiste = true;
            }
        }
        return jaExiste;
    }

}