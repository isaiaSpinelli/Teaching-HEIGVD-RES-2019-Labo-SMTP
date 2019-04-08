package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;

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

    public ArrayList<Message> generateMailMessage(){
        // Add name of sender
        String body = this.message + "\r\n" ;

        ArrayList<Message> mails = new ArrayList<Message>( );
        // add CC
        for(Person victimRecipient : victimRecipients){
            mails.add( new Message( victimSender.getMail(), victimRecipient.getMail(), body) );
        }

        return mails;
    }

}