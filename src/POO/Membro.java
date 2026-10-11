package POO;

import java.sql.SQLOutput;
import java.util.Objects;

public class Membro {
    private String nome;
    private int data;
    private String email;
    Categoria categoria;
    int QtLivro;


    if (categoria == null) {
        System.out.println("O Membro precisa de uma categoria");
    }


    Membro(String nome, int data, String email, Categoria categoria, int QtLivro) {
        this.nome = nome;
        this.data = data;
        this.email = email;
        this.categoria = categoria;
        this.QtLivro = 0;

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
        return this.QtLivro <= getCategoria().getMaxLivro();

    }

    void Emprestar() {
        if ( !Podeempreestar() ) {
            System.out.println("Voce nao pode emprestar, chegou no limite de livros");
        }
        else {
            QtLivro++;
        }
    }







}
