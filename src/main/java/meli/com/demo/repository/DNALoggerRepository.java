package meli.com.demo.repository;

import meli.com.demo.entity.DNALogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNALoggerRepository extends CrudRepository<DNALogger, String> {
    Integer countByResult(String result);
}

