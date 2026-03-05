package revaluation;

public interface AvaliavelParaRevenda {
    int calcularAvaliacao();  // calcula o produto de 0 até 100
    boolean estadoDeConservacaoAceitavel(); // avalia se o estado de conservação do produto está em condições praticaveis de venda
    double estimarValorDeMercado(); // estima o valor de mercado do produto
    double calcularLogistica(); // calcula a logistica a partir de caracteristicas especificas do produto
    String getResumoAvaliacao(); // retorna a avaliação final do produto
}

