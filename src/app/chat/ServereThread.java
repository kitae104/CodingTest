package app.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServereThread extends Thread
{
	private Socket socket;
	private ArrayList<ServereThread> threadList;
	private BufferedReader in;
	private BufferedWriter out;
	
	public ServereThread(Socket socket, ArrayList<ServereThread> threadList)
	{
		this.socket = socket;
		this.threadList = threadList;
	}

	@Override
	public void run()
	{
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));		
			
			String inMsg = null;
			
			while(true) {
				inMsg = in.readLine();
				System.out.println("받은 메시지 : " + inMsg);
				if(inMsg.equalsIgnoreCase("bye")) {
					break;					
				}
				sendToAllClients(inMsg);
			}			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void sendToAllClients(String outMsg)
	{
		for (ServereThread st : threadList)
		{
			try
			{
				st.out.write(outMsg+ "\n");
				st.out.flush();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
