package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket server = null;
    private Socket client = null;
    private BufferedReader in;
    private PrintWriter out;
    private String incomingMsg;
    private String outgoingMsg;

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public String getIncomingMsg() {
        return incomingMsg;
    }

    public void setIncomingMsg(String incomingMsg) {
        this.incomingMsg = incomingMsg;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public String getOutgoingMsg() {
        return outgoingMsg;
    }

    public void setOutgoingMsg(String outgoingMsg) {
        this.outgoingMsg = outgoingMsg;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public SocketServer() {
    }

    public void listenSocket() {

        try {
            server = new ServerSocket(4500);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4500");
            System.exit(-1);
        }

        try {
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4500");
            System.exit(-1);
        }
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed: 4500");
            System.exit(-1);
        }
        while (true) {
            try {
                incomingMsg = in.readLine();
//Send ok back to client
                out.println("1");
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
    }

    protected void finalize() {
//Clean up 
        try {
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}