package Enum;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import java.util.Properties;
import com.jcraft.jsch.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;

public class SFTPConnexion {

    private final String username = "hardis";
    private final String sftpAdress = "35.196.126.86";
    private final String password = "marketplace2019";
    private Session session;
    private Channel channel;
    ChannelSftp c;

    public SFTPConnexion() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, sftpAdress);
        session.setPassword(password);
        Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        c = (ChannelSftp) channel;

    }

    public boolean uploadFile(InputStream source, String destination) throws JSchException {
        try {
            c.cd("/");
            String[] folders = destination.split("/");
            folders = Arrays.copyOf(folders, folders.length-1);
            for (String folder : folders) {
                if (folder.length() > 0 ) {
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
            c.get(source, destination);
            return true;
        } catch (SftpException ex) {
            java.util.logging.Logger.getLogger(SFTPConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void disconnect() {
        session.disconnect();
    }

}
