package ru.is.testcase.dto;

import org.springframework.stereotype.Service;
import ru.is.testcase.model.Substring;

@Service
public class SubstringConverter {

    public SubstringDto convertSubstringToSubstringDto(Substring substring){
        SubstringDto substringDto = new SubstringDto();
        substringDto.setA1(substring.getA1());
        substringDto.setA2(substring.getA2());
        return substringDto;
    }

}
