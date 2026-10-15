package POO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private static int gID = 1;
    private static final double valorRenovacao = 5.0;

    private final int id;
    private Livros livro;
    private Membro membro;
    private SistemaBiblioteca sistema;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private boolean ativo;
    private double multaQuitada;
    private double multaFinal;

    Emprestimo(SistemaBiblioteca sistema, Livros livro, Membro membro, LocalDate dataEmprestimo) {
        this.id = gID++;
        this.sistema = sistema;
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataEmprestimo.plusDays(membro.getCategoria().getPrazo());
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public Livros getLivro() {
        return livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getStatus() {
        if (ativo) {
            return "Ativo";
        }
        return "Devolvido";
    }

    public long getDiasAtraso() {
        LocalDate data = sistema.hoje();
        if (!ativo) {
            data = dataDevolucao;
        }

        long dias = ChronoUnit.DAYS.between(dataPrevista, data);
        if (dias < 0) {
            return 0;
        }
        return dias;
    }

    static double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    private void verificarDesconto(double desconto) {
        if (!Double.isFinite(desconto) || desconto < 0 || desconto > 1) {
            throw new IllegalArgumentException("Desconto deve ser entre 0 e 1.");
        }
    }

    public double calcularMulta() {
        return calcularMulta(0);
    }

    public double calcularMulta(double desconto) {
        verificarDesconto(desconto);

        double multa;
        if (ativo) {
            multa = getDiasAtraso() * membro.getCategoria().getMulta();
            multa = multa * (1 - membro.getCategoria().getDescontoMulta());
        } else {
            multa = multaFinal;
        }

        multa = multa * (1 - desconto);
        return arredondar(multa);
    }

    public double getMultaPendente() {
        if (!ativo) {
            return 0;
        }

        double pendente = calcularMulta() - multaQuitada;
        if (pendente < 0) {
            return 0;
        }
        return arredondar(pendente);
    }

    public double pagarMulta() {
        return pagarMulta(0);
    }

    public double pagarMulta(double desconto) {
        verificarDesconto(desconto);
        double pendente = getMultaPendente();
        double pagamento = arredondar(pendente * (1 - desconto));

        multaQuitada = arredondar(multaQuitada + pendente);
        sistema.registrarMultaPaga(pagamento);
        return pagamento;
    }

    public double calcularTaxaRenovacao() {
        return valorRenovacao;
    }

    public double calcularTaxaRenovacao(String cupom) {
        if ("LEITOR10".equals(cupom)) {
            return arredondar(valorRenovacao * 0.90);
        }
        if ("RENOVACAO20".equals(cupom)) {
            return arredondar(valorRenovacao * 0.80);
        }
        return valorRenovacao;
    }

    public boolean renovar() {
        return renovar(null);
    }

    public boolean renovar(String cupom) {
        if (!ativo) {
            System.out.println("Esse emprestimo ja foi devolvido.");
            return false;
        }
        if (!membro.getCategoria().getRenovar()) {
            System.out.println("Essa categoria nao pode renovar.");
            return false;
        }
        if (getDiasAtraso() > 0) {
            System.out.println("Livro atrasado nao pode ser renovado.");
            return false;
        }

        dataPrevista = dataPrevista.plusDays(7);
        System.out.println("Renovado ate: " + dataPrevista);
        System.out.printf("Taxa: R$ %.2f%n", calcularTaxaRenovacao(cupom));
        return true;
    }

    public boolean devolver() {
        if (!ativo) {
            System.out.println("Esse emprestimo ja foi devolvido.");
            return false;
        }
        if (membro.getMultaPendente() > 0) {
            System.out.println("Pague as multas pendentes antes de devolver.");
            return false;
        }

        livro.devolver();
        multaFinal = calcularMulta();
        dataDevolucao = sistema.hoje();
        ativo = false;
        System.out.println("Livro devolvido: " + livro.getTitulo());
        return true;
    }

    public final void exibir() {
        System.out.println("\nEmprestimo: " + id);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Membro: " + membro.getNome());
        System.out.println("Data: " + dataEmprestimo);
        System.out.println("Devolucao prevista: " + dataPrevista);
        System.out.println("Status: " + getStatus());
        if (getDiasAtraso() > 0) {
            System.out.println("Dias em atraso: " + getDiasAtraso());
        }
        System.out.printf("Multa atual: R$ %.2f%n", getMultaPendente());
    }
}
