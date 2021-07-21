package meli.com.demo.service;

import meli.com.demo.controller.dto.DNA;
import meli.com.demo.controller.dto.Statistics;

public interface MutantService {
    Boolean isMutant(DNA dnaDTO);

    Boolean isValidDna(DNA dnaDataDTO);

    Statistics getStatistics();
}
