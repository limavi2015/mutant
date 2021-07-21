package meli.com.demo.controller;

import meli.com.demo.controller.dto.DNA;
import meli.com.demo.controller.dto.Statistics;
import meli.com.demo.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class Controller {

    @Autowired
    private MutantService mutantService;

    @RequestMapping(path = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> validateIsMutant(@RequestBody DNA dnaDataDTO) {
        if (mutantService.isValidDna(dnaDataDTO)) {
            boolean isMutant = mutantService.isMutant(dnaDataDTO);
            if (isMutant)
                return ResponseEntity.ok("It is a mutant!!");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("It is a human.");
        } else {
            return ResponseEntity.badRequest().body("The DNA data is invalid.");
        }
    }


    @RequestMapping(path = "/stats", method = RequestMethod.GET)
    public ResponseEntity<Statistics> stats() {
        return ResponseEntity.ok(mutantService.getStatistics());
    }
}