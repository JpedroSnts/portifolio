package dev.jpedrosnts.portifolio.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tecnologia")
public class Tecnologia {

    @Id
    @Column(name = "sg_tecnologia")
    private String id;
    @Column(name = "nm_tecnologia")
    private String nome;
    @Column(name = "nm_icone")
    private String icone;

}
