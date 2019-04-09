package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Prank {

    private Person victimSender;
    private final ArrayList<Person> victimRecipients = new ArrayList( );
    private final ArrayList<Person> witnessRecipients = new ArrayList( );
    private String message;

    public Person getVictimSender(){
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addVictimRecipients (ArrayList<Person> victims){
        victimRecipients.addAll( victims );

    }
    public void addWitnessRecipients (ArrayList<Person> witness){
        witnessRecipients.addAll( witness );

    }

    public ArrayList<Person> getVictimRecipients(){
        return victimRecipients;
    }

    public ArrayList<Person> getWitnessRecipients(){
        return witnessRecipients;
    }

    public Message generateMailMessage(){
        Message message = new Message();

        // Message
        message.setMsg( this.message + "\r\n" + this.victimSender.getFirstName() );

        // To
        int size = victimRecipients.size();
        String[] to = new String[size];

        int i = 0;
        for (Person victim : victimRecipients){
            to[i] = victim.getMail();
        }

        message.setTo( to );

        // CC
        int size2 = witnessRecipients.size();
        String[] cc = new String[size];

        int k = 0;
        for (Person witness : witnessRecipients){
            to[k] = witness.getMail();
        }

        message.setCc( cc );

        // From
        message.setFrom( victimSender.getMail() );

        return message;
    }

}