# encoding: iso-8859-1
Feature: Consultar CEP

  Scenario Outline: Consulta CEP valido
    Given o usuario insere um "<cep_valido>"
    When o servico eh consultado
    Then eh retornado o "<cep>", "<logradouro>", "<complemento>", "<bairro>", "<localidade>", "<uf>", "<ibge>"

    Examples: 
      | cep_valido | cep       | logradouro                | complemento | bairro        | localidade   | uf | ibge    |
      |   91060900 | 91060-900 | Avenida Assis Brasil 3940 |             | São Sebastião | Porto Alegre | RS | 4314902 |

  Scenario Outline: Consulta CEP inexistente
    Given o usuario insere um "<cep_inexistente>"
    When o servico eh consultado
    Then eh retornada um atributo "<erro>"

    Examples: 
      | cep_inexistente | erro |
      |        91060901 | true |

  Scenario Outline: Consulta CEP com formato invalido
    Given o usuario insere um "<cep_invalido>"
    When o servico eh consultado
    Then eh retornada uma "<mensagem_de_erro>"

    Examples: 
      | cep_invalido | mensagem_de_erro                  |
      |    123456789 | Verifique a sua URL (Bad Request) |

  Scenario Outline: Consulta dados informando UF, localidade, parte do logradouro
    Given o usuário insere "<uf>", "<localidade>", "<logradouro>"
    When o servico eh consultado
    Then eh retornada uma lista de locais que correspondem a pesquisa

    Examples: 
      | uf | localidade | logradouro |
      | RS | Gravatai   | Barroso    |
