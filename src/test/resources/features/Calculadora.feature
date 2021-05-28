Feature: Calcular
  
  Scenario: Somar dois números inteiros positivos
    Given abro a calculadora do Google no meu smartphone
    When seleciono "3" mais "5" e pressiono igual
    Then exibe o resultado como "8"

  Scenario Outline: Somar dois números Data Driven
    Given abro a calculadora do Google no meu smartphone
    When seleciono <num1> mais <num2> e pressiono igual
    Then exibe o resultado como <resultadoEsperado>
    Examples:
      | num1 | num2 | resultadoEsperado |
      | "3"  | "5"  | "8"               |
      | "7"  | "3"  | "10"              |
      | "1"  | "5"  | "6"               |

  Scenario: Calcular com Massa de Teste
    Given que utilizo a massa "db/massaCalc.csv" para testar a calculadora
    When realizo a operacao com dois numeros
    Then compara o resultado atual com o esperado

