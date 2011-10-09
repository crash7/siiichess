package chess.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class NetworkClient extends NetworkConnection {
	
	public NetworkClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		
	}

	protected boolean connect() {
		try {
			socket = new Socket(InetAddress.getByName(ip), port);
			return true;
		
		} catch (UnknownHostException e) {
			System.out.println("[NETWORK] Invalid host");
			
		} catch (IOException e) {
			System.out.println("[NETWORK] Error creating client socket to " + this.ip + " " + this.port);
			
		}
		return false;
		
	}

}