import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o valor do depósito inicial para criar a conta:");
        double depositoInicial = scanner.nextDouble();
        ContaBancaria contaBancaria = new ContaBancaria(depositoInicial);

        Menu menu = new Menu();

        int opcao;

        do {
            menu.exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    contaBancaria.sacar();
                    break;
                case 2:
                    contaBancaria.depositar();
                    break;
                case 3:
                    contaBancaria.pagarBoleto();
                    break;
                case 4:
                    contaBancaria.consultarSaldo();
                    break;
                case 5:
                    contaBancaria.usarChequeEspecial();
                    break;
                case 6:
                    contaBancaria.consultarChequeEspecial();
                    break;
                case 7:
                    menu.exibirSair();
                    break;
                default:
                    menu.exibirErro();
            }
        } while (opcao != 7);

    }
}