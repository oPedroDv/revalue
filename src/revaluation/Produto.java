package revaluation;

public class Produto implements AvaliavelParaRevenda {

    private String nome;
    private String descricao;
    private String marca;
    private String estadoConservacao;
    private int anoFabricacao;
    private double valorDesejado;
    private int quantidade;
    private double pesoEmQuilos;
    private boolean existeAvaria;
    private String descricaoAvaria;
    private String dimensoes;
    private double valorFinal = 0;

    public Produto(String nome, String marca, int ano, double preco, double peso) {
        this.nome = nome;
        this.marca = marca;
        this.anoFabricacao = ano;
        this.valorDesejado = preco;
        this.pesoEmQuilos = peso;
        this.estadoConservacao = "Bom"; // Valor padrão
        this.existeAvaria = false;
    }

    public boolean getExisteAvaria() {
        return this.existeAvaria;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAnoFabricacao() {
        return this.anoFabricacao;
    }

    public String getEstadoConservacao() {
        return this.estadoConservacao;
    }

    private double valorPedido;
    public double getValorPedido() {
        return this.valorPedido;
    }


    boolean estadoProduto() { // retorna o estado do Produto
        return  estadoConservacao.equals("Ruim")||
                estadoConservacao.equals("Regular") ||
                estadoConservacao.equals("Bom") ||
                estadoConservacao.equals("Ótimo") ||
                estadoConservacao.equals("Novo");
    }

    double calcularValorFrete() { // calcula o frete com base no peso + valor fixo.
        return (pesoEmQuilos * 8) + 20;
    }

    public boolean valeAPenaVender(double valorMercadoAtual) { // calcula a logistica necessaria e valida se vale a pena
        double valorFinal = valorDesejado * 0.10 + calcularValorFrete();
        return  valorMercadoAtual - valorFinal >= 30;
    }

    public String resumoVendedor() { // retorna as informações para o usuario final
        return nome + " (" + marca + ") - " + estadoConservacao +
                " - R$ " + valorDesejado + " - " + (existeAvaria ? "COM DEFEITO" : "OK");
    }

    public double getValorDesejado() {
        return this.valorDesejado;
    }

    @Override
    public int calcularAvaliacao() {
        int pontoBase = switch (this.estadoConservacao) {
            case "Novo" -> 100;
            case "Ótimo" -> 90;
            case "Bom" -> 70;
            case "Regular" -> 40;
            case "Ruim" -> 10;
            default -> 0;
        };
        if (this.existeAvaria) {
            pontoBase -= 30;
        }
        return Math.max(0, pontoBase);
    }


    @Override
    public boolean estadoDeConservacaoAceitavel() {
        int notaAvaliacao = this.calcularAvaliacao();

        if (notaAvaliacao >= 50) { // menos que isso nãp é viavel comprar/vender
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double estimarValorDeMercado() {
        int anoAtual = java.time.Year.now().getValue();
        int idade = anoAtual - this.anoFabricacao;
        double fatorIdade = Math.min(idade * 0.05, 0.70);
        double valorBase = this.valorDesejado * (1 - fatorIdade);
        double fatorQualidade = calcularAvaliacao() / 100.0;
        return valorBase * fatorQualidade;
    }

    @Override
    public double calcularLogistica() {
        return calcularValorFrete();
    }

    @Override
    public String getResumoAvaliacao() {
        return "";
    }
}
