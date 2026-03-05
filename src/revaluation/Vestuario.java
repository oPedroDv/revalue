package revaluation;

public class Vestuario extends Produto {
    public enum TipoMalha {ALGODAO, POLIESTER, LINHO, JEANS, COURO, CAMURCA}

    public enum Cor {PRETO, BRANCO, VERDE, AMARELO, AZUL, VERMELHO}

    public enum TipoVestuario {CAMISA, CALCA, SHORT, REGATA, TENIS}

    public enum Genero {MASCULINO, FEMININO, UNISEX}

    private TipoMalha tipoMalha;
    private Cor cor;
    private TipoVestuario tipoVestuario;
    private Genero genero;
    private boolean estampa;
    private int numeracao;


    public Vestuario(String nome, String marca, int ano, double preco, double peso, int numeracao, TipoVestuario tipoVestuario, TipoMalha tipoMalha, Cor cor, Genero genero, boolean estampa) {
        super(nome, marca, ano, preco, peso);
        this.tipoMalha = tipoMalha;
        this.cor = cor;
        this.tipoVestuario = tipoVestuario;
        this.genero = genero;
        this.estampa = estampa;
        this.numeracao = numeracao;

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
        if (this.estampa) {
            pontoBase -= 20;
        }
        if (this.tipoMalha == TipoMalha.ALGODAO ||
                this.tipoMalha == TipoMalha.COURO ||
                this.tipoMalha == TipoMalha.CAMURCA) {
            pontoBase += 20;
        }
        return Math.max(0, pontoBase);
    }

    @Override
    public double estimarValorDeMercado() {
        double valorBase = super.estimarValorDeMercado();
        double porcentagem = 0.0;
        if (this.tipoMalha == TipoMalha.ALGODAO ||
                this.tipoMalha == TipoMalha.COURO ||
                this.tipoMalha == TipoMalha.CAMURCA) {
            porcentagem += 20.0;
        }
        if (this.estampa) {
            porcentagem -= 15.0;
        }
        if (this.numeracao <= 36 || this.numeracao >= 48) {
            porcentagem -= 15.0;
        }
        double valorAdicional = valorBase * (porcentagem / 100);
        return valorBase + valorAdicional;
    }
    @Override
    public double calcularLogistica() {
        return calcularValorFrete();
    }

    @Override
    public String getResumoAvaliacao() {
        return String.format(
                "%s | Malha: %s | %s | Numeração: %d | Estampa: %s | Avaliação: %d/100 | Valor: R$ %.2f | Revenda: %s",
                getNome(),
                tipoMalha,
                getEstadoConservacao(),
                numeracao,
                estampa ? "Sim" : "Não",
                calcularAvaliacao(),
                estimarValorDeMercado(),
                estadoDeConservacaoAceitavel() ? "SIM" : "NÃO"
            );
        }
    }

