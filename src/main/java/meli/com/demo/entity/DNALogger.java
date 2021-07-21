package meli.com.demo.entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Data
@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "dna_logger", schema = "public")
public class DNALogger {
    @Id
    @Column(name = "dna")
    private String dna;

    @Column(name = "result")
    private String result;

}
