package ru.is.testcase.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Substring{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "a1")
    private String a1;

    @Column(name = "a2")
    private String a2;
}
