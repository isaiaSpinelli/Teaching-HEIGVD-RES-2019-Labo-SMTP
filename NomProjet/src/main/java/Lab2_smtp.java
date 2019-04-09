


import config.ConfigurationManager;
import model.mail.Message;
import model.mail.Person;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

		// Get configuration
		ConfigurationManager config = null;
		try {
			config = new ConfigurationManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Get client
		SmtpClient client = new SmtpClient(config.getSmtpServeurAddress(), config.getSmtpServeurPort());


		PrankGenerator generator = new PrankGenerator(config);
		ArrayList<Prank> pranks = generator.generatePranks();

		System.out.println( "Nb de prank = " + pranks.size() );

		for (Prank prank : pranks) {
			client.SendMail( prank.generateMailMessage() );
		}

	}

}
