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

    Boolean Podeempreestar() {
        return membro.getQtLivros() <= getCategoria().getMaxLivro();

    }

        void emprestar() {
            if ( !Podeempreestar() ) {
                System.out.println("Voce nao pode emprestar, chegou no limite de livros");
            }
            else {
                getMembro().getQtLivros()++;

            }
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
