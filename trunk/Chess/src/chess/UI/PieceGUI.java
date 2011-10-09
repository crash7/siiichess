package chess.UI;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class PieceGUI {
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

	public ImageIcon getImageIcon() {
		return image;
	}
	
	public Image getImage() {
		return image.getImage();
		
	}

	public void loadIcon(String image) {
		URL filepath = getClass().getClassLoader().getResource("chess/UI/resources/" + image);
		if(filepath != null) {
			this.image = new ImageIcon(filepath);
		
		} else {
			System.out.println("No se pudo cargar la imagen: " + image + " - fp: " + filepath);
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
