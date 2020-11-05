package cogent.demo.models;

import org.springframework.stereotype.Component;

/**
 * Date :July 25,2018
 * 
 * @author MukulJaiswal
 * @version 1.0
 *
 */
@Component
public class MailMessage {


	private String emailAddress;
	private String subject;
	private String bodyText;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

}
