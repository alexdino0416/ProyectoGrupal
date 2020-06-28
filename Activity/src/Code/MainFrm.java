package Code;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 13, 326, 575);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextPane tpValidated = new JTextPane();
		tpValidated.setBounds(350, 41, 232, 32);
		panel.add(tpValidated);
		
		JLabel lblNewLabel = new JLabel("Validar Expresion");
		lblNewLabel.setBounds(350, 13, 145, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Suma");
		lblNewLabel_1.setBounds(350, 86, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblResta = new JLabel("Resta");
		lblResta.setBounds(350, 160, 56, 16);
		panel.add(lblResta);
		
		JTextPane tpSum = new JTextPane();
		tpSum.setBounds(350, 115, 232, 32);
		panel.add(tpSum);
		
		JTextPane tpMinus = new JTextPane();
		tpMinus.setBounds(350, 189, 232, 32);
		panel.add(tpMinus);
		
		JLabel lblMultiplicacion = new JLabel("Multiplicacion");
		lblMultiplicacion.setBounds(350, 234, 84, 16);
		panel.add(lblMultiplicacion);
		
		JTextPane tpMultiplication = new JTextPane();
		tpMultiplication.setBounds(350, 263, 232, 32);
		panel.add(tpMultiplication);
		
		JLabel lblDivision = new JLabel("Division");
		lblDivision.setBounds(350, 308, 84, 16);
		panel.add(lblDivision);
		
		JTextPane tpDivision = new JTextPane();
		tpDivision.setBounds(350, 337, 232, 32);
		panel.add(tpDivision);
		
		JLabel lblPotencia = new JLabel("Potencia");
		lblPotencia.setBounds(350, 382, 84, 16);
		panel.add(lblPotencia);
		
		JTextPane tpPower = new JTextPane();
		tpPower.setBounds(350, 411, 232, 32);
		panel.add(tpPower);
		
		JLabel lblParentesis = new JLabel("Apertura de parentesis");
		lblParentesis.setBounds(350, 456, 145, 16);
		panel.add(lblParentesis);
		
		JTextPane tpOpenParenthesis = new JTextPane();
		tpOpenParenthesis.setBounds(350, 485, 232, 32);
		panel.add(tpOpenParenthesis);
		
		JLabel lblCierreDeParentesis = new JLabel("Cierre de parentesis");
		lblCierreDeParentesis.setBounds(350, 530, 145, 16);
		panel.add(lblCierreDeParentesis);
		
		JTextPane tpCloseParenthesis = new JTextPane();
		tpCloseParenthesis.setBounds(350, 559, 232, 32);
		panel.add(tpCloseParenthesis);
		
		JButton btnAnalyze = new JButton("Analizar");
		btnAnalyze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File textFile = new File("text.txt");
				PrintWriter pr;
				try {
					pr = new PrintWriter(textFile);
					pr.print(textField.getText());
					pr.close();
				}
				catch(FileNotFoundException ex){
					Logger.getLogger(MainFrm.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				try {
					Reader reader = new BufferedReader(new FileReader("text.txt"));
					Lexer lexer = new Lexer(reader);
					String result = "";
					int sum, minus, mult, div, pwr, opar, cpar;
					sum = minus = mult = div = pwr = opar = cpar = 0;
					
					while(true) {
						Tokens tokens = lexer.yylex();
						if(tokens == null) {
							result += "Fin";
							tpValidated.setText(result);
							tpSum.setText("Se encontraron " + sum + " coincidencias de este operador");
							tpMinus.setText("Se encontraron " + minus + " coincidencias de este operador");
							tpMultiplication.setText("Se encontraron " + mult + " coincidencias de este operador");
							tpDivision.setText("Se encontraron " + div + " coincidencias de este operador");
							tpPower.setText("Se encontraron " + pwr + " coincidencias de este operador");
							tpOpenParenthesis.setText("Se encontraron " + opar + " coincidencias de este operador");
							tpCloseParenthesis.setText("Se encontraron " + cpar + " coincidencias de este operador");
							return;
						}
						switch(tokens) {
							case Suma:
								sum = sum+1;
								break;
							case Resta:
								minus = minus+1;
								break;
							case Multiplicacion:
								mult = mult+1;
								break;
							case Division:
								div = div+1;
								break;
							case Potencia:
								pwr = pwr+1;
								break;
							case AbrePar:
								opar = opar+1;
								break;
							case CierraPar:
								cpar = cpar+1;
								break;
							case Identificador:
								result += lexer.lexeme + " es un Identificador\n";
								break;
							case Error:
								result = textField.getText() + " es un simbolo no definido\n";
								break;	
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAnalyze.setBounds(12, 601, 158, 32);
		panel.add(btnAnalyze);
		
		this.setLocationRelativeTo(null);
	}
}
