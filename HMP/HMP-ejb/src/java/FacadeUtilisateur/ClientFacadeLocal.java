/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Client;
import GestionUtilisateur.Entreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface ClientFacadeLocal {

    void create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();

    Client supprimerClient(Client c);

    Client rechercheClient(long id);

    List<Client> rechercheClient();

    Client creerClient(String nom, String prenom, String mail, String tel, String mdp);

    Client modifierClient(Client c, String nom, String prenom, String mail, String tel);

    Client modifierClientMDP(Client c, String mdp);
    
}
