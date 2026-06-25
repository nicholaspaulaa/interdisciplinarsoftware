package view;

import controller.CalculadoraController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculadoraGPONFrame extends JFrame {
    private static final Color COR_FUNDO = new Color(45, 45, 45);
    private static final Color COR_CAMPO = new Color(60, 60, 60);
    private static final Color COR_BORDA = new Color(100, 100, 100);
    private static final Color COR_LARANJA = new Color(255, 122, 0);
    private static final Color COR_LARANJA_PRESS = new Color(220, 95, 0);
    private static final Color COR_VERMELHO = new Color(220, 50, 50);
    private static final Color COR_VERMELHO_PRESS = new Color(180, 30, 30);
    private static final Color COR_TEXTO = new Color(230, 230, 230);
    private static final Color COR_VISOR = new Color(30, 30, 30);
    private static final Font FONTE = new Font("Helvetica", Font.BOLD, 13);
    private static final Font FONTE_VISOR = new Font("Helvetica", Font.BOLD, 20);

    private VisorCalculadora visor = new VisorCalculadora();
    private CampoArredondado txtPtx = new CampoArredondado();
    private CampoArredondado txtPrx = new CampoArredondado();
    private CampoArredondado txtDist = new CampoArredondado();
    private CampoArredondado txtAten = new CampoArredondado("0,25");
    private CampoArredondado txtMargem = new CampoArredondado("3,0");
    private CampoArredondado txtSplitters = new CampoArredondado();
    private CampoArredondado txtConectores = new CampoArredondado();
    private CampoArredondado txtFusoes = new CampoArredondado();
    private BotaoArredondado btnCalcular = new BotaoArredondado("Calcular Faltante", COR_LARANJA, COR_LARANJA_PRESS);
    private BotaoArredondado btnLimpar = new BotaoArredondado("Limpar", COR_VERMELHO, COR_VERMELHO_PRESS);
    private CalculadoraController controller = new CalculadoraController();

    public CalculadoraGPONFrame() {
        setTitle("Link Budget GPON");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(0, 12));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(20, 24, 20, 24));

        JPanel topo = new JPanel(new BorderLayout(0, 10));
        topo.setOpaque(false);
        topo.add(visor, BorderLayout.NORTH);

        JLabel dica = criarLabel("* Deixe um campo vazio (perdas vazias = 0 dB)");
        dica.setHorizontalAlignment(SwingConstants.CENTER);
        topo.add(dica, BorderLayout.SOUTH);
        painel.add(topo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(9, 2, 10, 10));
        formulario.setOpaque(false);
        formulario.add(criarRotuloComDescricao("Ptx (dBm):", "Potência de transmissão"));
        formulario.add(txtPtx);
        formulario.add(criarRotuloComDescricao("Prx (dBm):", "Sensibilidade de recepção"));
        formulario.add(txtPrx);
        formulario.add(criarLabel("Distância (km):"));
        formulario.add(txtDist);
        formulario.add(criarLabel("Atenuação (dB/km):"));
        formulario.add(txtAten);
        formulario.add(criarLabel("Margem (dB):"));
        formulario.add(txtMargem);
        formulario.add(criarLabel("Splitters (dB ou 1:64):"));
        formulario.add(txtSplitters);
        formulario.add(criarLabel("Perda Conectores (dB):"));
        formulario.add(txtConectores);
        formulario.add(criarLabel("Perda Fusões (dB):"));
        formulario.add(txtFusoes);
        formulario.add(centralizarBotao(btnLimpar));
        formulario.add(centralizarBotao(btnCalcular));

        painel.add(formulario, BorderLayout.CENTER);
        getContentPane().setBackground(COR_FUNDO);
        add(painel);

        btnCalcular.addActionListener(e -> calcular());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private JPanel centralizarBotao(JButton botao) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painel.setOpaque(false);
        painel.add(botao);
        return painel;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE);
        label.setForeground(COR_TEXTO);
        label.setBackground(COR_FUNDO);
        label.setOpaque(true);
        return label;
    }

    private JPanel criarRotuloComDescricao(String titulo, String descricao) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.add(criarLabel(titulo));
        painel.add(criarLabelDescricao(descricao));
        return painel;
    }

    private JLabel criarLabelDescricao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Helvetica", Font.PLAIN, 11));
        label.setForeground(new Color(160, 160, 160));
        label.setBackground(COR_FUNDO);
        label.setOpaque(true);
        return label;
    }

    private void calcular() {
        try {
            String resp = controller.processarCalculo(
                formatarInput(txtPtx.getText()), formatarInput(txtPrx.getText()), formatarInput(txtMargem.getText()),
                formatarInput(txtDist.getText()), formatarInput(txtAten.getText()), formatarSplitter(txtSplitters.getText()),
                formatarInput(txtConectores.getText()), formatarInput(txtFusoes.getText())
            );
            visor.atualizar(resp);
        } catch (Exception ex) {
            visor.atualizar("Erro de digitação: Use números ou splitter 1:64.");
        }
    }

    private void limparCampos() {
        txtPtx.setText("");
        txtPrx.setText("");
        txtDist.setText("");
        txtAten.setText("0,25");
        txtMargem.setText("3,0");
        txtSplitters.setText("");
        txtConectores.setText("");
        txtFusoes.setText("");
        visor.limpar();
        txtPtx.requestFocusInWindow();
    }

    private Double formatarInput(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;
        return Double.parseDouble(texto.replace(",", "."));
    }

    private Double formatarSplitter(String texto) {
        if (texto == null || texto.trim().isEmpty()) return null;

        String valor = texto.trim().replace(",", ".");
        if (valor.contains(":")) {
            String[] partes = valor.split(":");
            if (partes.length != 2) throw new NumberFormatException("Razão de splitter inválida");

            double entrada = Double.parseDouble(partes[0].trim());
            double saida = Double.parseDouble(partes[1].trim());
            if (entrada <= 0 || saida <= 0 || saida < entrada) throw new NumberFormatException("Razão de splitter inválida");

            return 10.0 * Math.log10(saida / entrada);
        }

        return Double.parseDouble(valor);
    }

    private static class VisorCalculadora extends JComponent {
        private String texto = "";

        public void atualizar(String resultado) {
            if (resultado.startsWith("ERRO") || resultado.startsWith("ALERTA")) {
                texto = resultado;
            } else {
                texto = resultado.replace(": ", " = ");
            }
            repaint();
        }

        public void limpar() {
            texto = "";
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 58);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COR_VISOR);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            g2.setColor(COR_BORDA);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);

            if (!texto.isEmpty()) {
                g2.setFont(FONTE_VISOR);
                g2.setColor(COR_TEXTO);
                FontMetrics fm = g2.getFontMetrics();
                int x = getWidth() - fm.stringWidth(texto) - 16;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(texto, Math.max(x, 16), y);
            }
            g2.dispose();
        }
    }

    private static class CampoArredondado extends JTextField {
        public CampoArredondado() {
            this("");
        }

        public CampoArredondado(String texto) {
            super(texto);
            setFont(FONTE);
            setForeground(COR_TEXTO);
            setCaretColor(COR_TEXTO);
            setOpaque(false);
            setBorder(new EmptyBorder(8, 12, 8, 12));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COR_CAMPO);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            g2.setColor(isFocusOwner() ? COR_BORDA : new Color(80, 80, 80));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class BotaoArredondado extends JButton {
        private final Color corNormal;
        private final Color corPressionada;

        public BotaoArredondado(String texto, Color corNormal, Color corPressionada) {
            super(texto);
            this.corNormal = corNormal;
            this.corPressionada = corPressionada;
            setFont(FONTE);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Dimension tamanho = new Dimension(180, 38);
            setPreferredSize(tamanho);
            setMaximumSize(tamanho);
            setMinimumSize(tamanho);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color cor = getModel().isPressed() ? corPressionada : corNormal;
            if (!isEnabled()) cor = new Color(80, 80, 80);

            g2.setColor(cor);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.setColor(getForeground());
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }
}
