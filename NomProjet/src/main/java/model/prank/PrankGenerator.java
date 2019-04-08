package model.prank;

import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.io.IOException;
import java.util.ArrayList;

public class PrankGenerator {

    private ConfigurationManager config ;

    public PrankGenerator(ConfigurationManager config) {
        this.config = config;
    }


    ArrayList<Group> generateGroups(ArrayList<Person> victims, int nbOfGroups){
        return new ArrayList<Group>( );
    }

}