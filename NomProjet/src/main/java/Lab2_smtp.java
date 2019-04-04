


import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is not really an HTTP client, but rather a very simple program that
 * establishes a TCP connection with a real HTTP server. Once connected, the 
 * client sends "garbage" to the server (the client does not send a proper
 * HTTP request that the server would understand). The client then reads the
 * response sent back by the server and logs it onto the console.
 * 
 * @author Olivier Liechti
 */
public class Lab2_smtp {

	static final Logger LOG = Logger.getLogger(Lab2_smtp.class.getName());

	final static int BUFFER_SIZE = 1024;

	/**
	 * This method does the whole processing
	 */
	public void sendWrongHttpRequest() {

		String from = "tommy@sd.com";
		String to = "isaia@sd.com";


		 String msg = "tg";

		// BufferedReader msg;
		File file = new File("message.txt");
		/*FileReader msgFileReader = null;
		try {
			msgFileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		msg = new BufferedReader(msgFileReader);*/

		String host = "127.0.0.1";

		Socket clientSocket = null;
		OutputStream os = null;
		InputStream is = null;
		LOG.log(Level.INFO, "1");
		
		try {
			clientSocket = new Socket(host, 25);
			os = clientSocket.getOutputStream();
			is = clientSocket.getInputStream();

			BufferedReader  in = new BufferedReader(new InputStreamReader(is));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(os), true);
			if (in == null || out == null) {
				System.out.println("Failed to open streams to socket.");
				return ;
			}

			String initialMessage = in.readLine();
			System.out.println(initialMessage);

			System.out.println("HELO local");
			out.println("HELO local");

			String config = in.readLine();
			while( config.contentEquals("250-") ){
				System.out.println(config);
				config = in.readLine();
			}

			System.out.println(config);

			System.out.println("MAIL From:<" + from + ">");
			out.println("MAIL From:<" + from + ">");
			String senderOK = in.readLine();
			System.out.println(senderOK);
			System.out.println("RCPT TO:<" + to + ">");
			out.println("RCPT TO:<" + to + ">");
			String recipientOK = in.readLine();
			System.out.println(recipientOK);
			System.out.println("DATA");
			out.println("DATA");
			String line;
			/*
			while ((line = msg.readLine()) != null) {
				out.println(line);
			}*/
			out.println(msg);
			System.out.println(".");
			out.println(".");
			String acceptedOK = in.readLine();
			System.out.println(acceptedOK);
			System.out.println("QUIT");
			out.println("QUIT");

		} catch (IOException ex) {
			LOG.log(Level.SEVERE, null, ex);
		} finally {
			try {
				is.close();
			} catch (IOException ex) {
				Logger.getLogger(Lab2_smtp.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				os.close();
			} catch (IOException ex) {
				Logger.getLogger(Lab2_smtp.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				clientSocket.close();
			} catch (IOException ex) {
				Logger.getLogger(Lab2_smtp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

		Lab2_smtp client = new Lab2_smtp();
		client.sendWrongHttpRequest();

	}

}
