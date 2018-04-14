import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator extends JFrame{
	JPanel p = new JPanel(new GridLayout(2,5,3,3));
	JTextField t = new JTextField();
	String btnText[] = {"+", "-", "*", "/", "OK"};
	String tText[] = {"", "", "", "=", ""};
	public Calculator(String s){
		super(s);
		setLayout(new BorderLayout());
		JTextField text[];
		JButton btn[];
		btn = new JButton[btnText.length];
		text = new JTextField[tText.length];
		for(int i = 0; i < tText.length; i++){
			text[i] = new JTextField(tText[i]);
			text[i].setHorizontalAlignment(JTextField.CENTER);
			p.add(text[i]);
		}
		text[1].setEditable(false);
		text[3].setEditable(false);
		text[4].setEditable(false);
		for(int i = 0; i < btnText.length; i++){
			btn[i] = new JButton(btnText[i]);
			if(i < btnText.length - 1){
				String str = btnText[i];
				btn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						text[1].setText(str);
					}
				});
			}
			else{
				btn[i].addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
		                if(text[0].getText().equals("") || text[1].getText().equals("") ||
		                        text[2].getText().equals("")) {
		                    JOptionPane.showConfirmDialog(null, "输入不能为空", "错误", JOptionPane.DEFAULT_OPTION);
		                }
		                else if(isNumeric(text[0].getText()) == false || isNumeric(text[2].getText()) == false){
		                    JOptionPane.showConfirmDialog(null, "输入必须为数字", "错误", JOptionPane.DEFAULT_OPTION);
		                }
		                else{
		                    double num1 = Double.valueOf(text[0].getText());
		                    double num2 = Double.valueOf(text[2].getText());
		                    double res;
		                    switch(text[1].getText()){
		                        case "+":
		                            res = num1 + num2;
		                            break;
		                        case "-":
		                            res = num1 - num2;
		                            break;
		                        case "*":
		                            res = num1 * num2;
		                            break;
		                        case "/":
		                            res = num1 / num2;
		                            break;
		                        default:
		                            res = 0;
		                            break;
		                    }
		                    text[4].setText(String.valueOf(res));
		                }
		            }
				});
			}
			p.add(btn[i]);
		}


		getContentPane().add(p, BorderLayout.CENTER);
		setVisible(true);
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	public static void main(String[] args){
		Calculator cal = new Calculator("calculator");
	}
	public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}