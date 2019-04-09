package model.mail;

public class Message {

    // Add CC
    private String from;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject;
    private String msg;


    public String getMsg() {
        return msg;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}