import java.util.Scanner;

public class ContaBancaria {
    private final Scanner scanner = new Scanner(System.in);

    private double saldo;
    private final double limiteChequeEspecial;
    private double valorUsadoChequeEspecial;

    private boolean usadoChequeEspecial;

    // Construtor que define o saldo inicial e o limite do cheque especial conforme regras
    public ContaBancaria(double depositoInicial) {
        this.saldo = depositoInicial;
        if (depositoInicial <= 500) {
            this.limiteChequeEspecial = 50;
        } else {
            this.limiteChequeEspecial = depositoInicial * 0.5;
        }
        this.valorUsadoChequeEspecial= 0;
        this.usadoChequeEspecial = false;
    }
    
    // Método para sacar valor, considerando saldo + limite do cheque especial
    public void sacar() {
        System.out.println("Digite o valor que deseja sacar:");
        double valorSaque = scanner.nextDouble();

        double saldoDisponivel = saldo + (limiteChequeEspecial - valorUsadoChequeEspecial);

        if (valorSaque > saldoDisponivel) {
            System.out.println("Saldo insuficiente, incluindo limite do cheque especial.");
        } else {
            saldo -= valorSaque;
            atualizarUsoChequeEspecial();
            System.out.println("Saque realizado com sucesso.");
        }
        System.out.println("Saldo atual: " + saldo);
    }

    // Método para depositar valor, que reduz primeiro o uso do cheque especial se houver
    public void depositar() {
        System.out.println("Digite o valor que deseja depositar:");
        double valorDeposito = scanner.nextDouble();

        saldo += valorDeposito;

        if (valorUsadoChequeEspecial > 0) {
            if (saldo >= valorUsadoChequeEspecial) {
                saldo -= valorUsadoChequeEspecial;
                valorUsadoChequeEspecial = 0;
                usadoChequeEspecial = false;
            } else {
                valorUsadoChequeEspecial -= saldo;
                saldo = 0;
            }
        }

        System.out.println("Saldo atual: " + saldo);
    }

    // Método para pagar boleto, considerando saldo + limite do cheque especial
    public void pagarBoleto() {
        System.out.println("Digite o valor do boleto:");
        double valorBoleto = scanner.nextDouble();

        double saldoDisponivel = saldo + (limiteChequeEspecial - valorUsadoChequeEspecial);

        if (valorBoleto > saldoDisponivel) {
            System.out.println("Saldo insuficiente, incluindo limite do cheque especial.");
        } else {
            saldo -= valorBoleto;
            atualizarUsoChequeEspecial();
            System.out.println("Boleto pago com sucesso.");
        }
        System.out.println("Saldo atual: " + saldo);
    }

    // Método para consultar saldo atual
    public void consultarSaldo() {
        System.out.println("Saldo atual: " + saldo);
    }

    // Método para consultar limite disponível do cheque especial
    public void consultarChequeEspecial() {
        double limiteDisponivel = limiteChequeEspecial - valorUsadoChequeEspecial;
        System.out.println("Limite do cheque especial disponível: " + limiteDisponivel);
        if (usadoChequeEspecial) {
            System.out.println("Cheque especial usado: " + valorUsadoChequeEspecial);
        } else {
            System.out.println("Cheque especial não usado.");
        }
    }

    // Método para cobrar taxa de 20% sobre o valor usado do cheque especial
    public void cobrarTaxaChequeEspecial() {
        if (usadoChequeEspecial && valorUsadoChequeEspecial > 0) {
            double taxa = valorUsadoChequeEspecial * 0.20;
            saldo -= taxa;
            System.out.println("Taxa de 20% sobre o cheque especial usado cobrada: " + taxa);
            System.out.println("Saldo atual após cobrança da taxa: " + saldo);
        } else {
            System.out.println("Nenhum cheque especial usado, nenhuma taxa cobrada.");
        }
    }

    // Método auxiliar para atualizar o uso do cheque especial após operações que alteram saldo
    private void atualizarUsoChequeEspecial() {
        if (saldo < 0) {
            usadoChequeEspecial = true;
            valorUsadoChequeEspecial = -saldo;
        } else {
            usadoChequeEspecial = false;
            valorUsadoChequeEspecial = 0;
        }
    }

    public void usarChequeEspecial() {
        System.out.println("Usar cheque especial?");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("sim")){
            System.out.println("Digite o valor que deseja usar do cheque especial:");
            double valorUsar = scanner.nextDouble();
            if (valorUsar <= limiteChequeEspecial) {
                saldo -= valorUsar;
                valorUsadoChequeEspecial += valorUsar;
                usadoChequeEspecial = true;
                System.out.println("Cheque especial usado com sucesso.");
                cobrarTaxaChequeEspecial();
            } else {
                System.out.println("Valor excede o limite do cheque especial.");
            }
        } else if (resposta.equalsIgnoreCase("não")){
            System.out.println("Operação cancelada.");
        }
    }
}