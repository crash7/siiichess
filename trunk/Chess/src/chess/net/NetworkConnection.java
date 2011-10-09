package chess.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

abstract class NetworkConnection {
	protected int port;
	protected String ip;
	protected Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public NetworkConnection() {
		port = 24377;
		ip = "127.0.0.1";
		
	}
	
	public boolean createSocket() {
		if(connect()) {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
				output = new PrintWriter(socket.getOutputStream(), true);
				return true;
				
			} catch(IOException e) {
				System.out.println("[NETWORK] Error creating socket");
				
			}
			
		} else {
			System.out.println("[NETWORK] Connection failed");
			
		}
		return false;
		
	}
		
	public void sendMessage(JSONObject msg) {
		if(isOnline()) {
			System.out.println("Enviando: " + msg);
			output.println(msg);
			
		}
		
	}
	
	public JSONObject waitMessage() {
		if(isOnline()) {
			try {
				JSONObject msg = (JSONObject) JSONValue.parse(input.readLine());
				System.out.println("Recibiendo: " + msg);
				return msg;
				
			} catch (IOException e) {
				System.out.println("[NETWORK] Input error");
				try {
					socket.close();
					
				} catch (IOException e1) {
					System.out.println("[NETWORK] Socket close error");
					
				}
			
			}
		}
		return null;
		
	}
	
	public boolean isOnline() {
		return !socket.isClosed();
		
	}
	
	protected abstract boolean connect();
	
}