package br.com.nubank.ganhoCapital.service.model;

import java.util.ArrayList;
import java.util.List;

public class OperationsOutput {
    List<OperationOutput> operations = new ArrayList<>();

    public List<OperationOutput> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        for(OperationOutput o:operations){
            String jsonWay = String.format("%s,", o);
            toString.append(jsonWay);
        }
        return String.format("[%s]",toString.deleteCharAt(toString.length()-1));
    }
}
