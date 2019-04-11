package model.mail;

import java.util.ArrayList;

public class Group {

    private final ArrayList<Person> members = new ArrayList<Person>();

    public ArrayList<Person> getMembers() {
        return members;
    }

    public void addMember(Person person) {
        this.members.add( person ) ;
    }
}