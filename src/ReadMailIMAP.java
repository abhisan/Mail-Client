import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class ReadMailIMAP {
	public static void main(String[] args) throws MessagingException, IOException {
		String username = "";// change accordingly
		String password = "";// change accordingly
		Properties props = System.getProperties();
		String host = "imap.gmail.com";// change accordingly
		Properties properties = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		props.setProperty("mail.imap.host", "imap.gmail.com");
		props.setProperty("mail.imap.port", "993");
		props.setProperty("mail.imap.connectiontimeout", "5000");
		props.setProperty("mail.imap.timeout", "5000");
		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, username, password);
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message[] messages = emailFolder.getMessages();
		System.out.println("messages.length---" + messages.length);
		for (int i = 0, n = messages.length; i < n; i++) {
			Message message = messages[i];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + message.getContent().toString());
		}
		emailFolder.close(false);
		store.close();
	}
}