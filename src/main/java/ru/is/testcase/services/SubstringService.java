package ru.is.testcase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.is.testcase.dto.SubstringConverter;
import ru.is.testcase.dto.SubstringDto;
import ru.is.testcase.exception.SubstringException;
import ru.is.testcase.model.Substring;
import ru.is.testcase.repositories.SubstringRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubstringService {

    @Autowired
    private SubstringRepository substringRepository;

    @Autowired
    private SubstringConverter substringConverter;

    public List<String> calculateStringArrays(List<String> stringArrays){

        List<String> arrayA1 = Arrays.stream(stringArrays.get(0).split("\\s+"))
                .filter(a-> !a.equals("")).collect(Collectors.toList());

        List<String> arrayA2 = Arrays.stream(stringArrays.get(1).split("\\s+"))
                .filter(a-> !a.equals("")).collect(Collectors.toList());

        if(arrayA1.size() == 0 || arrayA2.size()==0){
            throw new SubstringException("Заполните все ячейки");
        }

        List<String> subStrings = new ArrayList<>();
        arrayA2.stream().filter(a-> !a.equals("")).forEach(a -> {
            for(String s: arrayA1){
                if(a.contains(s)){
                    subStrings.add(s);
                }
            }
        });
        List<String> arrayR = subStrings.stream().distinct().sorted().collect(Collectors.toList());

        if(arrayR.size()==0){
            throw new SubstringException("Строки а2 не имеют подстрок из а1");
        }

        return arrayR;
    }


    public String saveInputArrays(List<String> stringArrays){

        List<String> arrayA1 = Arrays.stream(stringArrays.get(0).split("\\s+"))
                .filter(a-> !a.equals("")).collect(Collectors.toList());

        List<String> arrayA2 = Arrays.stream(stringArrays.get(1).split("\\s+"))
                .filter(a-> !a.equals("")).collect(Collectors.toList());

        if(arrayA1.size() == 0 || arrayA2.size()==0){
            throw new SubstringException("Заполните все ячейки");
        }

        Substring substring = new Substring();
        substring.setA1(stringArrays.get(0));
        substring.setA2(stringArrays.get(1));
        substringRepository.save(substring);
        return "Сохранено";
    }

    public List<SubstringDto> loadInputArrays(){
        return substringRepository.findAll().stream().map(a -> substringConverter
                .convertSubstringToSubstringDto(a)).collect(Collectors.toList());
    }

}
