package br.com.nubank.ganhoCapital;

import br.com.nubank.ganhoCapital.model.OperationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import jdk.dynalink.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GanhoCapital {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String DELIMITADOR_ARRAY_OBJETO = ",";
    public static final String DELIMITADOR_ARRAY_LIST = "]";


    //todo validar exception JsonProcessingException
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        //List<OperationInput> operationsInput = new ArrayList<>();

//        String inputStringList = getInputList(scanner);
//        String inputStringList = "[{\"operation\":\"buy\", \"unit\":10.00, \"quantity\": 100},\n" +
//                "{\"operation\":\"sell\", \"unit\":15.00, \"quantity\": 50},\n" +
//                "{\"operation\":\"sell\", \"unit\":15.00, \"quantity\": 50}]";

//        String inputStringList = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}]\n" +
//                "[{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":10.00, \"quantity\": 5000}]";
        String inputStringList = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n" +
                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000},\n" +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";
        inputStringList = inputStringList.replaceAll("\n", "");

        inputStringList = fixMultipleRootListInString(inputStringList);
        List<List<OperationInput>> allListOperations = converterToListOperation(inputStringList);
        allListOperations.stream().forEach(listOperation->{
            listOperation.stream().forEach(operationInput -> {
                System.out.println("operationInput.getOperation() = " + operationInput.getOperation() + "  " +operationInput.getUnitCost() + "  "+operationInput.getQuantity());
//                System.out.println("operationInput.getUnitCost() = " + operationInput.getUnitCost());
//                System.out.println("operationInput.getQuantity() = " + operationInput.getQuantity());
            });
            System.out.println("----------------");
        });
                

    }

    private static List<List<OperationInput>> converterToListOperation(String inputStringList) throws JsonProcessingException {
        String[] jsons = inputStringList.split("#");
        List<List<OperationInput>> operationInputsAll = new ArrayList<>();
        for(String s:jsons){
            List<OperationInput> operationInputs= objectMapper.readValue(s, new TypeReference<List<OperationInput>>() {
            });

            operationInputsAll.add(operationInputs);
        }
        return operationInputsAll;
    }

    private static String fixMultipleRootListInString(String inputStringList) {
        return inputStringList.replaceAll("]\\[", "]#[");
    }

    private static String getInputList(Scanner scanner) {
        StringBuffer inputLists = new StringBuffer();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            input = input.replaceAll("\n", "");
            if (input.isEmpty()) break;
            inputLists.append(input);
        }
        return inputLists.toString();
    }

}
