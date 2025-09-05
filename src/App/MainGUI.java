package App;




import Entities.AluguelVeiculo;
import Entities.Fatura;
import Entities.Veiculo;
import Services.ImpostoBrasil;
import Services.ServicosAluguel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Aluguel de Veículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 650);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Cabeçalho
        JLabel titleLabel = new JLabel("Aluguel de Veículos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        JLabel subLabel = new JLabel("Preencha os dados abaixo para calcular sua fatura");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subLabel.setForeground(new Color(80, 80, 80));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel de dados do veículo
        JPanel veiculoPanel = new JPanel();
        veiculoPanel.setBackground(new Color(220, 235, 250));
        veiculoPanel.setBorder(BorderFactory.createTitledBorder("Informações do Veículo"));
        veiculoPanel.setLayout(new BoxLayout(veiculoPanel, BoxLayout.Y_AXIS));
        veiculoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        veiculoPanel.setBorder(new EmptyBorder(10,10,10,10));

        JTextField modeloField = criarCampo(veiculoPanel, "Modelo do Carro:");
        JTextField retiradaField = criarCampo(veiculoPanel, "Data de Retirada (dd/MM/yyyy HH:mm):");
        JTextField retornoField = criarCampo(veiculoPanel, "Data de Retorno (dd/MM/yyyy HH:mm):");

        mainPanel.add(veiculoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Painel de preços
        JPanel precoPanel = new JPanel();
        precoPanel.setBackground(new Color(230, 250, 230));
        precoPanel.setBorder(BorderFactory.createTitledBorder("Preços"));
        precoPanel.setLayout(new BoxLayout(precoPanel, BoxLayout.Y_AXIS));
        precoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        precoPanel.setBorder(new EmptyBorder(10,10,10,10));

        JTextField precoHoraField = criarCampo(precoPanel, "Preço por hora (R$):");
        JTextField precoDiaField = criarCampo(precoPanel, "Preço por dia (R$):");

        mainPanel.add(precoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botão calcular
        JButton calcularButton = new JButton("Calcular Fatura");
        calcularButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calcularButton.setBackground(new Color(70, 130, 180));
        calcularButton.setForeground(Color.BLACK);
        calcularButton.setFocusPainted(false);
        calcularButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calcularButton.setMaximumSize(new Dimension(220, 45));
        calcularButton.setBorder(BorderFactory.createLineBorder(new Color(60, 110, 160), 2, true));
        mainPanel.add(calcularButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel do resultado
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setBackground(new Color(255, 255, 255));
        resultadoPanel.setBorder(BorderFactory.createTitledBorder("Fatura"));
        resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));
        resultadoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180,180,180), 1, true),
                new EmptyBorder(10,10,10,10)
        ));

        JLabel resultadoLabel = new JLabel("<html>Preencha os campos e clique em Calcular</html>");
        resultadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultadoLabel.setForeground(new Color(50, 50, 50));
        resultadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoPanel.add(resultadoLabel);

        mainPanel.add(resultadoPanel);

        // Ação do botão
        calcularButton.addActionListener(e -> {
            try {
                DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String modelo = modeloField.getText();
                LocalDateTime retirada = LocalDateTime.parse(retiradaField.getText(), form);
                LocalDateTime retorno = LocalDateTime.parse(retornoField.getText(), form);
                double precoHora = Double.parseDouble(precoHoraField.getText());
                double precoDia = Double.parseDouble(precoDiaField.getText());

                AluguelVeiculo av = new AluguelVeiculo(retirada, retorno, new Veiculo(modelo));
                ServicosAluguel servicosAluguel = new ServicosAluguel(precoHora, precoDia, new ImpostoBrasil());
                servicosAluguel.processarFatura(av);
                Fatura fatura = av.getFatura();

                resultadoLabel.setText("<html>Pagamento Básico: R$ " + String.format("%.2f", fatura.getPagamentoBasico()) +
                        "<br>Imposto: R$ " + String.format("%.2f", fatura.getImposto()) +
                        "<br>Pagamento Total: R$ " + String.format("%.2f", fatura.getPagamentoTotal()) + "</html>");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Método auxiliar para criar campos
    private static JTextField criarCampo(JPanel panel, String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(70, 70, 70));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(5,5,5,5)
        ));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        return field;
    }
}
