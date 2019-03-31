/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Conversation;
import GestionDevis.Devis;
import GestionUtilisateur.Client;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ManonMADRANGES
 */
@Local
public interface ConversationFacadeLocal {

    void create(Conversation conversation);

    void edit(Conversation conversation);

    void remove(Conversation conversation);

    Conversation find(Object id);

    List<Conversation> findAll();

    List<Conversation> findRange(int[] range);

    int count();

    Conversation creerConversation(Client client);

    Conversation affecterUHConversation(Conversation c, UtilisateurHardis uh);

    Conversation creerConversation(Devis d, Client client, UtilisateurHardis uh);

    Conversation rechercheConversation(long id);

    List<Conversation> rechercheConversations();

    List<Conversation> rechercherConversationsSansReponse();

    List<Conversation> rechercherConversations(Client c);

    List<Conversation> rechercherConversations(UtilisateurHardis u);

    Conversation rechercherConversation(Devis d);
    
}
