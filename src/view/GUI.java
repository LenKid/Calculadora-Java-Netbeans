package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import model.Calculadora;

public class GUI extends JFrame implements ActionListener {

    Panel buttonsPanel;
    Panel screenPanel;
    JTextArea screenArea;
    Container content;
    FlowLayout flow;
    GridLayout gridNumbers;
    Calculadora calc = new Calculadora();
    String operador;

    public GUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("add.png")).getImage());

        calc = new Calculadora();
        content = getContentPane();
        content.setBackground(Color.ORANGE);
        flow = new FlowLayout(FlowLayout.CENTER);
        screenArea = new JTextArea(1, 6);
        screenArea.setEditable(false);
        screenArea.setFont(new java.awt.Font("sansserif", 1, 48));
        screenArea.setBackground(Color.GRAY);
        screenArea.setForeground(Color.WHITE);
        screenArea.setFocusable(false);
        buttonsPanel = new Panel();
        screenPanel = new Panel();
        buttonsPanel.setBackground(Color.BLACK);
        screenPanel.setBackground(Color.black);
        buttonsPanel.setSize(100, 100);
        screenPanel.add(screenArea);
        //content.setLayout(new FlowLayout(FlowLayout.LEADING));

        content.add(screenPanel, BorderLayout.NORTH);
        content.add(buttonsPanel, BorderLayout.CENTER);
        screenArea.setLayout(flow);
        gridNumbers = new GridLayout(0, 4);
        gridNumbers.setHgap(5);
        gridNumbers.setVgap(5);
        buttonsPanel.setLayout(gridNumbers);

        agregarBoton("1");
        agregarBoton("2");
        agregarBoton("3");
        agregarBoton("+");
        agregarBoton("4");
        agregarBoton("5");
        agregarBoton("6");
        agregarBoton("-");
        agregarBoton("7");
        agregarBoton("8");
        agregarBoton("9");
        agregarBoton("*");
        agregarBoton("CE");
        agregarBoton("0");
        agregarBoton("=");
        agregarBoton("/");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton fuente = (JButton) e.getSource();
        String textoBoton = fuente.getText();
        if (screenArea.getText().equals("MathError")) {
                screenArea.setText("");
            }

        if ("CE".equals(textoBoton)) {
            screenArea.setText("");
            calc.setNum1(0);
            calc.setNum2(0);
            operador = null;
        } else if ("=".equals(textoBoton)) {
            calc.setNum2(Double.parseDouble(screenArea.getText()));
            try {
                screenArea.setText(String.valueOf(sinCero(calc.calcularResultado(operador))));
            } catch (ArithmeticException ex) {
                screenArea.setText("MathError");
            }
            operador = null;
        } else if ("+".equals(textoBoton) || "-".equals(textoBoton) || "*".equals(textoBoton) || "/".equals(textoBoton)) {
            if (!screenArea.getText().isEmpty()) {
                calc.setNum1(Double.parseDouble(screenArea.getText()));
                operador = textoBoton;
                screenArea.setText("");
            }
        } else {
            screenArea.append(textoBoton);
        }
    }

    public static void main(String[] args) {
        GUI open = new GUI();
        open.setVisible(true);
    }

    private void agregarBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.addActionListener(this);
        boton.setBackground(Color.DARK_GRAY);
        boton.setForeground(Color.white);
        boton.setFont(new java.awt.Font("sansserif", 3, 24) {
        });
        buttonsPanel.add(boton);
    }

    private String sinCero(Double resultado) {
        String retorno;
        retorno = Double.toString(resultado);
        if (resultado % 1 == 0) {
            retorno = retorno.substring(0, retorno.length() - 2);
        }
        return retorno;
    }
}
