package POO;

public abstract class Categoria {
    private final int prazo;
    private final double multa;
    private final int maxLivro;
    private final boolean renovar;

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

    public double getDescontoMulta() {
        return 0;
    }

    public abstract String getNome();

    public String toString() {
        return getNome();
    }
}
