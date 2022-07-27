package ru.is.testcase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.is.testcase.dto.InputSquareDto;
import ru.is.testcase.services.MagicSquareService;
import java.util.List;

@RestController
@RequestMapping("/magicsquare")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MagicSquareController {

    @Autowired
    private MagicSquareService magicSquareService;

    @PostMapping("/calculate")
    public List<Integer> calculateSquare(@RequestBody List<Integer> values) {
        return magicSquareService.transformSquare(values);
    }

    @PostMapping("/save")
    public String saveInputSquare(@RequestBody List<Integer> values) {
        return magicSquareService.saveInputSquare(values);
    }

    @GetMapping("/load")
    public List<InputSquareDto> loadInputSquare() {
        return magicSquareService.loadInputSquare();
    }
}
