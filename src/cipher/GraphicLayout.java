package cipher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphicLayout {
	

	public static void main(String[] args) {
		
		ChaChaMediator mediator = ChaChaMediator.getInstance();

		MainFrame miMarco = new MainFrame(mediator);
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);
		
		
		mediator.setTextPanel(miMarco.getTextPanel());
		mediator.setButtonPanel(miMarco.getButtonPanel());
	}
}

class MainFrame extends JFrame {
	
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public TextPanel getTextPanel() {
		return textPanel;
	}

	ButtonPanel buttonPanel;
	TextPanel textPanel;

	public MainFrame(ChaChaMediator mediator) {

		setTitle("Cifrando con ChaCha20");
		setBounds(300,100,1000,700);

		 buttonPanel = new ButtonPanel(mediator);
		 textPanel = new TextPanel(mediator);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);

		add(textPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
	}

}

class TextPanel extends JPanel implements GraphicInterface.TextPanel{
	
	JTextArea miAreaTexto;
	JScrollPane scrollPane;
	ChaChaMediator mediator;
	byte[] byteText;

	public TextPanel(ChaChaMediator mediator) {
		
		this.mediator=mediator;
		this.byteText=null;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		miAreaTexto = new JTextArea(20,50);
		miAreaTexto.setLineWrap(true);
		miAreaTexto.setFont(new Font("Times New Roman",Font.BOLD,20));
		miAreaTexto.setBackground(new Color(255,255,255));
		miAreaTexto.setBorder(BorderFactory.createCompoundBorder(null,BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(51,198,255))));
	    scrollPane= new JScrollPane(miAreaTexto);
		add(scrollPane);
	}
	
	@Override
	public String getText() {
		return miAreaTexto.getText();
	}

	@Override
	public void setText(String text) {
		miAreaTexto.setText(text);
	}
	
    @Override
    public void setBytesText (byte[] byteText) {
    	this.byteText=byteText;
    }
    
    @Override
    public byte[] getBytesText() {
    	return this.byteText;
    }
}

class ButtonPanel extends JPanel implements GraphicInterface.ButtonPanel {

	JButton botonCifrar = new JButton("Cifrar");
	JButton botonDescifrar = new JButton("Descifrar");
	JButton botonClave = new JButton("Mostrar Clave");
	JButton botonNonce = new JButton("Mostrar NONCE");
	ChaChaMediator mediator;

	public ButtonPanel(ChaChaMediator mediator) {
		this.mediator = mediator;

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		configureButtons();
		
		add(botonCifrar);
		add(botonDescifrar);
		add(botonClave);
		add(botonNonce);
		

	}

	public void configureButtons() {
		botonCifrar.setBackground(new Color(97,223,114));
		botonCifrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediator.cifrar();
				botonCifrar.setEnabled(false);
				botonDescifrar.setEnabled(true);
			}
		});
		botonDescifrar.setBackground(new Color(237,78,78));
		botonDescifrar.setEnabled(false);
		botonDescifrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediator.descifrar();
				botonCifrar.setEnabled(true);
				botonDescifrar.setEnabled(false);
		 }
		});
		
		botonNonce.setBackground(new Color(223,198,97));
		botonNonce.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediator.mostrarNonce();
			}
		});
		
		botonClave.setBackground(new Color(223,198,97));
		botonClave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mediator.mostrarClave();
			}
		});
	}
}
