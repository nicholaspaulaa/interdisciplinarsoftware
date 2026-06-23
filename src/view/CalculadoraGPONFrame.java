package view;

import controller.CalculadoraController;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGPONFrame extends JFrame {
    private JTextField txtPtx = new JTextField();
    private JTextField txtPrx = new JTextField();
    private JTextField txtDist = new JTextField();
    private JTextField txtAten = new JTextField("0.25");

    public CalculadoraGPONFrame() {
        setTitle("Link Budget GPON");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel(" Potência Ptx (dBm):")); add(txtPtx);
        add(new JLabel(" Sensibilidade Prx (dBm):")); add(txtPrx);
        add(new JLabel(" Distância (km):")); add(txtDist);
        add(new JLabel(" Atenuação Fibra:")); add(txtAten);
    }
}
