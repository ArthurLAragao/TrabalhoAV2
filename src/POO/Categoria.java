package POO;

public abstract class Categoria {
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

        public abstract String getNome();
        @Override
        public String toString() {
            return getNome();
        }




}

