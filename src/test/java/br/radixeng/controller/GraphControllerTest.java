package br.radixeng.controller;

import br.radixeng.Application;
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
import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
@RunWith(SpringRunner.class)	
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class GraphControllerTest {
    
    private MockMvc mockMvc;
    
    @Autowired
	private GraphController graphController;
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(graphController).build();
    }
    
    
    @Test
	public void testGraphNotFound() throws Exception {
        String inesistentGraphId = "123";
		mockMvc.perform(get(String.format("/graph/%s",inesistentGraphId)))
               .andExpect(status().isNotFound());
	}


	@Test
	public void testGraphInsert() throws Exception {
		String json = "{"
				+		"				\"data\":["
				+		"{"
				+		"	\"source\": \"A\", \"target\": \"B\", \"distance\":5"
				+		"},"
				+		"{"
				+		"	\"source\": \"B\", \"target\": \"C\", \"distance\":4"
				+		"},"
				+		"{"
				+		"	\"source\": \"C\", \"target\": \"D\", \"distance\":8"
				+		"},"
				+		"{"
				+		"	\"source\": \"D\", \"target\": \"C\", \"distance\":8"
				+		"},"
				+		"{"
				+		"	\"source\": \"D\", \"target\": \"E\", \"distance\":6"
				+		"},"
				+		"{"
				+		"	\"source\": \"A\", \"target\": \"D\", \"distance\":5"
				+		"},"
				+		"{"
				+		"	\"source\": \"C\", \"target\": \"E\", \"distance\":2"
				+		"},"
				+		"{"
				+		"	\"source\": \"E\", \"target\": \"B\", \"distance\":3"
				+		"},"
				+		"{"
				+	"		\"source\": \"A\", \"target\": \"E\", \"distance\":7"
				+	"	}"
				+  "]"
				+  "}";

		mockMvc.perform(post("/graph",json))
				.andExpect(status().isCreated());
	}
}
