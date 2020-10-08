package br.radixeng.controller;

import br.radixeng.Application;
import br.radixeng.model.Distancia;
import br.radixeng.model.Rota;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RouteControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
	private RouteController routeController;
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }


    @Test
    public void testRoute(){
        ArrayList<Rota> rotas = new ArrayList<Rota>();
        Rota rota1 = new Rota();
        rota1.setStops(2);rota1.setRoute("ADC");
        Rota rota2 = new Rota();
        rota2.setStops(2);rota2.setRoute("ABC");
        Rota rota3 = new Rota();
        rota3.setStops(4);rota3.setRoute("ADEBC");
        Rota rota4 = new Rota();
        rota4.setStops(3);rota4.setRoute("AEBC");
        rotas.add(rota1);
        rotas.add(rota2);
        rotas.add(rota3);
        rotas.add(rota4);


        try {
            mockMvc.perform(post("/routes/1/from/A/to/C?maxStops=4"))
                    .andExpect(request().asyncResult(rotas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
