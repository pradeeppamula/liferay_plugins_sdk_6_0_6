package org.ieeecs.communities.presentation.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Date: 10/31/13
 * Time: 4:11 PM
 * This class will handle authentication for sending emails.
 */
public class SmtpAuthenticator extends Authenticator {
    private String username = "user";
    private String password = "password";

    /**
     * Default constructor
     */
    public SmtpAuthenticator() {
        super();
    }

    /**
     * Constructor that sets the username and password
     * @param username
     * @param password
     */
    public SmtpAuthenticator(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        if ((this.username != null) && (this.username.length() > 0) && (this.password != null)
                && (this.password.length() > 0)) {
            return new PasswordAuthentication(this.username, this.password);
        }
        return null;
    }
}