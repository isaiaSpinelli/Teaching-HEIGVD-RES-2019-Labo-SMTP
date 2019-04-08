package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{

    static final Logger LOG = Logger.getLogger( SmtpClient.class.getName());

    private String smtpServeurAdresse = "127.0.0.1";
    private int smtpServeurPort = 25 ;

    public void SendMail (Mail mail){
        Socket clientSocket = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            clientSocket = new Socket(smtpServeurAdresse, smtpServeurPort);
            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(os), true);
            if (in == null || out == null) {
                System.out.println("Failed to open streams to socket.");
                return ;
            }

            // Lis le message initial
            String initialMessage = in.readLine();
            System.out.println(initialMessage);
            // Envoie le premier message HELO local
            System.out.println("HELO local");
            out.println("HELO local");

            // Lis toute les config du serveur SMTP
            String config = in.readLine();
            while( config.contentEquals("250-") ){
                System.out.println(config);
                config = in.readLine();
            }
            System.out.println(config);

            // Config le from
            System.out.println("MAIL From:<" + mail.getFrom() + ">");
            out.println("MAIL From:<" + mail.getFrom() + ">");
            String senderOK = in.readLine();
            System.out.println(senderOK);

            // Config le receptionneur
            System.out.println("RCPT TO:<" + mail.getTo() + ">");
            out.println("RCPT TO:<" + mail.getTo() + ">");
            String recipientOK = in.readLine();
            System.out.println(recipientOK);

            // Config les data
            System.out.println("DATA");
            out.println("DATA");

            String readyData = in.readLine();
            System.out.println(readyData);

            // Fin du message intégré dans le message
            out.println(mail.getMsg());
            System.out.println("envoyer");


            String acceptedOK = in.readLine();
            System.out.println(acceptedOK);

            // Quitte
            System.out.println("QUIT");
            out.println("QUIT");

            String Bye = in.readLine();
            System.out.println(Bye);

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger( SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger( SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger( SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        SmtpClient client = new SmtpClient();
        Mail mailTosend = new Mail( "tommy@sd.com", "isaia@sd.com", "u want sum phoque ?\nNice frère\r\n.\r\n" );
        client.SendMail(mailTosend);

    }

}