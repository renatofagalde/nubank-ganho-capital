


OperationInput -> sem setter para priorizar o lance do solid


fixMultipleRootListInString existe um retorno no método, não foi usado referencia entre os métodos porque o tipo
string é imutavel.
este método está tratando o json fornecido no cenário: Case #1 + Case #2,

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]

```


GanhoCapital -> não tem necessidade de inicializar os valores por que são tipos primitivos

caso 4 o retorno não tem formatacao