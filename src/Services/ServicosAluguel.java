package Services;

import Entities.AluguelVeiculo;
import Entities.Fatura;

import java.time.Duration;

public class ServicosAluguel {
    private Double precoHora;
    private Double precoDia;

    private ImpostoBrasil impostoServico;

    public ServicosAluguel(Double precoHora, Double precoDia, ImpostoBrasil impostoServico) {
        this.precoHora = precoHora;
        this.precoDia = precoDia;
        this.impostoServico = impostoServico;
    }
    public void processarFatura(AluguelVeiculo aluguelVeiculo){
        double minutos = Duration.between(aluguelVeiculo.getInicio(), aluguelVeiculo.getFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;
        if (horas <= 12.0 ){
            pagamentoBasico = precoHora * Math.ceil(horas);
        }
        else {
            pagamentoBasico = precoDia * Math.ceil(horas / 24.0);
        }

        double imposto = impostoServico.imposto(pagamentoBasico);

        aluguelVeiculo.setFatura(new Fatura(pagamentoBasico, imposto));
    }
}

