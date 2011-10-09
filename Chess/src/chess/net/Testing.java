package chess.net;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Testing {

	public static void main(String[] args) {
		JSONObject msg = new JSONObject();
		msg.put("jugador", "ejemplo");
		msg.put("xsource", 1);
		msg.put("ysource", 2);
		msg.put("xdest", 3);
		msg.put("ydest", 2);
		System.out.println(msg);
		String json = msg.toJSONString();
		msg = (JSONObject) JSONValue.parse(json);
		
		System.out.println(new Integer(msg.get("xdest").toString()));
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				NetworkConnection c = new NetworkServer(24377);
				if(c.createSocket()) {
					JSONObject msg = new JSONObject();
					msg.put("player", "ejemplo");
					c.sendMessage(msg);
					msg.put("result", "200 OK");
					while(true) {
						System.out.println(c.waitMessage());
						c.sendMessage(msg);
						
					}
					
				}
				
			}
		}).start();
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				NetworkConnection c = new NetworkClient("127.0.0.1", 24377);
				c.createSocket();
				
			}
		}).start();
		
		
		
			

	}

}
