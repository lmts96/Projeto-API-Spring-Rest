package br.radixeng.services;

import br.radixeng.model.Grafo;
import br.radixeng.repository.GrafoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GrafoServices {
    @Autowired
    GrafoRepository repository;

    public ArrayList<Grafo> findById(Integer id){
        ArrayList<Grafo> grafos = new ArrayList<Grafo>();
        grafos = repository.findAllByidGroup(id);
        return grafos;
    }

    public String salvaGrafo(Grafo grafo){
        repository.save(grafo);
        return "Sucesso";
    }
    public Integer atribuiGrupo(Grafo grafo){
        Integer novoGrupo = 0;
        //Se não houver nenhum grupo ja cadastrado ele dá o valor de 1 para o primeiro grupo
        //Caso contrário ele pega o ultimo valorde grupo inserido e adiciona 1
        novoGrupo = repository.findTopByOrderByIdDesc() == null ? novoGrupo = 1 : repository.findTopByOrderByIdDesc().getIdGroup() + 1;
        return novoGrupo;
    }
}
