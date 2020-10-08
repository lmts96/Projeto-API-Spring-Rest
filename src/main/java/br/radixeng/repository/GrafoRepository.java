package br.radixeng.repository;

import br.radixeng.model.Grafo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 *
 */
@Repository
public interface GrafoRepository extends JpaRepository<Grafo, Long> {

    //busca a ultima linha
    Grafo findTopByOrderByIdDesc();

    //Faz um Select de todas as linhas de um determinado grupo
    ArrayList<Grafo> findAllByidGroup(Integer id);

}

