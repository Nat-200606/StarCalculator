import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class MyFrame extends JFrame implements ActionListener {
    ButtonBase soma, subtrai, divide, multiplica, potencia,igual, reset, decimal;
    ButtonBase[] numeros = new ButtonBase[10];
    JLabel output;
    char operation;
    MyFrame() throws IOException, FontFormatException {

        Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/Minecraft.ttf")
        ).deriveFont(50f);

        Font fontOut = Font.createFont(
                Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/Minecraft.ttf")
        ).deriveFont(60f);


        URL backGroundURL = getClass().getResource("/starback2.png");
        ImageIcon backGround = new ImageIcon(backGroundURL);

        URL starURL = getClass().getResource("/starbutton.png");
        ImageIcon star = new ImageIcon(starURL);

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,896,896);

        JPanel buttonPanel = new JPanel(new GridLayout(3,6,10,6));
        buttonPanel.setBounds(20,170,840,460);
        buttonPanel.setOpaque(false);

        output = new JLabel("",JLabel.CENTER);
        output.setVerticalAlignment(JLabel.CENTER);
        output.setBackground(Color.decode("#35063E"));
        output.setForeground(Color.orange);
        output.setOpaque(true);
        output.setFocusable(false);
        output.setFont(fontOut);
        output.setBounds(180,40,460,100);

        JLabel back = new JLabel(backGround);
        back.setBounds(0,0,896,896);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(320,40,896,760);
        this.setIconImage(star.getImage());
        this.setResizable(false);

        for (int x = 0; x < 10; x++){
            numeros[x] =new ButtonBase(String.valueOf(x));
            numeros[x].addActionListener(this);
            buttonPanel.add(numeros[x]);
        }
        soma = new ButtonBase("+");
        soma.addActionListener(this);
        subtrai = new ButtonBase("-");
        subtrai.addActionListener(this);
        divide = new ButtonBase("/");
        divide.addActionListener(this);
        multiplica = new ButtonBase("X");
        multiplica.addActionListener(this);
        potencia = new ButtonBase("x^y");
        potencia.addActionListener(this);
        igual = new ButtonBase("=");
        igual.addActionListener(this);
        decimal = new ButtonBase(".");
        decimal.addActionListener(this);

        reset = new ButtonBase("AC");
        reset.addActionListener(this);


        pane.add(back,JLayeredPane.DEFAULT_LAYER);
        pane.add(buttonPanel,JLayeredPane.DRAG_LAYER);
        pane.add(output,JLayeredPane.DRAG_LAYER);
        buttonPanel.add(soma);
        buttonPanel.add(subtrai);
        buttonPanel.add(divide);
        buttonPanel.add(multiplica);
        buttonPanel.add(potencia);
        buttonPanel.add(decimal);
        buttonPanel.add(reset);
        buttonPanel.add(igual);

        this.add(pane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton btn : numeros) {
            if (btn.equals(e.getSource())) {
                output.setText(output.getText() + btn.getText());
                break;
            }
        }
        if (e.getSource().equals(soma)){
            output.setText(output.getText() + "+");
            operation = '+';
            disableOperations();
        }
        if (e.getSource().equals(subtrai)){
            output.setText(output.getText() + "-");
            operation = '-';
            disableOperations();
        }
        if (e.getSource().equals(multiplica)){
            output.setText(output.getText() + "x");
            operation = 'x';
            disableOperations();
        }
        if (e.getSource().equals(divide)){
            output.setText(output.getText() + "/");
            operation = '/';
            disableOperations();
        }
        if (e.getSource().equals(potencia)){
            output.setText(output.getText() + "^");
            operation = '^';
            disableOperations();
        }
        if (e.getSource().equals(reset)){
            output.setText("");
            enableOperations();
        }
        if (e.getSource().equals(decimal)){
            output.setText(output.getText()+".");
        }
        if (e.getSource().equals(igual)){
            String text = output.getText();
            double n1 = Double.parseDouble(text.substring(0,text.indexOf(operation)));
            double n2 = Double.parseDouble(text.substring(text.indexOf(operation)+1));
            enableOperations();
            switch (operation){
                case '+':
                    output.setText(String.valueOf(n1+n2));
                    break;
                case '-':
                    output.setText(String.valueOf(n1-n2));
                    break;
                case 'x':
                    output.setText(String.valueOf(n1*n2));
                    break;
                case '/':
                    output.setText(String.valueOf(n1/n2));
                    break;
                case '^':
                    output.setText(String.valueOf(Math.pow(n1,n2)));
                    break;

            }
        }
    }

    void disableOperations(){
        soma.setEnabled(false);
        subtrai.setEnabled(false);
        multiplica.setEnabled(false);
        divide.setEnabled(false);
        potencia.setEnabled(false);
    }

    void enableOperations(){
        soma.setEnabled(true);
        subtrai.setEnabled(true);
        multiplica.setEnabled(true);
        divide.setEnabled(true);
        potencia.setEnabled(true);
    }
}
