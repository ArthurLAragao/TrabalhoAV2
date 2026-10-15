package POO;

public class Premium extends Categoria {
    public Premium() {
        super(21, 1.5, 5, true);
    }

    @Override
    public String getNome() {
        return "Premium";
    }
}
