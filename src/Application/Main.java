package Application;

import Entities.AluguelVeiculo;
import Entities.Fatura;
import Entities.Veiculo;
import Services.ImpostoBrasil;
import Services.ServicosAluguel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String modeloCar = sc.nextLine();
        System.out.println("Retirada: (dd/mm/yyyy hh:mm): ");
        LocalDateTime retirada = LocalDateTime.parse(sc.nextLine(), form);
        System.out.println("Retorno: (dd/mm/yyyy hh:mm): ");
        LocalDateTime retorno = LocalDateTime.parse(sc.nextLine(), form);

        AluguelVeiculo av = new AluguelVeiculo(retirada, retorno, new Veiculo(modeloCar));

        System.out.print("Entre com o preço por hora: ");
        Double precoHora = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        Double precoDia = sc.nextDouble();

        ServicosAluguel servicosAluguel = new ServicosAluguel(precoHora, precoDia,  new ImpostoBrasil());
        servicosAluguel.processarFatura(av);

        System.out.println("Fatura: ");
        System.out.println("Pagamento basico: " + av.getFatura().getPagamentoBasico());
        System.out.println("Impoato: " + av.getFatura().getImposto());
        System.out.println("Pagamento total: " + av.getFatura().getPagamentoTotal());



        sc.close();

    }
}