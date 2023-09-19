public class ContaCorrente extends ContaBancaria {
    private double taxaOperacao;

    public ContaCorrente(int numeroConta, double saldo, double taxaOperacao) {
        super(numeroConta, saldo);
        this.taxaOperacao = taxaOperacao;
    }

    @Override
    public double sacar(double x) {
        double novoValor = (getSaldo() - x) - getTaxaOperacao();
        setSaldo(novoValor);
        return novoValor;
    }

    @Override
    public double depositar(double x) {
        double novoValor = (getSaldo() + x) - getTaxaOperacao();
        setSaldo(novoValor);
        return novoValor;
    }

    @Override
    public double transferir(double x, int id) {
        for (ContaBancaria contaBancaria1 : Banco.contasBancarias) {
            if (contaBancaria1.getNumeroConta() == id) {
                sacar(x);
                contaBancaria1.depositar(x);
            }
        }
        return 0;
    }

    @Override
    public String mostrarDados() {
        return "Conta Corrente" + "\n" +
                "Taxa para realizar operações: " + taxaOperacao + "\n" +
                " " + super.toString();
    }

    public double getTaxaOperacao() {
        return taxaOperacao;
    }
}