package meli.com.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Statistics {
    int count_mutant_dna;
    int count_human_dna;
    int ratio;
}
