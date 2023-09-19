public class ContaPoupanca extends ContaBancaria {
    private double limite;

    public ContaPoupanca(int numeroConta, double saldo, double limite) {
        super(numeroConta, saldo);
        this.limite = limite;
    }

    @Override
    public double sacar(double x) {
        double novoValor = 0;
        if (x > getSaldo()) {
            setLimite(limite -= x);
        }
        if (getSaldo() + getLimite() < x) {
            System.out.println("Não foi possivel efetuar o saque");
            return 0;
        } else {
            novoValor = getSaldo() - x;
            setSaldo(novoValor);
            return novoValor;
        }
    }

    @Override
    public double depositar(double x) {
        double novoValor = getSaldo() + x;
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
        return "Conta Poupança" + "\n" +
                "Limite da conta: " + limite + "\n" +
                " " + super.toString();
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
