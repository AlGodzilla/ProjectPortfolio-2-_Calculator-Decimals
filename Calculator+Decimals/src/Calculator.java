import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;

public class Calculator extends JFrame implements ActionListener, MouseListener  {

    //variabel untukk angka sampai 1000 decimal
    private DecimalFormat DF = new DecimalFormat("#,###.00");

    //variabel array buat tombol
    private String[] symbols = {
            "Clear", "+/-", "%", "/",
            "7","8","9","*",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","ADV","="

    };


    //operator
    private int operator = 0;
    //panel background
    private JPanel panel = new JPanel(new BorderLayout(5,5));
    //panel tombol2
    private JPanel btnPanel = new JPanel(new GridLayout(5,3, 2 , 2));
    //tombol masuk sesuai array ada 20 tombol
    private JButton[] bnykBtn = new JButton[20];
    //textfield input
    private JTextArea display = new JTextArea(5,40);
    private double pertaAngka = 0, keduaAngka = 0;
    private JTextField kalkulasiTF = new JTextField(40);

    public Calculator(){
        initiliazte();
    }

    private void initiliazte(){

        display.setFont(new Font("Times New Roman", Font.BOLD,30));


        //untuk warna gui
        display.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        btnPanel.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);


        for(int i = 0; i<bnykBtn.length;i++){
            bnykBtn[i] = new JButton(symbols[i]);

            bnykBtn[i].setOpaque(false);
            bnykBtn[i].setBorderPainted(false);
            bnykBtn[i].setBackground(Color.WHITE);
            bnykBtn[i].setForeground(Color.BLUE);
            bnykBtn[i].addActionListener(this);
            btnPanel.add(bnykBtn[i]);

            int finalI = i;
            bnykBtn[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    bnykBtn[finalI].setBackground(UIManager.getColor("control"));
                }

                public void mouseExited(MouseEvent evt) {
                    bnykBtn[finalI].setBackground(Color.GREEN);


                }
            });


        }





        kalkulasiTF.setForeground(Color.BLACK);
        kalkulasiTF.setBackground(Color.WHITE);
        panel.add(kalkulasiTF, BorderLayout.SOUTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(display, BorderLayout.NORTH);
        add(panel);
        setSize(340,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        new Calculator();
    }

    public void highlightButtons(Point cursor) {
        for (int i = 0; i < bnykBtn.length; i++) {
            JButton button = bnykBtn[i];
            Point buttonLocation = button.getLocationOnScreen();
            double west = buttonLocation.getX();
            double east = buttonLocation.getX() + button.getWidth();
            double north = buttonLocation.getY();
            double south = buttonLocation.getY() + button.getHeight();
            boolean inRow = cursor.getX() > west && cursor.getX() < east;
            boolean inCol = cursor.getY() > north && cursor.getY() < south;
            boolean inBounds = inRow || inCol;
            button.setBackground(inBounds ? new Color(0x08FF00) : null);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {



        //input angka dan "."
        String cmd = e.getActionCommand().toString();

        switch (cmd){
            case".":
                if (!display.getText().contains(".")){
                    display.setText(display.getText() + ".");
                }
                break;
            case"0":
                display.setText(display.getText() + "0");
                break;
            case "1":
                display.setText(display.getText() + "1");

                break;
            case "2":
                display.setText(display.getText() + "2");

                break;
            case "3":
                display.setText(display.getText() + "3");

                break;
            case "4":
                display.setText(display.getText() + "4");

                break;
            case "5":
                display.setText(display.getText() + "5");

                break;
            case "6":
                display.setText(display.getText() + "6");

                break;
            case "7":
                display.setText(display.getText() + "7");

                break;
            case "8":
                display.setText(display.getText() + "8");

                break;
            case "9":
                display.setText(display.getText() + "9");

                break;

            case"+":
                if(!display.getText().isEmpty()){
                    pertaAngka = Double.parseDouble(display.getText().toString());
                    operator = 1;
                    display.setText("");
                }
                break;
            case"-":
                if(!display.getText().isEmpty()){
                    pertaAngka = Double.parseDouble(display.getText().toString());
                    operator = 2;
                    display.setText("");
                }
                break;
            case"*":
                if(!display.getText().isEmpty()){
                    pertaAngka = Double.parseDouble(display.getText().toString());
                    operator = 3;
                    display.setText("");
                }
                break;

            case"/":
                if(!display.getText().isEmpty()){
                    pertaAngka = Double.parseDouble(display.getText().toString());
                    operator = 4;
                    display.setText("");
                }
                break;

            case"%":

                double angka = Double.parseDouble(display.getText().toString());
                display.setText(String.valueOf(angka/100.0));

                break;
            case"+/-":
                double negatif = Double.parseDouble(display.getText().toString());
                negatif*=-1;
                display.setText(String.valueOf(negatif));

                break;
                case"Clear":
                display.setText("");

                break;


            default:
        }

        if (cmd.equalsIgnoreCase("=")){
            if(!display.getText().isEmpty()){
                keduaAngka = Double.parseDouble(display.getText().toString());
                switch (operator){
                    case 1: //tambah
                        display.setText(String.valueOf(pertaAngka + keduaAngka));
                        kalkulasiTF.setText(String.valueOf(pertaAngka + "+" + keduaAngka + "=" + (DF.format(pertaAngka * keduaAngka))));
                        break;
                    case 2: //kkurang
                        display.setText(String.valueOf(pertaAngka - keduaAngka));
                        kalkulasiTF.setText(String.valueOf(pertaAngka + "-" + keduaAngka + "=" + (DF.format(pertaAngka * keduaAngka))));
                        break;
                    case 3: //perkalian
                        display.setText(String.valueOf(pertaAngka * keduaAngka));
                        kalkulasiTF.setText(String.valueOf(pertaAngka + "*" + keduaAngka + "=" + (DF.format(pertaAngka * keduaAngka))));
                        break;
                    case 4: //pembagian
                        display.setText(String.valueOf(pertaAngka / keduaAngka));
                        kalkulasiTF.setText(String.valueOf(pertaAngka + "/" + keduaAngka + "=" + (DF.format(pertaAngka / keduaAngka))));
                        break;

                    default:
                }
            }
        }




    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {
        highlightButtons(event.getLocationOnScreen());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
