
# Ganho de capital APP

- [Indice do APP Ganho de capital](#ganho-de-capital-app)
  - [Introdução](#introdução)
  - [Estrutura do projeto](#estrutura-do-projeto)
  - [Linguagem e padrões de projeto](#linguagem-e-padrões-de-projeto)
    - [Java](#java)
  - [Rodando os cenarios:](#rodando-o-app)
    - [Instalando](#instalando)    
    - [Cenario1](#cenario-1)
    - [Cenario2](#cenario-2)
    - [Cenario1 e Cenario2](#cenario-1--cenario-2)
    - [Cenario3](#cenario-3)
    - [Cenario4](#cenario-5)
    - [Cenario5](#cenario-5)
    - [Cenario6](#cenario-6)
    - [Cenario7](#cenario-7)
    - [Cenario8](#cenario-8)


## Introdução

## Linguagem e padrões de projeto
### Java
    o mínimo uso de frameworks
### 

## Estrutura do projeto

```tree
.
├── pom.xml
├── readme.md
└── src
    ├── main
    │   ├── br
    │   │   └── com
    │   │       └── nubank
    │   │           └── ganhoCapital
    │   │               ├── GanhoCapitalApp.java
    │   │               └── service
    │   │                   ├── calculadora
    │   │                   │   ├── Calculadora.java
    │   │                   │   ├── CalculadoraPrecoMedioCompra.java
    │   │                   │   ├── CalculadoraPrecoMedio.java
    │   │                   │   └── CalculadoraPrecoMedioVenda.java
    │   │                   ├── CalcularLucroPrejuizo.java
    │   │                   ├── GanhoCapital.java
    │   │                   ├── GanhoCapitalService.java
    │   │                   └── model
    │   │                       ├── MediaPonderadaLucroPrejuizo.java
    │   │                       ├── OperationInput.java
    │   │                       ├── OperationOutput.java
    │   │                       └── OperationsOutput.java
    │   └── resources
    │       └── META-INF
    └── test
        └── java
            └── br
                └── com
                    └── nubank
                        └── ganhoCapital
                            ├── GanhoCapitalAppTest.java
                            └── service
                                ├── calculadora
                                │   ├── CalculadoraPrecoMedioCompraTest.java
                                │   └── CalculadoraPrecoMedioVendaTest.java
                                ├── GanhoCapitalServiceTest.java
                                └── model
                                    └── OperationsOutputTest.java

```

### Instalando
Todas as dependencias irão ser 
```shell
mvn clean install
```

### Cenario 1
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 100},
{"operation":"sell", "unit-cost":15.00, "quantity": 50},
{"operation":"sell", "unit-cost":15.00, "quantity": 50}]
```
esperado:
```json
[{"tax": 0},{"tax": 0},{"tax": 0}]
```

### Cenario 2
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
```
esperado:
```json
[{"tax": 0.00},{"tax": 10000.00},{"tax": 0.00}]
```

### Cenario 1 + Cenario 2
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input

[//]: # (&#40;usado o json lines, por ser uma entrada multiple root elements&#41;)
```json lines
[{"operation":"buy", "unit-cost":10.00, "quantity": 100},
  {"operation":"sell", "unit-cost":15.00, "quantity": 50},
  {"operation":"sell", "unit-cost":15.00, "quantity": 50}]
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
```
esperado:
[//]: # (usado o json lines, por ser uma entrada multiple root elements&#41;)
```json lines
[{"tax": 0.00},{"tax": 0.00},{"tax": 0.00}]
[{"tax": 0.00},{"tax": 10000.00},{"tax": 0.00}]
```
### Cenario 3
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":5.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 3000}]
```
esperado:
```json
[{"tax": 0.00},{"tax": 0.00},{"tax": 1000.00}]
```

### Cenario 4
- Ignorado o formatação de saída deste cenario, ele consta como valor: 0 ao invés de 0.00.  Entendo que o objetivo é validar os valores e não a formatação do texto.
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
- input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"buy", "unit-cost":25.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":15.00, "quantity": 10000}]
```
esperado sem formatação:
```json
[{"tax": 0},{"tax": 0},{"tax": 0}]
```
esperado com formatação:
```json
[{"tax": 0.00},{"tax": 0.00},{"tax": 0.00}]
```

### Cenario 5
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"buy", "unit-cost":25.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":15.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":25.00, "quantity": 5000}]
```
esperado:
```json
[{"tax": 0.00},{"tax": 0.00},{"tax": 0.00},{"tax": 10000.00}]
```

### Cenario 6
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":2.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 2000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 2000},
  {"operation":"sell", "unit-cost":25.00, "quantity": 1000}]
```
esperado:
```json
[{"tax": 0.00},{"tax": 0.00},{"tax": 0.00},{"tax": 0.00},{"tax": 3000.00}]
```

### Cenario 7
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
- Ignorado o formatação de saída deste cenario, neste a saída esperda tem espaços entre os objetos. Entendo que o objetivo é validar os valores e não a formatação do texto.
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":2.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 2000},
  {"operation":"sell", "unit-cost":20.00, "quantity": 2000},
  {"operation":"sell", "unit-cost":25.00, "quantity": 1000},
  {"operation":"buy", "unit-cost":20.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":15.00, "quantity": 5000},
  {"operation":"sell", "unit-cost":30.00, "quantity": 4350},
  {"operation":"sell", "unit-cost":30.00, "quantity": 650}]
```
esperado:
```json
[{"tax":0.00}, {"tax":0.00}, {"tax":0.00}, {"tax":0.00}, {"tax":3000.00},
  {"tax":0.00}, {"tax":0.00}, {"tax":3700.00}, {"tax":0.00}]
```

### Cenario 8
```shell
java -jar ./target/ganho-capital-1.0-SNAPSHOT-jar-with-dependencies.jar
```
input
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":50.00, "quantity": 10000},
  {"operation":"buy", "unit-cost":20.00, "quantity": 10000},
  {"operation":"sell", "unit-cost":50.00, "quantity": 10000}]
```
esperado:
```json
[{"tax":0.00},{"tax":80000.00},{"tax":0.00},{"tax":60000.00}]
```





OperationInput -> sem setter para priorizar o lance do solid


fixMultipleRootListInString existe um retorno no método, não foi usado referencia entre os métodos porque o tipo
string é imutavel.
este método está tratando o json fornecido no cenario: Case #1 + Case #2,

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]

```


GanhoCapital -> não tem necessidade de inicializar os valores por que são tipos primitivos

caso 4 o retorno não tem formatacao