
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ICliente extends JFrame implements ActionListener{
		boolean mode = true;
		String NICK, SALA, IP;
		
		JLabel l1 = new JLabel("Nick: ");
		JLabel l2 = new JLabel("Sala: ");
		JLabel l3 = new JLabel("IP: ");
		JTextField nick = new JTextField();
		JTextField ip = new JTextField();
		JTextField sala =  new JTextField();
		JButton botao = new JButton("Conectar");
		
			
	public ICliente(){
		super("Cliente"); // nome da janela
              
        botao.addActionListener(this);
        
        
        Container c = new JPanel();
        c.setLayout(new GridLayout(4,2));
        c.add(l1);
        c.add(nick);
        c.add(l2);
        c.add(sala);
        c.add(l3);
        c.add(ip);
        c.add(new JLabel(""));
        c.add(botao);
        
        
        Font x = new Font("Arial", Font.PLAIN, 18);
        
        l1.setFont(x);
        l2.setFont(x);
        l3.setFont(x);
        
        //l1.setHorizontalAlignment(SwingConstants.CENTER); 
        //l2.setHorizontalAlignment(SwingConstants.CENTER); 
        //l3.setHorizontalAlignment(SwingConstants.CENTER);
        
        getContentPane().add(c);         
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// operação de saida
        setSize(300, 165); // tamanho
        setVisible(true); // ela é visivel
		
		
	}
	
	
	public void reset(){
		botao.setText("Conectar");
		
		
		ip.setText("");
		nick.setVisible(true);
		ip.setVisible(true);
		sala.setVisible(true);
		
		Font x = new Font("Arial", Font.PLAIN, 18);
        l1.setFont(x);
        l2.setFont(x);
        l3.setFont(x);
		l1.setText("Nick: ");
		l2.setText("Sala: ");
		l3.setText("IP: ");
		
		mode = true;
        
        
	}

	
	public void actionPerformed(ActionEvent e) {
		if(mode){
			mode = false;
			botao.setText("Desconectar");
			NICK = nick.getText();
			IP = ip.getText();
			SALA = sala.getText();
			
			// testa conexão
			// inicia thread cliente
			new Cliente(this, NICK, IP, SALA).start();		
			
			nick.setVisible(false);
			ip.setVisible(false);
			sala.setVisible(false);
			Font x = new Font("Arial", Font.PLAIN, 12);
	        l1.setFont(x);
	        l2.setFont(x);
	        l3.setFont(x);
	        l1.setText("Nick: " + NICK);
			l2.setText("Sala: " + SALA);
			l3.setText("IP: " + IP);
			
		}else{
			
			System.exit(1);
			
		}
		
	}
	
	public static void main(String []args){
		new ICliente();
	}

}
