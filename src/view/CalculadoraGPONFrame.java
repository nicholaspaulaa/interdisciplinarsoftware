package view;

import controller.CalculadoraController;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGPONFrame extends JFrame {
    private JTextField txtPtx = new JTextField(), txtPrx = new JTextField(), txtDist = new JTextField();
    private JTextField txtAten = new JTextField("0.25"), txtMargem = new JTextField("3.0");
    private JTextField txtSplitters = new JTextField(), txtConectores = new JTextField(), txtFusoes = new JTextField();
    private JButton btnCalcular = new JButton("Calcular Faltante");

    public CalculadoraGPONFrame() {
        setTitle("Link Budget GPON");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel(" Ptx (dBm):")); add(txtPtx);
        add(new JLabel(" Prx (dBm):")); add(txtPrx);
        add(new JLabel(" Distância (km):")); add(txtDist);
        add(new JLabel(" Atenuação:")); add(txtAten);
        add(new JLabel(" Margem:")); add(txtMargem);
        add(new JLabel(" Splitters (dB):")); add(txtSplitters);
        add(new JLabel(" Conectores (dB):")); add(txtConectores);
        add(new JLabel(" Fusões (dB):")); add(txtFusoes);
        add(new JLabel("")); add(btnCalcular);
    }

    private Double formatarInput(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;
        return Double.parseDouble(texto.replace(",", "."));
    }
}
