package br.com.nubank.ganhoCapital;

import br.com.nubank.ganhoCapital.model.OperationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GanhoCapital {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String LOOKING_FOR_BRACKETS = "]\\[";
    public static final String LINE_BREAK = "\n";
    public static final String HASH = "#";
    public static final String CHANGE_TO_BRACKETS_AND_HASH = "]#[";
    public static final String EMPTY = "";

    public static HashMap<Integer, List<OperationInput>> operationsMap = new HashMap<>();

    public static Integer count = 0;

    //public static MediaPonderada mediaPonderada = new MediaPonderada(0, 0, 0);

    public static int quantidadeAcoesAtual;
    public static double mediaPonderadaAtual;

    public static int quantidadeAcoesCompradas;

    //todo validar exception JsonProcessingException
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        //List<OperationInput> operationsInput = new ArrayList<>();

//        String inputStringList = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n" +
//                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000},\n" +
//                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";

        String inputStringList = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":15.00, \"quantity\": 200},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 200},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 200},\n" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 400}]";

        inputStringList = inputStringList.replaceAll(LINE_BREAK, EMPTY);

        inputStringList = fixMultipleRootListInString(inputStringList);
        List<List<OperationInput>> allListOperations = converterToListOperation(inputStringList);
        allListOperations.stream().forEach(listOperation -> {
            operationsMap.put(++count, listOperation); //todo trocar para reduce
            listOperation.stream().forEach(operationInput -> {

                //todo escrever o pq não tem tratamento de erro e o pq não preciso checar o outro
                if (operationInput.getOperation() == "sell") {
                    System.out.println("operationInput = " + operationInput);
                } else { //comprar acoes
                    mediaPonderadaAtual = mediaPonderada(operationInput);
                    System.out.println("result = " + mediaPonderadaAtual);
                }
                quantidadeAcoesAtual += operationInput.getQuantity();

            });
            System.out.println("----------------");
        });

        System.out.println("operationsMap = " + operationsMap.size());
        System.out.println("====");

    }

    public static double mediaPonderada(OperationInput operation) {
        double qtdAcoesAtualXAvgPond = quantidadeAcoesAtual * mediaPonderadaAtual;
        double qtdAcoesXValorCompra = operation.getUnitCost().doubleValue() * operation.getQuantity().doubleValue();

        int somaQuantidadeAcoesCompradas = quantidadeAcoesAtual + operation.getQuantity();
        return  (qtdAcoesAtualXAvgPond + qtdAcoesXValorCompra) / somaQuantidadeAcoesCompradas;
    }

    public static double venda(OperationInput operation) {
        double qtdAcoesAtualXAvgPond = quantidadeAcoesAtual * mediaPonderadaAtual;
        double qtdAcoesXValorCompra = operation.getUnitCost().doubleValue() * operation.getQuantity().doubleValue();

        int somaQuantidadeAcoesCompradas = quantidadeAcoesAtual + operation.getQuantity();
        return  (qtdAcoesAtualXAvgPond + qtdAcoesXValorCompra) / somaQuantidadeAcoesCompradas;
    }

    private static List<List<OperationInput>> converterToListOperation(String inputStringList) throws JsonProcessingException {
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
            input = input.replaceAll(LINE_BREAK, EMPTY);
            if (input.isEmpty()) break;
            inputLists.append(input);
        }
        return inputLists.toString();
    }

}
