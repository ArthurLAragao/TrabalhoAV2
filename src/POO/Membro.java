package POO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Membro {
    private String nome;
    private LocalDate data;
    private final String email;
    private Categoria categoria;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Membro(String nome, LocalDate data, String email, Categoria categoria) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Informe nome e email.");
        }
        if (data == null || categoria == null) {
            throw new IllegalArgumentException("Informe a data e a categoria.");
        }

        this.nome = nome;
        this.data = data;
        this.email = email;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getEmail() {
        return email;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void trocarCategoria(Categoria novaCategoria) {
        if (novaCategoria == null) {
            throw new IllegalArgumentException("Escolha uma categoria.");
        }
        categoria = novaCategoria;
    }

    public boolean podeEmprestar() {
        return getQtLivro() < categoria.getMaxLivro();
    }

    void registrarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public int getQtLivro() {
        int quantidade = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public double getMultaPendente() {
        double total = 0;
        for (Emprestimo emprestimo : emprestimos) {
            total += emprestimo.getMultaPendente();
        }
        return Emprestimo.arredondar(total);
    }

    public ArrayList<Livros> getLivrosAtrasados() {
        ArrayList<Livros> atrasados = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo() && emprestimo.getDiasAtraso() > 0) {
                atrasados.add(emprestimo.getLivro());
            }
        }
        return atrasados;
    }

    public ArrayList<Emprestimo> getUltimosTresEmprestimos() {
        ArrayList<Emprestimo> ultimos = new ArrayList<>();
        for (int i = emprestimos.size() - 1; i >= 0; i--) {
            if (ultimos.size() == 3) {
                break;
            }
            ultimos.add(emprestimos.get(i));
        }
        return ultimos;
    }

    public void exibirResumo() {
        System.out.println("\nNome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Cadastro: " + data);
        System.out.println("Categoria: " + categoria.getNome());
        System.out.println("Livros emprestados: " + getQtLivro());
        System.out.printf("Multa pendente: R$ %.2f%n", getMultaPendente());

        ArrayList<Livros> atrasados = getLivrosAtrasados();
        if (atrasados.isEmpty()) {
            System.out.println("Nao possui livros atrasados.");
        } else {
            System.out.println("Livros atrasados:");
            for (Livros livro : atrasados) {
                System.out.println(livro.getTitulo());
            }
        }

        System.out.println("Ultimos emprestimos:");
        for (Emprestimo emprestimo : getUltimosTresEmprestimos()) {
            System.out.println(emprestimo.getId() + " - " + emprestimo.getLivro().getTitulo());
        }
    }
}
