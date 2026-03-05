package revaluation;

public class Eletronico extends Produto {
    private Cor cor;
    private Voltagem voltagem;
    private TipoEletronico tipoEletronico;
    public int mesesGarantia;

    public Eletronico(String nome, String marca, int ano, double preco, double peso, int mesesGarantia, TipoEletronico tipo, Voltagem volt, Cor cor) {
        super(nome, marca, ano, preco, peso);
        this.cor = cor;
        this.voltagem = volt;
        this.tipoEletronico = tipo;
        this.mesesGarantia = mesesGarantia;
    }

    public int calcularGarantia (){
        int anoAtual = java.time.Year.now().getValue();
        int idade = anoAtual - this.getAnoFabricacao();

        if (idade <= 1){
            this.mesesGarantia = 3;
        } else if (idade <= 2) {
            this.mesesGarantia = 2;
        } else this.mesesGarantia = 0;

        return this.mesesGarantia;
    }
    public enum Voltagem {
        BIVOLT, V110, V220;
    }

    public enum TipoEletronico{
        SMARTPHONE, LAPTOP, TABLET, CONSOLE;
    }

    public enum Cor {
        PRETO("Black"), // adiciona + 10% ao valor
        BRANCO("White"),
        AZUL("Blue"),
        DOURADO ("Golden"),
        VERMELHO("Red"),
        PRATA("Silver"),  // adiciona + 10% ao valor
        CINZA("Grey"),
        AMARELO("Yellow"),
        VERDE("Green"),
        ROSA("Pink"),
        LARANJA("Orange"),
        ROXO("Purple");

        private final String ingles;
        Cor(String ingles) {
            this.ingles = ingles;
        }

        public String getNomeEmIngles() {
            return String.format("%s / %s", this.name(), ingles );
        }

        public double calcularValorCor() {
            return switch (this) {
                case BRANCO, PRATA -> 0.10;
                case PRETO, LARANJA -> 0.15;
                default -> 0;
            };
        }

    }

    @Override
    boolean estadoProduto() {
        return super.estadoProduto();
    }

    @Override
    double calcularValorFrete() {
        return super.calcularValorFrete() + 15; // aumento de 15 reais no frete por ser produto fragil.
    }


    @Override
    public boolean valeAPenaVender(double valorMercadoAtual) {
        double precoBase = getValorDesejado();
        if (this.mesesGarantia > 0) {
            precoBase = precoBase * 1.10;
        }
        double custos = (precoBase * 0.10) + calcularValorFrete();
        return (valorMercadoAtual - custos) >= 30;
    }

    @Override
    public String resumoVendedor() {
        return super.resumoVendedor() +  "Garantia de: " + calcularGarantia() + "meses";
    }

    @Override
    public int calcularAvaliacao() {
        int nota = super.calcularAvaliacao();
        if (calcularGarantia() >= 12) nota += 10;
        else if (calcularGarantia() >= 6) nota += 5;
        else if (calcularGarantia() >= 3) nota += 2;
        return Math.min(100, nota);
    }

    @Override
    public double estimarValorDeMercado() {
        double valorBase = super.estimarValorDeMercado();
        valorBase += valorBase * cor.calcularValorCor();
        if (calcularGarantia() > 0) valorBase *= 1.10;
        return valorBase;
    }

    @Override
    public String getResumoAvaliacao() {
        return String.format(
                "%s | Tipo: %s | Cor: %s | Garantia: %d meses | Avaliação: %d/100 | Valor: R$ %.2f | Revenda: %s",
                getNome(), tipoEletronico, cor,
                calcularGarantia(), calcularAvaliacao(),
                estimarValorDeMercado(),
                estadoDeConservacaoAceitavel() ? "SIM" : "NÃO"
        );
    }
}

