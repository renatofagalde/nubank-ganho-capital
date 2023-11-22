package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.model.OperationInput;
import br.com.nubank.ganhoCapital.service.model.OperationsOutput;

import java.util.List;

public interface CalcularLucroPrejuizo {
    List<OperationsOutput> processarLucroPrejuizo(List<List<OperationInput>> allListOperations);
}
