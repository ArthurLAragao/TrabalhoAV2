package POO;

public class Livros {
    private String titulo;
    private String autor;
    private final int quantidadeOriginal;
    private int quantdisp;

    public Livros(String titulo, String autor, int quantdisp) {
        if (titulo == null || titulo.isBlank() || autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Informe titulo e autor.");
        }
        if (quantdisp < 0) {
            throw new IllegalArgumentException("Quantidade invalida.");
        }

        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeOriginal = quantdisp;
        this.quantdisp = quantdisp;
    }

    void emprestar() {
        if (quantdisp == 0) {
            throw new IllegalStateException("Nao tem copias disponiveis.");
        }
        quantdisp--;
    }

    void devolver() {
        if (quantdisp == quantidadeOriginal) {
            throw new IllegalStateException("Todas as copias ja foram devolvidas.");
        }
        quantdisp++;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getQuant() {
        return quantdisp;
    }

    public int getQuantidadeOriginal() {
        return quantidadeOriginal;
    }

    public void exibicao() {
        System.out.println(titulo + " - " + autor + " | Disponiveis: " + quantdisp);
    }
}
