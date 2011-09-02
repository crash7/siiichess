package UIX.pieces;

import java.net.URL;

import javax.swing.ImageIcon;

public class GraphicPiece extends ImageIcon {
	
	public GraphicPiece(String image) {
		URL filepath = getClass().getClassLoader().getResource("UIX/resources/" + image);
		if(filepath != null) {
			
			
			(filepath);
		
		} else {
			System.out.println("No se pudo cargar la imagen: " + image);
			
		}
//		if(getClass().getResource("UIX/resources" + image).toString() != null) {
//			System.out.println("viva");
//			
//		} else {
//			System.out.println("no se pudo");
//			
//		}
		// CHEEEEE QUE HACEMOSSS?????? onda que nosotros esperamos que ellos hicieran algo...y nada :P
		// decimos algo?
		
	}
	

}
