package br.com.compass.cadastro.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PartidoControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void mainPostOk() throws Exception {
        String msg = "{\"nome\": \"Ziah\", \"sigla\": \"Z\", \"ideologia\": \"Esquerda\", \"dataFundacao\": \"31/05/2011\"}";
        mock.perform(MockMvcRequestBuilders.post("/partidos")
          .content(msg)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(200));
    }
   
    @Test
    void mainPostIdeologiaError() throws Exception {
    	String msg = "{\"nome\": \"Ziah\", \"sigla\": \"Z\", \"ideologia\": \"none\", \"dataFundacao\": \"31/05/2011\"}";
        mock.perform(MockMvcRequestBuilders.post("/partidos")
          .content(msg)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(400));
    }        
    
    @Test
    void mainGetPartidosOk() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/partidos")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(200));
    } 
    
}