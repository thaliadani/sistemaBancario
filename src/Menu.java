public class Menu {
    public void exibirMenu() {
        System.out.println("Selecione uma opção:");
        System.out.println("1. Sacar Dinheiro");
        System.out.println("2. Depositar Dinheiro");
        System.out.println("3. Pagar Boleto");
        System.out.println("4. Consultar Saldo");
        System.out.println("5. Usar Cheque Especial");
        System.out.println("6. Consultar Cheque Especial");
        System.out.println("7. Sair");
    }

    public void exibirErro() {
        System.out.println("Opção inválida. Tente novamente.");
    }

    public void exibirSair() {
        System.out.println("Saindo do sistema. Até logo!");
    }
}
