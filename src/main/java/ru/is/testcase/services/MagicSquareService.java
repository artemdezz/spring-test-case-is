package ru.is.testcase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.is.testcase.dto.InputSquareConverter;
import ru.is.testcase.dto.InputSquareDto;
import ru.is.testcase.exception.MagicSquareException;
import ru.is.testcase.model.InputSquare;
import ru.is.testcase.repositories.InputSquareRepository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagicSquareService {

    @Autowired
    private InputSquareRepository inputSquareRepository;

    @Autowired
    private InputSquareConverter inputSquareConverter;

    // https://sportloto.pp.ru/component/option,com_magic/morder,3/task,viewhalf/Itemid,23/
    int[][] combination =
            {{5,7,3,1,6,8,9,2,4},
            {9,1,5,2,6,7,4,8,3},
            {8,6,1,3,7,5,4,2,9},
            {7,3,5,2,4,9,6,8,1},
            {1,6,8,5,7,3,9,2,4},
            {9,4,2,5,3,7,1,8,6},
            {7,6,2,3,8,4,5,1,9},
            {3,4,8,7,2,6,5,9,1},
            {4,8,3,9,1,5,2,6,7},
            {3,7,5,8,6,1,4,2,9},
            {5,7,3,9,2,4,1,6,8},
            {9,1,5,4,8,3,2,6,7},
            {6,8,1,7,3,5,2,4,9},
            {9,5,1,2,7,6,4,3,8},
            {5,1,9,7,6,2,3,8,4},
            {2,7,6,4,3,8,9,5,1},
            {5,1,9,3,8,4,7,6,2},
            {7,5,3,2,9,4,6,1,8},
            {3,4,8,5,9,1,7,2,6},
            {3,8,4,5,1,9,7,6,2},
            {2,6,7,4,8,3,9,1,5},
            {3,5,7,8,1,6,4,9,2},
            {7,2,6,5,9,1,3,4,8},
            {5,9,1,7,2,6,3,4,8},
            {5,3,7,9,4,2,1,8,6},
            {1,8,6,5,3,7,9,4,2},
            {4,2,9,8,6,1,3,7,5},
            {1,9,5,8,4,3,6,2,7},
            {9,5,1,4,3,8,2,7,6},
            {9,2,4,1,6,8,5,7,3},
            {7,5,3,6,1,8,2,9,4},
            {8,3,4,6,7,2,1,5,9},
            {4,3,8,2,7,6,9,5,1},
            {1,5,9,8,3,4,6,7,2},
            {1,5,9,6,7,2,8,3,4},
            {9,4,2,1,8,6,5,3,7},
            {5,9,1,3,4,8,7,2,6},
            {6,2,7,1,9,5,8,4,3},
            {2,4,9,7,3,5,6,8,1},
            {8,4,3,1,9,5,6,2,7},
            {6,2,7,8,4,3,1,9,5},
            {1,9,5,6,2,7,8,4,3},
            {5,3,7,1,8,6,9,4,2},
            {2,6,7,9,1,5,4,8,3},
            {1,6,8,9,2,4,5,7,3},
            {6,7,2,8,3,4,1,5,9},
            {6,8,1,2,4,9,7,3,5},
            {2,9,4,6,1,8,7,5,3},
            {3,7,5,4,2,9,8,6,1},
            {6,1,8,2,9,4,7,5,3},
            {1,8,6,9,4,2,5,3,7},
            {9,2,4,5,7,3,1,6,8},
            {3,8,4,7,6,2,5,1,9},
            {3,5,7,4,9,2,8,1,6},
            {4,9,2,8,1,6,3,5,7},
            {8,6,1,4,2,9,3,7,5},
            {8,4,3,6,2,7,1,9,5},
            {4,2,9,3,7,5,8,6,1},
            {2,4,9,6,8,1,7,3,5},
            {8,1,6,4,9,2,3,5,7},
            {7,6,2,5,1,9,3,8,4},
            {7,3,5,6,8,1,2,4,9},
            {7,2,6,3,4,8,5,9,1},
            {4,8,3,2,6,7,9,1,5}};


    public List<Integer> transformSquare(List<Integer> inputSquare){

        if(inputSquare.stream().anyMatch(x -> x==null)){
            throw new MagicSquareException("Заполните все ячейки");
        }

        List<Integer> sortNumber = inputSquare
                .stream().sorted().collect(Collectors.toList());

        checkInputValues(sortNumber);

        List<Integer> minCostMagicSquare = null;
        int minCost = 0;
        for (int i = 0; i < combination.length; i++) {

            List<Integer> assumedMinCostMagicSquare = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                assumedMinCostMagicSquare.add(sortNumber.get(combination[i][j]-1));
            }

            int sumMod = 0;
            for (int j = 0; j < 9; j++) {
                sumMod += Math.abs(assumedMinCostMagicSquare.get(j)-inputSquare.get(j));
            }

            if(i==0){
                minCost=sumMod;
            }

            if(sumMod<minCost){
                minCost = sumMod;
                minCostMagicSquare = new ArrayList<>(assumedMinCostMagicSquare);
                assumedMinCostMagicSquare.clear();
            }
        }

        minCostMagicSquare.add(minCost);
        return minCostMagicSquare;
    }

    public String saveInputSquare(List<Integer> inputSquare){

        if(inputSquare.stream().anyMatch(x -> x==null)){
            throw new MagicSquareException("Заполните все ячейки");
        }

        String saveSquare = inputSquare.stream()
                .map(Object::toString).collect(Collectors.joining(","));

        if(inputSquareRepository.findByInputArray(saveSquare) != null){
            throw new MagicSquareException("Данный квадрат уже существует в Базе данных");
        }

        InputSquare magicSquare = new InputSquare();
        magicSquare.setInputArray(saveSquare);
        inputSquareRepository.save(magicSquare);
        return "Сохранено";
    }

    public List<InputSquareDto> loadInputSquare(){
        return inputSquareRepository.findAll().stream().map(a -> inputSquareConverter
                .convertInputSquareToInputSquareDto(a)).collect(Collectors.toList());
    }

    private void checkInputValues(List<Integer> inputSquare){
        int intendedSequence = inputSquare.get(1)-inputSquare.get(0);
        int currentNumber = inputSquare.get(0);
        for (Integer in: inputSquare){
            if(in-currentNumber==0){
                currentNumber += intendedSequence;
            } else {
                throw new MagicSquareException("Данный квадрат не имеет строгой последовательности");
            }
        }
    }
}
