package revaluation;

    public class Main {
        public static void main(String[] args) {

            Produto produto = new Produto("Cadeira", "Generica", 2020, 200.0, 5.0);
            System.out.println(produto.getResumoAvaliacao());

            Eletronico eletronico = new Eletronico("iPhone 13", "Apple", 2022, 3500.0, 0.5, 12,
                    Eletronico.TipoEletronico.SMARTPHONE, Eletronico.Voltagem.BIVOLT, Eletronico.Cor.PRETO);
            System.out.println(eletronico.getResumoAvaliacao());

            Vestuario vestuario = new Vestuario("Jaqueta", "Zara", 2023, 350.0, 0.8, 42,
                    Vestuario.TipoVestuario.CAMISA, Vestuario.TipoMalha.COURO,
                    Vestuario.Cor.PRETO, Vestuario.Genero.MASCULINO, false);
            System.out.println(vestuario.getResumoAvaliacao());

            Movel movel = new Movel("Sofá", "Tok&Stok", 2021, 1500.0, 40.0, 3.5, Movel.Material.MADEIRA);
            System.out.println(movel.getResumoAvaliacao());
        }
    }

