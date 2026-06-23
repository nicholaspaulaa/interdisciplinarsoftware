package view;

import controller.CalculadoraController;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGPONFrame extends JFrame {
    private JTextField txtPtx = new JTextField(), txtPrx = new JTextField(), txtDist = new JTextField();
    private JTextField txtAten = new JTextField("0.25"), txtMargem = new JTextField("3.0");
    private JTextField txtSplitters = new JTextField(), txtConectores = new JTextField(), txtFusoes = new JTextField();
    private JButton btnCalcular = new JButton("Calcular Faltante");
    private CalculadoraController controller = new CalculadoraController();

    public CalculadoraGPONFrame() {
        setTitle("Link Budget GPON");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel(" Ptx (dBm):")); add(txtPtx);
        add(new JLabel(" Prx (dBm):")); add(txtPrx);
        add(new JLabel(" Distância (km):")); add(txtDist);
        add(new JLabel(" Atenuação (dB/km):")); add(txtAten);
        add(new JLabel(" Margem (dB):")); add(txtMargem);
        add(new JLabel(" Perda Splitters (dB):")); add(txtSplitters);
        add(new JLabel(" Perda Conectores (dB):")); add(txtConectores);
        add(new JLabel(" Perda Fusões (dB):")); add(txtFusoes);
        add(new JLabel(" * Deixe um campo vazio")); add(btnCalcular);

        btnCalcular.addActionListener(e -> {
            try {
                String resp = controller.processarCalculo(
                    formatarInput(txtPtx.getText()), formatarInput(txtPrx.getText()), formatarInput(txtMargem.getText()),
                    formatarInput(txtDist.getText()), formatarInput(txtAten.getText()), formatarInput(txtSplitters.getText()),
                    formatarInput(txtConectores.getText()), formatarInput(txtFusoes.getText())
                );
                JOptionPane.showMessageDialog(this, resp, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro de digitação: Use apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Double formatarInput(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;
        return Double.parseDouble(texto.replace(",", "."));
    }
}
