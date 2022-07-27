package ru.is.testcase.dto;

import lombok.Data;
import javax.persistence.Column;

@Data
public class SubstringDto {

    @Column(name = "a1")
    private String a1;

    @Column(name = "a2")
    private String a2;

}
