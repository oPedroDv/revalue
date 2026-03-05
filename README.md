# revalue

Sistema de avaliação de produtos para revenda desenvolvido em Java como projeto de estudo.

## Sobre o projeto

O revalue simula uma plataforma de avaliação de produtos usados, onde cada item passa por critérios específicos para determinar se vale a pena revendê-lo. A ideia central é que produtos diferentes têm características diferentes, e essas características influenciam diretamente no valor de mercado, na logística e na nota de avaliação.

## O que foi praticado

- Interfaces e contratos entre classes
- Herança e sobrescrita de métodos
- Polimorfismo
- Enums para representar categorias e atributos
- Encapsulamento com getters e setters
- Lógica de negócio aplicada a um domínio real

## Estrutura

```
revaluation/
├── AvaliavelParaRevenda.java   # interface principal
├── Produto.java                # classe base
├── Eletronico.java             # herda de Produto
├── Vestuario.java              # herda de Produto
├── Movel.java                  # herda de Produto
└── Main.java                   # exemplos de uso
```

A interface `AvaliavelParaRevenda` define o contrato que todas as categorias de produto devem seguir:

- `calcularAvaliacao()` — retorna uma nota de 0 a 100
- `estadoDeConservacaoAceitavel()` — indica se o produto está em condições de revenda
- `estimarValorDeMercado()` — estima o valor atual do produto
- `calcularLogistica()` — calcula o custo de logística
- `getResumoAvaliacao()` — retorna um resumo completo da avaliação

## Como rodar

Pré-requisito: Java 14 ou superior (uso de switch expressions).

```bash
# clone o repositório
git clone https://github.com/seu-usuario/revalue.git

# compile
javac src/revaluation/*.java -d out

# execute
java -cp out revaluation.Main
```

## Tecnologias

- Java 17
- IntelliJ IDEA