package Enum;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import java.util.Properties;
import com.jcraft.jsch.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

public class SFTPConnexion {

    private final String username = "hardis";
    private final String sftpAdress = "35.196.126.86";
    private final String password = "marketplace2019";
    private Session session;

    public SFTPConnexion() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, sftpAdress);
            session.setPassword(password);
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
        } catch (JSchException ex) {
            java.util.logging.Logger.getLogger(SFTPConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean uploadFile(InputStream source, String destination) throws JSchException {
        try {
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp c = (ChannelSftp) channel;
            c.cd("/");
            String[] folders = destination.split("/");
            folders = Arrays.copyOf(folders, folders.length - 1);
            for (String folder : folders) {
                if (folder.length() > 0) {
                    try {
                        c.cd(folder);
                    } catch (SftpException e) {
                        c.mkdir(folder);
                        c.cd(folder);
                    }
                }
            }
            c.put(source, destination);
            return true;
        } catch (SftpException ex) {
            java.util.logging.Logger.getLogger(SFTPConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean downloadFile(String source, OutputStream destination) throws JSchException {
        try {
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp c = (ChannelSftp) channel;
            c.get(source, destination);
            return true;
        } catch (SftpException ex) {
            java.util.logging.Logger.getLogger(SFTPConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean downloadFile(String source, String destination) throws JSchException {
        try {
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp c = (ChannelSftp) channel;
            c.get(source, destination);
            return true;
        } catch (SftpException ex) {
            java.util.logging.Logger.getLogger(SFTPConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<String> listeFichiersRepertoire(String repertoire) {
        try {
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp c = (ChannelSftp) channel;
            List liste = new ArrayList();
            c.cd(repertoire);
            Vector filelist = c.ls(repertoire);
            for (int i = 0; i < filelist.size(); i++) {
                LsEntry entry = (LsEntry) filelist.get(i);
                liste.add(entry.getFilename());
            }
            return liste;
        } catch (Exception e) {
            return null;
        }

    }

    public void disconnect() {
        session.disconnect();
    }

}
