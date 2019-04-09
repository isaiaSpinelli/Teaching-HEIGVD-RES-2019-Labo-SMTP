package smtp;

import model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{

    static final Logger LOG = Logger.getLogger( SmtpClient.class.getName());

    private String smtpServeurAdresse = "127.0.0.1";
    private int smtpServeurPort = 25 ;

    public SmtpClient(String smtpServeurAdresse, int smtpServeurPort ){
        this.smtpServeurAdresse = smtpServeurAdresse;
        this.smtpServeurPort = smtpServeurPort;
    }

    SmtpClient(){

    }

    public void SendMail (Message mail){
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
            LOG.info(senderOK);

            // Config les receptionneurs
            for (String to : mail.getTo()){
                out.println("RCPT TO:<" + to + ">");
                String recipientOK = in.readLine();
                LOG.info(senderOK);
            }
            out.flush();

            // Config les CC
            for (String cc : mail.getCc()){
                out.println("RCPT TO:<" + cc + ">");
                String recipientOK = in.readLine();
                LOG.info(senderOK);
            }
            out.flush();

            // Config les BCC
            for (String bcc : mail.getCc()){
                out.println("RCPT TO:<" + bcc + ">");
                String recipientOK = in.readLine();
                LOG.info(senderOK);
            }
            out.flush();
/*
            System.out.println("RCPT TO:<" + mail.getTo() + ">");
            out.println("RCPT TO:<" + mail.getTo() + ">");
            String recipientOK = in.readLine();
            System.out.println(recipientOK);*/

            // Config les data
            System.out.println("DATA");
            out.println("DATA");
            String readyData = in.readLine();
            System.out.println(readyData);

            // Construction du message (DATA)
            // en-tete
            out.write( "Content-Type: text/plain; charset=\"utf-8\"\r\n" );
            // From
            out.write( "From: " + mail.getFrom() + "\r\n" );
            // To
            out.write( "To: " + mail.getTo()[0] );
            for (int i =1; i < mail.getTo().length;++i){
                out.write( ", " + mail.getTo()[i] );
            }
            out.write( "\r\n" );
            // CC
            if (mail.getCc().length != 0){
                out.write( "Cc: " + mail.getCc()[0] );
                for (int i =1; i < mail.getCc().length;++i){
                    out.write( ", " + mail.getCc()[i] );
                }
                out.write( "\r\n" );
                out.flush();
            }



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
        Message message = new Message();
        message.setFrom( "tommy@sd.com" );
        String[] to = {"isaia@sd.com"};
        message.setTo( to );
        String[] cc = {"isaia.spinelli@heig-vd.ch"};
        message.setTo( cc );
        message.setMsg( "Subject : Hi!\r\nu want sum phoque ?\nNice frère\r\n.\r\n" );
        client.SendMail(message);

    }

}