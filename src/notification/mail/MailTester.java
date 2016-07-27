package notification.mail;

import javax.mail.MessagingException;

public class MailTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "suntrian@126.com";
		String password = "suntreei-toto";
		
		MailSender sender = new MailSender(username, password);
		try {
			sender.send("17091644959@139.com", "tell me why", "helo my k s kfbkks lskf sfacnogt hinc to ssay ");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
