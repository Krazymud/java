import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Calculator extends JFrame{
    private JPanel p = new JPanel(new GridLayout(2,5,3,3));
    private String btnText[] = {"+", "-", "*", "/", "OK"};
    private String tText[] = {"", "", "", "=", ""};
    public Calculator(String s){
        super(s);
        setLayout(new BorderLayout());
        JTextField text[];
        JButton btn[];
        btn = new JButton[btnText.length];
        text = new JTextField[tText.length];
        /*set display-mode for testfield*/
        for(int i = 0; i < tText.length; i++){  
            text[i] = new JTextField(tText[i]);
            text[i].setHorizontalAlignment(JTextField.CENTER);
            p.add(text[i]);
        }
        text[1].setEditable(false);
        text[3].setEditable(false);
        text[4].setEditable(false);
        for(int i = 0; i < btnText.length - 1; i++){    
            btn[i] = new JButton(btnText[i]);
            String str = btnText[i];
            /*register events for btn*/
            btn[i].addActionListener(new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e){
                    text[1].setText(str);
                }
            });
            p.add(btn[i]);
        }
        btn[btnText.length - 1] = new JButton(btnText[btnText.length - 1]);
        //register events for btn
        btn[btnText.length - 1].addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if inputfield has been filled
                if(text[0].getText().equals("") || text[1].getText().equals("") ||
                    text[2].getText().equals("")) {
                    JOptionPane.showConfirmDialog(null, "输入不能为空", "错误", JOptionPane.DEFAULT_OPTION);
                }
                //check if input is digit
                else if(!isNumeric(text[0].getText()) || !isNumeric(text[2].getText())){
                    JOptionPane.showConfirmDialog(null, "输入必须为数字", "错误", JOptionPane.DEFAULT_OPTION);
                }
                //calculate
                else{
                    double num1 = Double.valueOf(text[0].getText());
                    double num2 = Double.valueOf(text[2].getText());
                    text[4].setText(String.valueOf(cal(text[1].getText(), num1, num2)));
                }
            }
        });
        p.add(btn[btnText.length - 1]);
        getContentPane().add(p, BorderLayout.CENTER);
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    //func to calculate the result
    private double cal(String text, double num1, double num2){  
        switch(text){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0.0;
        }
    }
    public static void main(String[] args){
        new Calculator("calculator");
    }
    //check if a str contains only digits
    public static boolean isNumeric(String str){    
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}