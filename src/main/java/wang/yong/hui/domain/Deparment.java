package wang.yong.hui.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="deparment")
public class Deparment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
