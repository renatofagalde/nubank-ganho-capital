package br.com.nubank.ganhoCapital;

import br.com.nubank.ganhoCapital.service.GanhoCapitalService;
import br.com.nubank.ganhoCapital.service.model.OperationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GanhoCapitalApp {

    public static final String LOOKING_FOR_BRACKETS = "]\\[";
    public static final String LINE_BREAK = "\n";
    public static final String HASH = "#";
    public static final String CHANGE_TO_BRACKETS_AND_HASH = "]#[";
    public static final String EMPTY = "";


    //todo validar exception JsonProcessingException
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        String inputStringList = getInputList(scanner);
        inputStringList = fixMultipleRootListInString(inputStringList);
        List<List<OperationInput>> allListOperations = converterToListOperation(inputStringList);

        new GanhoCapitalService().processarLucroPrejuizo(allListOperations);
    }

    private static List<List<OperationInput>> converterToListOperation(String inputStringList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] jsons = inputStringList.split(HASH);
        List<List<OperationInput>> operationInputsAll = new ArrayList<>();
        for (String json : jsons) {
            List<OperationInput> operationInputs = objectMapper.readValue(json, new TypeReference<List<OperationInput>>() {
            });
            operationInputsAll.add(operationInputs);
        }
        return operationInputsAll;
    }

    private static String fixMultipleRootListInString(String inputStringList) {
        return inputStringList.replaceAll(LOOKING_FOR_BRACKETS, CHANGE_TO_BRACKETS_AND_HASH);
    }

    private static String getInputList(Scanner scanner) {
        StringBuffer inputLists = new StringBuffer();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) break;
            inputLists.append(input);
        }
        return inputLists.toString().replaceAll(LINE_BREAK, EMPTY);
    }
}