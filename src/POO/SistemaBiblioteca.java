package POO;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBiblioteca {
    private ArrayList<Membro> membros = new ArrayList<>();
    private ArrayList<Livros> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private double totalMultasPagas;

    LocalDate hoje() {
        return LocalDate.now();
    }

    public void cadastrarMembro(Membro membro) {
        if (membro == null) {
            throw new IllegalArgumentException("Informe o membro.");
        }
        for (Membro cadastrado : membros) {
            if (cadastrado.getEmail().equalsIgnoreCase(membro.getEmail())) {
                throw new IllegalArgumentException("Email ja cadastrado.");
            }
        }
        membros.add(membro);
    }

    public void cadastrarLivro(Livros livro) {
        if (livro == null || livros.contains(livro)) {
            throw new IllegalArgumentException("Livro invalido ou ja cadastrado.");
        }
        livros.add(livro);
    }

    public Membro buscarMembro(String nome) {
        for (Membro membro : membros) {
            if (membro.getNome().equalsIgnoreCase(nome)) {
                return membro;
            }
        }
        return null;
    }

    public Livros buscarLivro(String titulo) {
        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Emprestimo realizarEmprestimo(Membro membro, Livros livro) {
        return realizarEmprestimo(membro, livro, hoje());
    }

    public Emprestimo realizarEmprestimo(Membro membro, Livros livro, LocalDate data) {
        if (data == null || data.isAfter(hoje())) {
            throw new IllegalArgumentException("Data de emprestimo invalida.");
        }
        if (!membros.contains(membro) || !livros.contains(livro)) {
            throw new IllegalArgumentException("Cadastre o membro e o livro primeiro.");
        }
        if (!membro.podeEmprestar()) {
            System.out.println("Membro chegou ao limite de livros.");
            return null;
        }
        if (livro.getQuant() == 0) {
            System.out.println("Livro sem copias disponiveis.");
            return null;
        }

        Emprestimo emprestimo = new Emprestimo(this, livro, membro, data);
        livro.emprestar();
        membro.registrarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    void registrarMultaPaga(double valor) {
        totalMultasPagas = Emprestimo.arredondar(totalMultasPagas + valor);
    }

    public double getTotalMultasPagas() {
        return totalMultasPagas;
    }

    public int getTotalMembros() {
        return membros.size();
    }

    public int getTotalMembrosPorCategoria(String categoria) {
        int total = 0;
        for (Membro membro : membros) {
            if (membro.getCategoria().getNome().equalsIgnoreCase(categoria)) {
                total++;
            }
        }
        return total;
    }

    public int getTotalEmprestimosAtivos() {
        int total = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                total++;
            }
        }
        return total;
    }

    public int getTotalEmprestimosAtrasados() {
        int total = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo() && emprestimo.getDiasAtraso() > 0) {
                total++;
            }
        }
        return total;
    }

    public void exibirConsultasGlobais() {
        System.out.println("\nTotal de membros: " + getTotalMembros());
        System.out.println("Padrao: " + getTotalMembrosPorCategoria("Padrao"));
        System.out.println("Premium: " + getTotalMembrosPorCategoria("Premium"));
        System.out.println("VIP: " + getTotalMembrosPorCategoria("VIP"));
        System.out.println("Emprestimos ativos: " + getTotalEmprestimosAtivos());
        System.out.println("Emprestimos atrasados: " + getTotalEmprestimosAtrasados());
        System.out.printf("Multas pagas: R$ %.2f%n", totalMultasPagas);
    }
}
