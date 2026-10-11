package POO;

public class Livros {
    private String titulo;
    private String autor;
    static int quantdisp;

    Livros(String titulo,String autor,int quantdisp ){
        this.titulo = titulo;
        this.autor = autor;
        Livros.quantdisp = quantdisp;

    }

    void emprestar(){
        quantdisp -= quantdisp;
        System.out.println("livro emprestado...");
        System.out.println("quantidade restante: "+ quantdisp);
    }
    void devolver() {
        quantdisp += quantdisp;
        System.out.println("livro devolvido");
        System.out.println("quantidade restante: "+ quantdisp);

    }

    void exibicao(){
        System.out.println("indentificador: ");
        System.out.println("titulo:" + getTitulo());
        System.out.println("autor " + getAutor());
        System.out.println("membro: ");
        System.out.println("");
    }

    String getTitulo() {
        return titulo;
    }

    String getAutor() {
        return autor;
    }

}
