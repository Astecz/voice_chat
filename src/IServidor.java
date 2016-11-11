

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class IServidor extends JFrame implements ActionListener{
	boolean fase = true;
	JButton botao = new JButton("Conectar");
	JButton botao2 = new JButton("Clear");
	JTextArea texto = new JTextArea("Voice Chat UDP\n\n\nAutores:\n\tAbraão Aires\n\tAellison Cassimiro\n\tJosé Alves\n\tLucas Aversari");
	Servidor server;
	
	public IServidor(){
		super("Servidor");
		
		Font fonte = new Font("Arial", Font.PLAIN, 26);
		botao.setFont(fonte);
		texto.setFont(new Font("Arial", Font.PLAIN, 15));
		
		botao2.setFont(new Font("Arial", Font.PLAIN, 15));
		
		botao.addActionListener(this);
		botao2.addActionListener(this);
		
		botao2.setVisible(false);
		
		Container c = new JPanel();
		c.setLayout(new BorderLayout());
		c.add(BorderLayout.EAST, botao);
		c.add(BorderLayout.WEST, botao2);
		getContentPane().add(BorderLayout.SOUTH, c);
		
		JScrollPane scrol = new JScrollPane(texto);
		getContentPane().add(BorderLayout.CENTER, scrol);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao2){
			
			texto.setText("");
			
		}else{
			if(fase){
				botao2.setVisible(true);
				botao.setText("Desconectar");
				fase = false;
				texto.setText("Servidor Online - Porta: 8000\n----------------------------------\n");
				server = new Servidor(this);
				server.start();
			
			
			}else{
			
				System.exit(1);
			
			}
		}
	}
	
	public void printT(String x){
		texto.append(x + "\n");
	}
	
	public static void main(String args[]){
		new IServidor();
	}
		
}
