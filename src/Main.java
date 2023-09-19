import java.util.Random;
import java.util.Scanner;

public class Main {
    static Banco banco = new Banco();

    static ContaBancaria logado;

    static Scanner sc = new Scanner(System.in);

    static Random random = new Random();

    public static void main(String[] args) {
        menuInicial();
    }

    private static void menuInicial() {
        int opcao;
        do {
            System.out.println("""
                    Bem vindo ao Banco
                    [1] - Logar
                    [2] - Cadastrar conta
                    [3] - Excluir conta
                    [4] - Gerar relatório
                    [5] - Sair""");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    menuLogin();
                    break;
                case 2:
                    menuCadastro();
                    break;
                case 3:
                    menuExcluirConta();
                    break;
                case 4:
                    menuGerarRelatorio();
                    break;
                case 5:
                    System.out.println("Encerrando aplicação");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 3);
    }

    private static void menuCadastro() {
        int opcao;
        do {
            System.out.println("""
                    Qual o tipo de conta que deseja cadastrar
                    [1] - Conta corrente
                    [2] - Conta poupança
                    [3] - Voltar""");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    int numero = random.nextInt(999);
                    System.out.println("Número da sua conta");
                    System.out.print("> " + numero + "\n");
                    System.out.println("Qual o saldo inicial");
                    System.out.print("> ");
                    double saldo = sc.nextDouble();
                    System.out.println("Qual a taxa de operação por ação");
                    System.out.print("> ");
                    double taxa = sc.nextDouble();
                    ContaBancaria contaCorrente = new ContaCorrente(numero, saldo, taxa);
                    banco.inserirConta(contaCorrente);
                    System.out.println("Cadastrado(a) com sucesso!");
                    break;
                case 2:
                    int numero1 = random.nextInt(999);
                    System.out.println("Crie um número para sua conta");
                    System.out.print("> " + numero1 + "\n");
                    System.out.println("Qual o saldo inicial");
                    System.out.print("> ");
                    double saldo1 = sc.nextDouble();
                    System.out.println("Qual o limite da conta");
                    System.out.print("> ");
                    double limite = sc.nextDouble();
                    ContaBancaria contaPoupanca = new ContaPoupanca(numero1, saldo1, limite);
                    banco.inserirConta(contaPoupanca);
                    System.out.println("Cadastrado(a) com sucesso!");
                    break;
                case 3:
                    menuInicial();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 3);
    }

    private static void menuLogin() {
        System.out.println("Qual o número da conta");
        System.out.print("> ");
        int id = sc.nextInt();
        if (banco.procurarConta(id) != null) {
            logado = banco.procurarConta(id);
            menuLogado();
        } else {
            System.out.println("Conta não encontrada");
        }
    }

    private static void menuLogado() {
        int opcao;
        do {
            System.out.println("""
                    Qual ação deseja realizar?
                    [1] - Depositar
                    [2] - Sacar
                    [3] - Transferir
                    [4] - Gerar relatório
                    [5] - Voltar""");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    depositar();
                    break;
                case 2:
                    sacar();
                    break;
                case 3:
                    transferir();
                    break;
                case 4:
                    relatorioContaEspecifica();
                    break;
                case 5:
                    menuInicial();
                    break;
                default:
                    System.out.println("Erro");
            }
        } while (opcao != 3);
    }

    private static void menuGerarRelatorio() {
        banco.mostrarDados();
    }

    private static void menuExcluirConta() {
        System.out.println("Qual o número da conta que deseja excluir? ");
        System.out.print("> ");
        int numero = sc.nextInt();
        if (banco.procurarConta(numero) != null) {
            banco.removerConta(banco.procurarConta(numero));
            System.out.println("Conta excluida!");
        } else {
            System.out.println("Conta não encontrada");
        }
        menuInicial();
    }

    private static void relatorioContaEspecifica() {
        System.out.println("Qual o número da conta que deseja gerar o relatorio? ");
        System.out.print("> ");
        int numero = sc.nextInt();
        if (banco.procurarConta(numero) != null) {
            System.out.println(Relatorio.gerarRelatorio(banco.procurarConta(numero)));
        } else {
            System.out.println("Conta não encontrada");
        }
        menuLogado();
    }

    private static void transferir() {
        System.out.println("Qual o número da conta que deseja fazer uma transferencia? ");
        System.out.print("> ");
        int numero = sc.nextInt();
        if (banco.procurarConta(numero) != null) {
            System.out.println("Quanto você deseja transferir");
            System.out.print("> ");
            double quantia = sc.nextDouble();
            logado.transferir(quantia, numero);
        } else {
            System.out.println("Conta não encontrada");
        }
        menuLogado();
    }

    private static void sacar() {
        System.out.println("Quanto você deseja sacar");
        System.out.print("> ");
        int quantia = sc.nextInt();
        if (quantia > 0) {
            logado.sacar(quantia);
        } else {
            System.out.println("Erro");
        }
        menuLogado();
    }

    private static void depositar() {
        System.out.println("Quanto você deseja depositar");
        System.out.print("> ");
        int quantia = sc.nextInt();
        if (quantia > 0) {
            logado.depositar(quantia);
        } else {
            System.out.println("Erro");
        }
        menuLogado();
    }
}