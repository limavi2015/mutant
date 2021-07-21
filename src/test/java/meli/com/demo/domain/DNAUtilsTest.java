package meli.com.demo.domain;

import meli.com.demo.controller.dto.DNA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DNAUtilsTest {

    DNAUtils utils = new DNAUtils();

    @DisplayName("DNA validation - Size validation")
    @Test
    void DNAHasValidSize() {
        DNA data = new DNA(List.of("AAA","AAA"));
        assertEquals(false, utils.DNAHasValidSize(data));

        data = new DNA(List.of("AAA","AAA", "AAA"));
        assertEquals(true, utils.DNAHasValidSize(data));
    }

    @DisplayName("DNA validation -  Characters validation")
    @Test
    void DNAHasValidCharacters() {
        DNA data = new DNA(List.of("XCG","TAA", "ACG"));
        assertEquals(false, utils.DNAHasValidCharacters(data));

        data = new DNA(List.of("ACG","TAA", "ACG"));
        assertEquals(true, utils.DNAHasValidCharacters(data));
    }

}