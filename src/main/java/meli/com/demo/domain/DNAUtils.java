package meli.com.demo.domain;

import meli.com.demo.controller.dto.DNA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DNAUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    List<String> MUTANT_SEQUENCES = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    List<String> VALID_CHARACTERS = List.of("A", "C", "G", "T");
    String EMPTY = "";

    public boolean DNAHasValidSize(DNA dnaDataDTO) {
        int DNASequenceSize = dnaDataDTO.dna.stream().map(String::length).max(Comparator.naturalOrder()).orElse(0);
        if (DNASequenceSize != dnaDataDTO.dna.size())
            logger.error("Invalid size. The DNA must be NxN");
        return DNASequenceSize == dnaDataDTO.dna.size();
    }

    public boolean DNAHasValidCharacters(DNA dnaDataDTO) {
        String completeDNA = String.join("", dnaDataDTO.dna).toUpperCase();
        for (String mutantSequence : VALID_CHARACTERS) {
            completeDNA = completeDNA.replaceAll(mutantSequence, "");
        }
        if (completeDNA.length() > 0)
            logger.error("Invalid character. The DNA must have only these characters:" + VALID_CHARACTERS);
        return completeDNA.length() == 0;
    }

    public Integer countMutantSequences(List<String> dnaSequences) {
        int totalMutantSequences = 0;
        for (String dnaSequence : dnaSequences) {
            dnaSequence = dnaSequence.toUpperCase();
            for (String mutantSequence : MUTANT_SEQUENCES) {
                while (dnaSequence.contains(mutantSequence)) {
                    logger.info("Mutant sequence found: " + mutantSequence);
                    dnaSequence = dnaSequence.replaceFirst(mutantSequence, "Z");
                    totalMutantSequences += 1;
                    if (totalMutantSequences > 1)
                        break;
                }
            }
        }
        return totalMutantSequences;
    }

    public List<String> getVerticalSequences(List<String> dna) {
        List<String> verticalDnaSequences = new ArrayList();
        for (int x = 0; x <= dna.size() - 1; x++) {
            String sequence = EMPTY;
            for (int y = 0; y <= dna.size() - 1; y++) {
                if (dna.get(y).length() >= x + 1)
                    sequence = sequence.concat(dna.get(y).substring(x, x + 1));
            }
            verticalDnaSequences.add(sequence);
        }
        return verticalDnaSequences;
    }

    public List<String> getDiagonalRightSequences(List<String> dna) {
        List<String> diagonalRightDnaSequences = new ArrayList();
        int dna_size = dna.size();

        for (int x = 3; x <= dna_size - 1; x++) {
            String sequence = EMPTY;
            for (int y = x; y >= 0; y--) {
                sequence = sequence.concat(dna.get(x - y).substring(y, y + 1));
            }
            diagonalRightDnaSequences.add(sequence);
        }

        for (int x = 1; x < dna_size - 3; x++) {
            String sequence = EMPTY;
            for (int y = dna_size - 1; y >= x; y--) {
                int position = (dna_size - 1 - y) + x;
                sequence = sequence.concat(dna.get(y).substring(position, position + 1));
            }
            diagonalRightDnaSequences.add(sequence);
        }
        return diagonalRightDnaSequences;
    }

    public List<String> getDiagonalLeftSequences(List<String> dna) {
        List<String> diagonalLeftDnaSequences = new ArrayList();
        int dna_size = dna.size();

        for (int col = 0; col < dna_size - 3; col++) {
            String sequence = EMPTY;
            for (int row = 0; row <= dna_size - col - 1; row++) {
                int position = row + col;
                sequence = sequence.concat(dna.get(row).substring(position, position + 1));
            }
            diagonalLeftDnaSequences.add(sequence);
        }

        for (int col = dna_size - 2; col >= 3; col--) {
            String sequence = EMPTY;
            for (int row = dna_size - 1; row >= dna_size - 1 - col; row--) {
                int position = row - (dna_size - 1 - col);
                sequence = sequence.concat(dna.get(row).substring(position, position + 1));
            }
            diagonalLeftDnaSequences.add(sequence);
        }
        return diagonalLeftDnaSequences;
    }
}
