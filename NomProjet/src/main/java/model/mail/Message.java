package model.mail;

public class Message {

    // Add CC
    private String from;
    private String to;
    private String msg;


    public Message(String from, String to, String msg) {
        this.from = from;
        this.to = to;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}