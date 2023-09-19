public abstract class ContaBancaria implements IImprimir {
    private int numeroConta;
    private double saldo;

    public abstract double sacar(double x);

    public abstract double depositar(double x);

    public abstract double transferir(double x, int id);

    public ContaBancaria(int numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    @Override
    public String toString() {
        return "Número da Conta: " + numeroConta + "\n" +
                "Saldo disponivel: " + saldo;
    }
}
