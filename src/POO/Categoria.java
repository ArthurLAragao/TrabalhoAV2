package POO;

public class Categoria {
        private int prazo;
        private double multa;
        private int maxLivro;
        private boolean renovar;

        public Categoria(int prazo, double multa, int maxLivro, boolean renovar) {
            this.prazo = prazo;
            this.multa = multa;
            this.maxLivro = maxLivro;
            this.renovar = renovar;
        }

        public int getPrazo() {
            return prazo;
        }

        public double getMulta() {
            return multa;
        }

        public int getMaxLivro() {
            return maxLivro;
        }

        public boolean getRenovar() {
            return renovar;
        }


    final class  Padrao extends Categoria{

        Padrao() {
            super(14, 2.0,3, false);
        }

    class Premium extends Categoria {
            Premium() {
                super(21, 1.5,5, true);
            }
        }
    }

    class VIP extends Categoria {
        VIP() {
            super(30, 1.0,10, true);
        }
    }



}

