package model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private String firstName = null;
    private String lastName = null;
    private String mail;

    public Person(String firstName,String lastName, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public Person(String mail) {
        this.mail = mail;
        // Permet de détécter les patterne comme les addresse email de l heig
        // (prenom.nom@)
        Pattern pattern = Pattern.compile( "(.*)\\.(.*)@" );
        Matcher matcher = pattern.matcher( mail );
        boolean found = matcher.find();
        if(found){
            this.firstName = matcher.group(1);
            firstName = firstName.substring( 0,1 ).toUpperCase() + firstName.substring( 1 );
            this.lastName = matcher.group(2);
            lastName = lastName.substring( 0,1 ).toUpperCase() + lastName.substring( 1 );
        }

    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}