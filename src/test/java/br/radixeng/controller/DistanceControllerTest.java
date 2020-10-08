package br.radixeng.controller;

import br.radixeng.Application;
import br.radixeng.model.Distancia;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DistanceControllerTest {
    
    private MockMvc mockMvc;
    
    @Autowired
	private DistanceController distanceController;
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(distanceController).build();
    }

    @Test
    public void testSameRoute() throws Exception {
        Distancia distancia = new Distancia();
        distancia.setPath("BB");
        distancia.setDistance(0);
        mockMvc.perform(get("distance/1/from/B/to/B"))
                .andExpect(request().asyncResult(distancia));
    }

    @Test
    public void testDistanceAC() throws Exception {
        Distancia distancia = new Distancia();
        distancia.setPath("ABC");
        distancia.setDistance(9);
        mockMvc.perform(get("distance/1/from/A/to/C"))
                .andExpect(request().asyncResult(distancia));
    }
}
