import java.util.ArrayList;

public class Banco implements IImprimir {
    public static ArrayList<ContaBancaria> contasBancarias = new ArrayList<>();

    public void inserirConta(ContaBancaria conta) {
        contasBancarias.add(conta);
    }

    public void removerConta(ContaBancaria conta) {
        contasBancarias.remove(conta);
        contasBancarias.remove(conta);
    }

    public ContaBancaria procurarConta(int id) {
        for (ContaBancaria contaBancaria : contasBancarias) {
            if (contaBancaria.getNumeroConta() == id) {
                return contaBancaria;
            }
        }
        return null;
    }

    @Override
    public String mostrarDados() {
        for (ContaBancaria contaBancaria : contasBancarias) {
            System.out.println(contaBancaria.mostrarDados());
        }
        return null;
    }
}
