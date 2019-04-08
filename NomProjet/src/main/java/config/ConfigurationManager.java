package config;

import model.mail.Message;
import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class ConfigurationManager {
    private String smtpServeurAddress;
    private int smtpServeurPort;
    private final ArrayList<Person> victims;
    private final ArrayList<String> messages;
    private int nbGroups;

    public ConfigurationManager() throws IOException {
        victims = loadAddressesFromFile("./config/victimes.utf8");
        messages = loadMessagesFromFile("./config/messages.utf8");
        loadProperties("./config/victimes.utf8");
    }

    private void loadProperties(String fileName) throws IOException {

        FileInputStream fis = new FileInputStream( fileName );
        Properties properties = new Properties( );
        properties.load(fis);

        this.smtpServeurAddress = properties.getProperty( "smtpServeurAddress" );
        this.smtpServeurPort = Integer.parseInt(  properties.getProperty( "smtpServeurPort" )) ;
        this.nbGroups = Integer.parseInt(properties.getProperty( "numberOfGroups" ));

        // Get to CC if we have time
    }

    private ArrayList<Person> loadAddressesFromFile(String fileName) throws IOException {
        ArrayList<Person> result = new ArrayList();

        FileInputStream fis = new FileInputStream( fileName );
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String address = reader.readLine();
        while (address != null){
            result.add( new Person( address ) );
            address = reader.readLine();
        }

        return result;

    }

    private ArrayList<String> loadMessagesFromFile(String fileName) throws IOException {
        final String endMsg = "\r\n.\r\n";

        ArrayList<String> messages = new ArrayList();

        FileInputStream fis = new FileInputStream( fileName );
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String line = reader.readLine();
        // Pour chaque message
        while (line != null){
            StringBuilder body = new StringBuilder( );
            // Pour chaque ligne du message
            while ( (line != null) && (line.equals( ".")) ){
                body.append( line + "\r\n");
                line = reader.readLine();

            }
            // Ajout le fin demander en SMTP au message
            body.append( endMsg );
            messages.add( body.toString() );
            line = reader.readLine();
        }

        return messages;

    }

    public ArrayList<Person> getVictims(){
        return victims;
    }

    public ArrayList<String> getMessages(){
        return messages;
    }

    public String getRandomMessage(){
        Random rand = new Random();
        int pos = (rand.nextInt() % messages.size());
        return messages.get( pos );


    }

    public int getNbGroups() {
        return nbGroups;
    }
}
