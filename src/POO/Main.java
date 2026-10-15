package POO;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BibliotecaSimulada biblioteca = new BibliotecaSimulada();
        LocalDate dataCadastro = LocalDate.of(2026, 10, 10);

        System.out.println("=== FASE 1: Setup Inicial ===");
        biblioteca.exibirConsultasGlobais();

        System.out.println("=== FASE 2: Criar Membros e Livros ===");

        Membro joao = new Membro("João", dataCadastro, "joao@mail.com", new Padrao());
        Membro maria = new Membro("Maria", dataCadastro, "maria@mail.com", new Premium());
        Membro pedro = new Membro("Pedro", dataCadastro, "pedro@mail.com", new VIP());
        Membro ana = new Membro("Ana", dataCadastro, "ana@mail.com", new Premium());

        biblioteca.cadastrarMembro(joao);
        biblioteca.cadastrarMembro(maria);
        biblioteca.cadastrarMembro(pedro);
        biblioteca.cadastrarMembro(ana);

        biblioteca.cadastrarLivro(new Livros("O Cortiço", "Aluísio Azevedo", 3));
        biblioteca.cadastrarLivro(new Livros("Grande Sertão: Veredas", "Guimarães Rosa", 2));
        biblioteca.cadastrarLivro(new Livros("Capitães da Areia", "Jorge Amado", 2));
        biblioteca.cadastrarLivro(new Livros("Dom Casmurro", "Machado de Assis", 4));
        biblioteca.cadastrarLivro(new Livros("Vidas Secas", "Graciliano Ramos", 1));
        System.out.println("Membros e Livros cadastrados com sucesso!\n");

        System.out.println("=== FASE 3: Realizar Empréstimos (10/10/2026) ===");
        Emprestimo emp1 = biblioteca.realizarEmprestimo(joao, biblioteca.buscarLivro("O Cortiço"));
        Emprestimo emp2 = biblioteca.realizarEmprestimo(maria, biblioteca.buscarLivro("Grande Sertão: Veredas"));
        Emprestimo emp3 = biblioteca.realizarEmprestimo(maria, biblioteca.buscarLivro("Capitães da Areia"));
        Emprestimo emp4 = biblioteca.realizarEmprestimo(pedro, biblioteca.buscarLivro("Dom Casmurro"));
        Emprestimo emp5 = biblioteca.realizarEmprestimo(ana, biblioteca.buscarLivro("Vidas Secas"));

        if (emp1 != null) emp1.exibir();
        if (emp2 != null) emp2.exibir();
        if (emp3 != null) emp3.exibir();
        if (emp4 != null) emp4.exibir();
        if (emp5 != null) emp5.exibir();

        System.out.println("\n=== FASE 4: Renovações (Testando Sobrecarga) ===");
        System.out.printf("Valor renovação padrão: R$ %.2f\n", emp3.calcularTaxaRenovacao());
        System.out.printf("Valor renovação com cupom LEITOR10: R$ %.2f\n", emp3.calcularTaxaRenovacao("LEITOR10"));
        System.out.printf("Valor renovação com cupom RENOVACAO20: R$ %.2f\n", emp3.calcularTaxaRenovacao("RENOVACAO20"));

        emp3.renovar("LEITOR10");

        System.out.println("\nTentando renovar para o João (Categoria Padrão - não permite renovação):");
        emp1.renovar();

        System.out.println("\n=== FASE 5: Atrasos e Cálculo de Multas (Simulando Dias) ===");
        biblioteca.setDataAtual(LocalDate.of(2026, 10, 25));
        emp1.exibir();
        System.out.printf("Multa de João (sem parâmetros): R$ %.2f\n", emp1.calcularMulta());
        System.out.printf("Multa de João (com 15%% de desconto adicional): R$ %.2f\n", emp1.calcularMulta(0.15));

        System.out.println("\n--- Simulando 8 dias de atraso para o João ---");
        biblioteca.setDataAtual(LocalDate.of(2026, 11, 1));
        emp1.exibir();
        System.out.printf("Multa atualizada de João: R$ %.2f\n", emp1.calcularMulta());

        System.out.println("\n=== FASE 6: Devoluções ===");
        emp1.devolver();

        System.out.println("\n=== FASE 7: Consultas de Membros ===");
        System.out.println("Categoria atual da Maria: " + maria.getCategoria().getNome());
        System.out.println("Livros emprestados com a Maria atualmente: " + maria.getQtLivro());

        System.out.println("\n=== FASE 8: Mudança de Categoria ===");
        joao.trocarCategoria(new Premium());
        System.out.println("João mudou para a categoria: " + joao.getCategoria().getNome());

        System.out.println("\n=== FASE 9: Demonstração de Restrições (Classe Restricoes) ===");
        Restricoes regraTrabalho = new Restricoes(
                "Limitado por categoria de membro",
                "Inalterável após o cadastro",
                "Modificável via método trocarCategoria",
                "Calculada baseada nos dias de atraso e tipo de categoria",
                "Permitida apenas para categorias que suportam renovação"
        );

        System.out.println("Regras do Sistema de Biblioteca:");
        System.out.println("- Empréstimo: " + regraTrabalho.getEmprestar());
        System.out.println("- E-mail do Membro: " + regraTrabalho.getEmail());
        System.out.println("- Categoria: " + regraTrabalho.getCategoria());
        System.out.println("- Multa: " + regraTrabalho.getMulta());
        System.out.println("- Renovação: " + regraTrabalho.getRenovacao());

        System.out.println("\n=== FASE 11: Consultas Globais Finais ===");
        biblioteca.exibirConsultasGlobais();
    }
}

class BibliotecaSimulada extends SistemaBiblioteca {
    private LocalDate dataAtual = LocalDate.of(2026, 10, 10);

    public void setDataAtual(LocalDate dataAtual) {
        this.dataAtual = dataAtual;
    }

    @Override
    LocalDate hoje() {
        return dataAtual;
    }
}
