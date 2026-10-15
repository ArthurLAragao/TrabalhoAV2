package POO;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca();
        LocalDate hoje = LocalDate.now();

        Membro joao = new Membro("Joao", hoje, "joao@mail.com", new Padrao());
        Membro maria = new Membro("Maria", hoje, "maria@mail.com", new Premium());
        Membro pedro = new Membro("Pedro", hoje, "pedro@mail.com", new VIP());

        biblioteca.cadastrarMembro(joao);
        biblioteca.cadastrarMembro(maria);
        biblioteca.cadastrarMembro(pedro);

        Livros cortico = new Livros("O Cortico", "Aluisio Azevedo", 3);
        Livros capitaes = new Livros("Capitaes da Areia", "Jorge Amado", 2);
        Livros casmurro = new Livros("Dom Casmurro", "Machado de Assis", 4);

        biblioteca.cadastrarLivro(cortico);
        biblioteca.cadastrarLivro(capitaes);
        biblioteca.cadastrarLivro(casmurro);

        Emprestimo emp1 = biblioteca.realizarEmprestimo(joao, cortico, hoje.minusDays(22));
        Emprestimo emp2 = biblioteca.realizarEmprestimo(maria, capitaes);
        Emprestimo emp3 = biblioteca.realizarEmprestimo(pedro, casmurro, hoje.minusDays(40));

        emp1.exibir();
        emp2.exibir();
        emp3.exibir();

        System.out.println("\nTaxas de renovacao:");
        System.out.println(emp2.calcularTaxaRenovacao());
        System.out.println(emp2.calcularTaxaRenovacao("LEITOR10"));
        System.out.println(emp2.calcularTaxaRenovacao("RENOVACAO20"));

        emp1.renovar();
        emp2.renovar("LEITOR10");
        emp3.renovar();

        System.out.println("\nMultas:");
        System.out.println("Joao: " + emp1.calcularMulta());
        System.out.println("Joao com 15% de desconto: " + emp1.calcularMulta(0.15));
        System.out.println("Pedro: " + emp3.calcularMulta());
        System.out.println("Pedro com mais 10% de desconto: " + emp3.calcularMulta(0.10));

        biblioteca.exibirConsultasGlobais();
        joao.exibirResumo();

        emp1.devolver();
        System.out.println("Joao pagou: " + emp1.pagarMulta());
        emp1.devolver();

        System.out.println("Pedro pagou: " + emp3.pagarMulta(0.10));
        emp3.devolver();

        joao.trocarCategoria(new Premium());
        joao.exibirResumo();
        maria.exibirResumo();
        biblioteca.exibirConsultasGlobais();
    }
}
