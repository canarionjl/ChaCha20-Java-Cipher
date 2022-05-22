package cipher;

public class ChaChaMediator {
	
	static ChaChaMediator mediator;
	static ChaCha20Generator generator;
	GraphicInterface.ButtonPanel buttonPanel;
	GraphicInterface.TextPanel textPanel;
	
	private ChaChaMediator() {}
	
	public static ChaChaMediator getInstance() {
		if(mediator == null) {
			mediator = new ChaChaMediator();
			generator = new ChaCha20Generator();
		}
		return mediator;
	}
	
	public void setButtonPanel (GraphicInterface.ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
	public void setTextPanel (GraphicInterface.TextPanel textPanel) {
		this.textPanel = textPanel;
	}
	
	public void cifrar() {
		String plainText = textPanel.getText();
		byte[] plainTextBytes = plainText.getBytes();
		byte[] cipherTextBytes = generator.encrypt(plainTextBytes);
		textPanel.setBytesText(cipherTextBytes);
		textPanel.setText(new String(cipherTextBytes));
	}

	public void descifrar() {
		if(textPanel.getBytesText()==null) return;
		byte[] cipherTextBytes = textPanel.getBytesText();
		textPanel.setBytesText(null);
		byte[] plainTextBytes = generator.decrypt(cipherTextBytes);
		textPanel.setText(new String(plainTextBytes));		
	}
	
	public void mostrarClave() {
		String key = generator.getHexKey();
		textPanel.setText(textPanel.getText()+"\n\n\n\n"+ "Clave: "+key);
	}
	
	public void mostrarNonce() {
		String nonce = generator.getHexNonce();
		textPanel.setText(textPanel.getText()+"\n\n\n\n"+ "NONCE: "+nonce);
	}
	
}
