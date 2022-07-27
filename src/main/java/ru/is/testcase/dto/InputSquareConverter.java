package ru.is.testcase.dto;

import org.springframework.stereotype.Service;
import ru.is.testcase.model.InputSquare;

@Service
public class InputSquareConverter {

    public InputSquareDto convertInputSquareToInputSquareDto(InputSquare inputSquare){
        InputSquareDto inputSquareDto = new InputSquareDto();
        inputSquareDto.setInputArray(inputSquare.getInputArray());
        return inputSquareDto;
    }

}
