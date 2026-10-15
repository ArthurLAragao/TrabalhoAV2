package POO;

public final class Padrao extends Categoria {
    public Padrao() {
        super(14, 2.0, 3, false);
    }

    @Override
    public String getNome() {
        return "Padrao";
    }
}
