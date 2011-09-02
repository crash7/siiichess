package chess;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;

import UIX.ChessWindow;
import UIX.pieces.GraphicPiece;

import chess.business.Controller;
import chess.dtos.PlayerDTO;

public class Main {
	Controller cc;
	PlayerDTO players[];
	

	public static void main(String[] args) {
		//new Main(new Controller()).start();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ChessWindow().setVisible(true);
				
			}
		});
		GraphicPiece p = new GraphicPiece("black_king.png");

	}

	public Main(Controller c) {
		cc = c;
		// Jugadores
		players = new PlayerDTO[2];
		players[0] = new PlayerDTO();
		players[0].setColor('w');
		players[0].setName("Foo");

		players[1] = new PlayerDTO();
		players[1].setColor('b');
		players[1].setName("Bar");

	}

	public void start() {
		cc.newGame(players[0], players[1]);
		int turno = 0;
        int origenX;
        int origenY;
        int destinoX;
        int destinoY;
        int respuesta;
        PlayerDTO current;
        BufferedReader keyb = new BufferedReader(new InputStreamReader(System.in));
        
        
		while(cc.isActive()) {
        	printBoard(cc.getBoard());
        	current = players[turno%2];
            System.out.println("Juega " + current.getName() + "(" + current.getColor() + ")");

            do{
            	respuesta = 6;
                try {
                	System.out.print("Elija Pieza\nPosX: ");
                	origenX = Integer.parseInt(keyb.readLine());
                	
                	System.out.print("PosY: ");
                	origenY = Integer.parseInt(keyb.readLine());
                	System.out.print("Elija destino\nPosX: ");
                	destinoX = Integer.parseInt(keyb.readLine());
                	System.out.print("PosY: ");
					destinoY = Integer.parseInt(keyb.readLine());
					respuesta = cc.move(current, origenX, origenY, destinoX, destinoY);
					
				} catch (Exception e) {
					System.out.println("We got an exception, RUUUUUN");
					e.printStackTrace();
					
				}
                
                
                if(respuesta == 6) {
                	System.out.println("Movimiento Invalido");
                	
                } else {
                	System.out.println("RTA: " + respuesta);
                	
                }
            } while (respuesta == 6);
            
            turno++;
        }
		
	}
	
	private void printBoard(String[][] board) {
		System.out.println("Tablero\n\n");
		int xtop = board.length;
		int ytop = board[0].length;
		System.out.print("\t");
		for(int s = 0; s < xtop; s++) {
			System.out.print(s + "\t");
		}
		System.out.println();
		for(int i = 0; i < xtop; i++) {
			System.out.print(i + "\t");
			for(int j = 0; j < ytop; j++) {
				if(board[i][j] != null) {
					System.out.print(board[i][j]);
					
				} else {
					System.out.print("XXXX");
					
				}
				System.out.print("\t");
				
			}
			System.out.println();
			
		}
		
		
	}

}