package revaluation;
public class Movel extends Produto {
    private double dimensao;
    private Material material;


     public Movel(String nome, String marca, int ano, double preco, double peso,double dimensao, Material material) {
        super(nome, marca, ano, preco, peso);
        this.dimensao = dimensao;
        this.material = material;
    }

    public enum Material {
    MADEIRA, MDF, METAL, PLASTICO, VIDRO, MARMORE

    }

    @Override
    public int calcularAvaliacao() {
        int pontoBase = switch (this.getEstadoConservacao()) {
            case "Novo" -> 100;
            case "Ótimo" -> 90;
            case "Bom" -> 70;
            case "Regular" -> 40;
            case "Ruim" -> 10;
            default -> 0;
        };
        boolean materialNobre = switch (this.material) {
            case MADEIRA, METAL, MARMORE -> true;
            default -> false;
        };
        if (materialNobre) pontoBase += 30;
        if (this.getExisteAvaria()) pontoBase -= 15;

        return Math.max(0, pontoBase);
    }

    @Override
    public double estimarValorDeMercado() {
        double valorBase = super.estimarValorDeMercado();
        boolean materialNobre = switch (this.material) {
            case MADEIRA, METAL, MARMORE -> true;
            default -> false;
        };
        if (materialNobre) valorBase *= 1.20;
        if (this.dimensao >= 2.0) valorBase *= 1.10;
        return valorBase;
    }

    @Override
    public double calcularLogistica() {
        return calcularValorFrete() + (this.dimensao * 10);
    }

    @Override
    public String getResumoAvaliacao() {
        return String.format(
                "%s | Material: %s | Dimensão: %.1fm² | Avaliação: %d/100 | Valor: R$ %.2f | Revenda: %s",
                getNome(), material, dimensao,
                calcularAvaliacao(), estimarValorDeMercado(),
                estadoDeConservacaoAceitavel() ? "SIM" : "NÃO"
        );
    }
}
