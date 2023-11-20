package br.com.nubank.ganhoCapital;

import br.com.nubank.ganhoCapital.service.GanhoCapital;
import br.com.nubank.ganhoCapital.service.GanhoCapitalService;
import br.com.nubank.ganhoCapital.service.model.OperationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GanhoCapitalApp {

    static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String LOOKING_FOR_BRACKETS = "]\\[";
    public static final String LINE_BREAK = "\n";
    public static final String HASH = "#";
    public static final String CHANGE_TO_BRACKETS_AND_HASH = "]#[";
    public static final String EMPTY = "";
    public static int quantidadeAcoesAtual;
    public static double mediaPonderadaAtual;
    public static double prejuizoPassado;



    //todo validar exception JsonProcessingException
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        String inputStringList = getInputList(scanner);

        inputStringList = inputStringList.replaceAll(LINE_BREAK, EMPTY);



        inputStringList = fixMultipleRootListInString(inputStringList);
        List<List<OperationInput>> allListOperations = converterToListOperation(inputStringList);

        new GanhoCapitalService().processarLucroPrejuizo(allListOperations);

        allListOperations.stream().forEach(listOperation -> {
            listOperation.stream().forEach(operationInput -> {
                double imposto = 0;
                //todo escrever o pq não tem tratamento de erro e o pq não preciso checar o outro
                if (operationInput.getOperation().equals("sell")) {
                    imposto = imposto(operationInput);
                    quantidadeAcoesAtual -= operationInput.getQuantity();
                } else { //comprar acoes
                    mediaPonderadaAtual = mediaPonderada(operationInput);
                    quantidadeAcoesAtual += operationInput.getQuantity();
                }
                System.out.println("imposto = " + imposto);
            });
        });

    }

    public static double mediaPonderada(OperationInput operation) {
        double qtdAcoesAtualXAvgPond = quantidadeAcoesAtual * mediaPonderadaAtual;
        double qtdAcoesXValorCompra = getQtdAcoesXValor(operation);

        int somaQuantidadeAcoesCompradas = quantidadeAcoesAtual + operation.getQuantity();
        return (qtdAcoesAtualXAvgPond + qtdAcoesXValorCompra) / somaQuantidadeAcoesCompradas;
    }

    private static double getQtdAcoesXValor(OperationInput operation) {
        double qtdAcoesXValorCompra = operation.getUnitCost().doubleValue() * operation.getQuantity().doubleValue();
        return qtdAcoesXValorCompra;
    }

    public static double imposto(OperationInput operation) {
        double qtdAcoesXValorVenda = getQtdAcoesXValor(operation);
        double imposto = 0;

        double valorPonderado = operation.getQuantity().intValue() * mediaPonderadaAtual;
        double lucroAjuste = 0;

        //1 calcular lucro x prejuizo
        double lucro = qtdAcoesXValorVenda - valorPonderado;
        if (lucro < 0) { //prejuizo, aumentando o prejuizo
            prejuizoPassado += lucro;
        } else { //lucro

            //se o valor que teve de lucro, consigo zerar o prejuizo passado
            lucroAjuste = lucro + prejuizoPassado;

            //2 prejuizo anterior
            if (prejuizoPassado < 0) {
                if (lucroAjuste >= 0) { //posso fazer a conta direto, porque o meu lucro atual é maior ou igual ao prejuizo acumulado
                    prejuizoPassado = 0; //como é maior, posso zerar o prejuizo passado
                } else {
                    //quando tenho lucro, mas não é suficiente para zerar o prejuizoPassado
                    //so ajusto o prejuizoPassado
                    prejuizoPassado -= lucroAjuste;
                    lucroAjuste = 0;
                }
            }
        }

        if (qtdAcoesXValorVenda <= 20000) {
            return imposto;
        }

        //3 calculo do imposto da operacao
        imposto = lucroAjuste * 0.2;
        return imposto;
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
