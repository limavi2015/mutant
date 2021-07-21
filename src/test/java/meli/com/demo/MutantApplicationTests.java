package meli.com.demo;

import meli.com.demo.controller.Controller;
import meli.com.demo.repository.DNALoggerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MutantApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_a_humnan_DNA_then_forbidden_status()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/mutant")
                .content("{\"dna\":[\"ATGA\",\"TATT\",\"TTAA\", \"gAAA\"] }\n")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void given_a_mutant_DNA_then_ok_status()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/mutant")
                .content("{\"dna\":[\"AAACCC\",\"AAACCC\",\"TTTAAG\", \"ATAACC\", \"AAACCC\", \"AAACCC\"] }\n")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void given_a_mutant_DNA_Diagonal_Right_then_ok_status()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/mutant")
                .content("{\"dna\":[\"AAACCC\",\"AAACCC\",\"TTTAAG\", \"ATAACC\", \"AAACCC\", \"AAACCC\"] }\n")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void given_a_mutant_DNA_Diagonal_Left_then_ok_status()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/mutant")
                .content("{\"dna\":[\"AAACCC\",\"AAACCC\",\"TCTAAG\", \"ATCACC\", \"AAACCC\", \"TATCCC\"] }\n")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void given_a_invalid_DNA_then_badrequest_status()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/mutant")
                .content("{\"dna\":[\"AJAA\",\"TATT\",\"TTAA\", \"AAAA\"] }\n")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void get_statistics()  throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
