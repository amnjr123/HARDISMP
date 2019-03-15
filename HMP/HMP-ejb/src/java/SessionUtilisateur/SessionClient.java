/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import GestionUtilisateur.Client;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class SessionClient implements SessionClientLocal {

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @Override
    public void creerClient() {
        Client c = new Client();
        c.setNom("Madranges");
        c.setPrenom("Manon");
        c.setMail("madranges.m@gmail.com");
        c.setMdp("123");
        c.setTelephone("0600000000");
        c.setDateCreationCompte(new Date());
        //clientFacade.creerClient(c);
    }

    @Override
    public void creerPO() {
        //porteurOffreFacade.creerPO();
    }
    
    

    
    

}
