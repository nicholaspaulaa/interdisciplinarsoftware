package view;

import controller.CalculadoraController;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGPONFrame extends JFrame {
    public CalculadoraGPONFrame() {
        setTitle("Link Budget GPON");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));
    }
}
