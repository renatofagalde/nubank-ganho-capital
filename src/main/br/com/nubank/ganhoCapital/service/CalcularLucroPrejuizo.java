package br.com.nubank.ganhoCapital.service;

import br.com.nubank.ganhoCapital.service.model.OperationInput;

import java.util.List;

public interface CalcularLucroPrejuizo {
    void processarLucroPrejuizo(List<List<OperationInput>> allListOperations);
}
