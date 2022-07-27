package ru.is.testcase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.is.testcase.dto.SubstringDto;
import ru.is.testcase.services.SubstringService;

import java.util.List;

@RestController
@RequestMapping("/substring")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubstringController {

    @Autowired
    private SubstringService substringService;

    @PostMapping("/calculate")
    public List<String> calculateSubString(@RequestBody List<String> stringArrays) {
        return substringService.calculateStringArrays(stringArrays);
    }

    @PostMapping("/save")
    public String saveInputArrays(@RequestBody List<String> stringArrays) {
        return substringService.saveInputArrays(stringArrays);
    }

    @GetMapping("/load")
    public List<SubstringDto> loadInputSquare() {
        return substringService.loadInputArrays();
    }
}
