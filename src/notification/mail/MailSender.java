package notification.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailSender {
	
	private final transient Properties properties = System.getProperties();
	private transient MailAuthenticator authenticator ;
	private transient Session session;
	
	public MailSender(final String username,
			final String password,final String smtpHostName) {
		// TODO Auto-generated constructor stub
		init(username,password,smtpHostName);
	}
	
	public MailSender(final String username,final String password){
		final String smtpHostName = "smtp." + username.split("@")[1];
		init(username, password, smtpHostName);
	}

	private void init(String username, String password, String smtpHostName) {
		// TODO Auto-generated method stub
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", smtpHostName);
		
		authenticator = new MailAuthenticator(username, password);
		session = Session.getInstance(properties,authenticator);
		
	}
	
	public void send(String recipient, String subject,Object content )
	throws AddressException,MessagingException{
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(authenticator.getUsername()));;
		message.setSubject(subject);
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));;
		message.setContent(content.toString(), "text/html;charset=utf-8");
		
		Transport.send(message);
	}
	
}
