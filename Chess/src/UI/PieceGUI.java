package UI;

import java.net.URL;
import javax.swing.ImageIcon;

public class PieceGUI {
	private ImageIcon image;
	private char keyName;
	private char color;
	
	public PieceGUI(String image, char name, char color) {
		loadIcon(image);
		keyName = name;
		this.color = color;
		
	}
	
	public PieceGUI() {
		image = null;
		
	}

	public ImageIcon getImage() {
		return image;
	}

	public void loadIcon(String image) {
		URL filepath = getClass().getClassLoader().getResource("UI/resources/" + image);
		if(filepath != null) {
			this.image = new ImageIcon(filepath);
		
		} else {
			System.out.println("No se pudo cargar la imagen: " + image);
			this.image = new ImageIcon();
			
		}

	}

	public char getKeyName() {
		return keyName;
	}

	public void setKeyName(char keyName) {
		this.keyName = keyName;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
		
	}
	
}
