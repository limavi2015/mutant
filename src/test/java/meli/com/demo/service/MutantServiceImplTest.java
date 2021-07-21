package meli.com.demo.service;

import meli.com.demo.controller.dto.DNA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MutantServiceImplTest {

    @Autowired
    MutantService mutantService;

    @DisplayName("DNA validation")
    @Test
    void isValidDNA() {
        DNA data = new DNA(List.of("AAAA","CGCC","CCCA","TTTG"));
        assertEquals(true, mutantService.isValidDna(data));

        data = new DNA(List.of("AAHA","CGCC","CCCA","TTTG"));
        assertEquals(false, mutantService.isValidDna(data));
    }

    @DisplayName("DNA validation")
    @Test
    void isMutant() {
        DNA data = new DNA(List.of("AAAA","CGCC","CCCA","TTTG"));
        assertEquals(false, mutantService.isMutant(data));

        data= new DNA(List.of("AAAA","TTTC","TTTC","CCCC")); //horizontal
        assertEquals(true, mutantService.isMutant(data));

        data= new DNA(List.of("CTTA","CTTA","CTTA","CGGA")); //vertical
        assertEquals(true, mutantService.isMutant(data));

        data= new DNA(List.of("CTTC","TCCT","TCCT","CTTC")); //diagonals
        assertEquals(true, mutantService.isMutant(data));
    }

}
