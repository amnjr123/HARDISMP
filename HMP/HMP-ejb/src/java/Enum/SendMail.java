/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail {

    private final String username = "hardis.marketplace@gmail.com";
    private final String password = "Hardis480871//";

    private Session authentification(String username, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public void sendMail(String to, String sujet, String mess) {
        Session session = authentification(username, password);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(sujet);

            message.setContent(mess, "text/html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendMailUtilisateurHardisMdp(UtilisateurHardis uh, String typeUtilisateur) {
        if(typeUtilisateur==null)
            typeUtilisateur="";
        /*Generation mdp*/
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        int complementPw = (int) (Math.random() * (99 - 10)+10);
        String generatedPw = buffer.toString()+complementPw;
        
        sendMail(uh.getMail(), "Création de votre compte sur Hardis Market Place", "Bonjour "+uh.getNom()+" "+uh.getPrenom()+",\nVotre compte Hardis Market Place en tant que "+typeUtilisateur+" a bien été crée.\nVotre mot de passe est : "+generatedPw+"\nÀ bientôt sur Hardis Market Place");
        return generatedPw;
    }
    
    public String sendMailMDPOublie(Utilisateur u){
        /*Generation mdp*/
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        int complementPw = (int) (Math.random() * (99 - 10)+10);
        String generatedPw = buffer.toString()+complementPw;
        
        sendMail(u.getMail(), "Mail de récupération du mot de passe", "Bonjour "+u.getNom()+" "+u.getPrenom()+",\nVotre nouveau mot de passe est : "+generatedPw+"\nNous vous invitons à le modifier dès votre prochaine connexion\n\nÀ bientôt sur Hardis Market Place");
        return generatedPw;
    }
}
