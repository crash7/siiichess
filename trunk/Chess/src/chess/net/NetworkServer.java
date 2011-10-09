package chess.net;

import java.io.IOException;
import java.net.ServerSocket;

class NetworkServer extends NetworkConnection {
	
	public NetworkServer(int port) {
		this.port = port;
				
	}

	protected boolean connect() {
		try {
			ServerSocket svrs = new ServerSocket(port);
			socket = svrs.accept();
			return true;
			
		} catch (IOException e) {
			System.out.println("[NETWORK] Error creating server socket");
			
		}
		return false;
		
	}

}