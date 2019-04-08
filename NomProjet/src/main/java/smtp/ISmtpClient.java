package smtp;

import model.mail.Mail;

public interface ISmtpClient {

    public void SendMail(Mail mail);

}