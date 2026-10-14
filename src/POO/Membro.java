package POO;

public class Membro {
    private String nome;
    private int data;
    private String email;
    Categoria categoria;
    int QtLivro;

    void VerifCateg(){
        if (categoria == null) {
            System.out.println("O Membro precisa de uma categoria");
        }
    }

    public Membro(String nome, int data, String email, Categoria categoria) {
        this.nome = nome;
        this.data = data;
        this.email = email;
        this.categoria = categoria;

    }


    String getNome() {
        return nome;
    }
    int getData() {
        return data;
    }
    String getEmail() {
        return email;
    }
    Categoria getCategoria() {
        return categoria;
    }
    int getQtLivro() {
        return QtLivro;
    }

    void Trocarcategoria(Categoria novacategoria) {
        this.categoria = novacategoria;
        System.out.println("Categoria nova: " + this.categoria);
    }


    Boolean Podeempreestar() {
        return QtLivro < getCategoria().getMaxLivro();

    }

    void emprestar() {
        if ( !Podeempreestar() ) {
            System.out.println("Voce nao pode emprestar, chegou no limite de livros");
        }
        else {
            QtLivro++;

        }
    }





}
