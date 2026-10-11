package POO;

public class Livros {
    private String titulo;
    private String autor;
    private int quantdisp;

    Livros(String titulo,String autor,int quantdisp ){
        this.titulo = titulo;
        this.autor = autor;
        this.quantdisp = quantdisp;

    }

    void devolver() {
        quantdisp++;
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
