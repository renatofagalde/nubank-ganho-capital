package br.com.nubank.ganhoCapital;

import br.com.nubank.ganhoCapital.model.OperationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import jdk.dynalink.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GanhoCapital {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String DELIMITADOR_ARRAY_OBJETO = ",";
    public static final String DELIMITADOR_ARRAY_LIST = "]";

    //todo validar exception JsonProcessingException
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);

//        String inputStringList = getInputList(scanner);
        String inputStringList="[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}]\n" +
                "[{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":10.00, \"quantity\": 5000}]";
        inputStringList=inputStringList.replaceAll("\n","");
        List<OperationInput> operationsInput = new ArrayList<>();

        fixMultipleRootListInString(inputStringList);

        System.out.println("inputStringList = " + inputStringList);
    }

    private static void fixMultipleRootListInString(String inputStringList) {

        inputStringList = inputStringList.replaceAll("]\\[","],[");
        //change to list with lists
        inputStringList = String.format("%s,%s,%s","[",inputStringList,"]");

        System.out.println("inputStringList = " + inputStringList);

//        return inputStringList;
    }

    private static String getInputList(Scanner scanner) {
        StringBuffer inputLists = new StringBuffer();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            input = input.replaceAll("\n","");
            if (input.isEmpty()) break;
            inputLists.append(input);
        }
        return inputLists.toString();
    }

}
