package meli.com.demo.service;

import meli.com.demo.controller.dto.DNA;
import meli.com.demo.controller.dto.Statistics;
import meli.com.demo.domain.DNAUtils;
import meli.com.demo.entity.DNALogger;
import meli.com.demo.repository.DNALoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MutantServiceImpl extends DNAUtils implements MutantService {

    int MINIMAL_DNA_SIZE_TO_MUTANTS = 4;
    int MINIMAL_DNA_SEQUENCES_TO_BE_A_MUTANT=2;

    @Autowired
    private DNALoggerRepository loggerRepository;

    public Boolean isValidDna(DNA dnaDataDTO) {
        if (DNAHasValidCharacters(dnaDataDTO))
            return DNAHasValidSize(dnaDataDTO);
        else
            return false;
    }

    public Boolean isMutant(DNA dnaDTO) {
        int totalMutantSequences = 0;
        if (dnaDTO.dna.size() < MINIMAL_DNA_SIZE_TO_MUTANTS)
            return false;

        totalMutantSequences += countMutantSequences(dnaDTO.dna);

        if (totalMutantSequences < MINIMAL_DNA_SEQUENCES_TO_BE_A_MUTANT){
            List<String> verticalSequences =getVerticalSequences(dnaDTO.dna);
            totalMutantSequences += countMutantSequences(verticalSequences);
        }

        if (totalMutantSequences < MINIMAL_DNA_SEQUENCES_TO_BE_A_MUTANT) {
            List<String> diagonalRightSequences =getDiagonalRightSequences(dnaDTO.dna);
            totalMutantSequences += countMutantSequences(diagonalRightSequences);
        }

        if (totalMutantSequences < MINIMAL_DNA_SEQUENCES_TO_BE_A_MUTANT) {
            List<String> diagonalLeftSequences =getDiagonalLeftSequences(dnaDTO.dna);
            totalMutantSequences += countMutantSequences(diagonalLeftSequences);
        }

        Boolean result = totalMutantSequences >= MINIMAL_DNA_SEQUENCES_TO_BE_A_MUTANT;
        logDnaValidation(result, dnaDTO.dna);
        return result;
    }

    public Statistics getStatistics(){
        Integer mutants = loggerRepository.countByResult("Mutant");
        Integer humans = loggerRepository.countByResult("Human");
        Integer ratio = (humans>0)? mutants/humans : 0;
        return new Statistics(mutants,humans,ratio);
    }

    private void logDnaValidation(Boolean isMutant, List<String> dna){
        String complete_dna = dna.stream().collect(Collectors.joining(", "));
        loggerRepository.save(new DNALogger(complete_dna,((isMutant) ? "Mutant" : "Human")));
    }

}
