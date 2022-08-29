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
class AssociadoControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void mainPostOk() throws Exception {
        String msg = "{\"nome\": \"Ziah\", \"cargoPolitico\": \"Presidente\", \"dataNascimento\": \"31/05/2011\", \"sexo\": \"Masculino\"}";
        mock.perform(MockMvcRequestBuilders.post("/associados")
          .content(msg)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void mainPostCargoError() throws Exception {
        String msg = "{\"nome\": \"Ziah\",\"cargoPolitico\": \"None\", \"dataNascimento\": \"31/05/2011\", \"sexo\": \"Masculino\"}";
        mock.perform(MockMvcRequestBuilders.post("/associados")
          .content(msg)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(400));
    }    
    
    @Test
    void mainPostSexoError() throws Exception {
        String msg = "{\"nome\": \"None\",\"cargoPolitico\": \"Presidente\", \"dataNascimento\": \"31/05/2011\", \"sexo\": \"SEXO\"}";
        mock.perform(MockMvcRequestBuilders.post("/associados")
          .content(msg)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(400));
    } 
    
    
    @Test
    void mainGetAssociadosOk() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/associados")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(200));
    } 
    
}