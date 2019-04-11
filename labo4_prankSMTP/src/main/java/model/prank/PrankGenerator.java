package model.prank;

import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Person;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class PrankGenerator {

    static final Logger LOG = Logger.getLogger( PrankGenerator.class.getName());

    private ConfigurationManager config ;

    public PrankGenerator(ConfigurationManager config) {
        this.config = config;
    }

    public ArrayList<Prank> generatePranks(){
        ArrayList<Prank> pranks = new ArrayList<Prank>();

        ArrayList<String> messages = config.getMessages();
        int messageIndex =0;

        int numberOfGroups = config.getNbGroups();
        int numberOfVictims = config.getVictims().size();

        if ((numberOfVictims/numberOfGroups) < 3){
            numberOfGroups = numberOfVictims / 3;
            LOG.warning( "Pas assez de victime en fonction du nombre de groupe (3 personnes/groupe min)" );
        }

        ArrayList<Group> groups = generateGroups( config.getVictims(), numberOfGroups );
        for (Group group : groups){
            Prank prank = new Prank();
            ArrayList<Person> victims = group.getMembers();
            // Randomise l'ordre des personnes
            Collections.shuffle(victims);
            prank.setVictimSender(  victims.remove( 0 ) );
            prank.addVictimRecipients( victims );

            prank.addWitnessRecipients(config.getWitnessesToCC());

            prank.setMessage( config.getRandomMessage() );

            pranks.add( prank );
        }

        return pranks;
    }

    ArrayList<Group> generateGroups(ArrayList<Person> victims, int nbOfGroups){

        ArrayList<Person> availableVictims = new ArrayList<Person>(victims);
        Collections.shuffle( availableVictims );
        // Crée le nombre de groupe souhaité
        ArrayList<Group> groups = new ArrayList<Group>();
        for (int i=0; i<nbOfGroups; ++i){
            Group group = new Group();
            groups.add( group );
        }

        int turn = 0;
        Group targetGroup;
        while(availableVictims.size()>0){
            targetGroup = groups.get(turn);
            turn = (turn+1) % groups.size();
            Person victim = availableVictims.remove( 0 );
            targetGroup.addMember(victim);
        }

        return groups;
    }



}