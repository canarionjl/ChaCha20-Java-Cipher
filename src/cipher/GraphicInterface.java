package cipher;

public interface GraphicInterface {
	interface TextPanel{

		String getText();

		void setText(String text);

		void setBytesText(byte[] byteText);

		byte[] getBytesText();
		
	}
	interface ButtonPanel{
		
	}
}
