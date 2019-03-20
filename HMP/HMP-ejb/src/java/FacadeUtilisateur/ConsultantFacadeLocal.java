/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Consultant;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface ConsultantFacadeLocal {

    void create(Consultant consultant);

    void edit(Consultant consultant);

    void remove(Consultant consultant);

    Consultant find(Object id);

    List<Consultant> findAll();

    List<Consultant> findRange(int[] range);

    int count();

    Consultant modifierConsultant(Consultant c, String mail, String tel, boolean actifInactif);

    Consultant supprimerConsultant(Consultant c);

    Consultant rechercheConsultant(long id);

    List<Consultant> rechercheConsultant();

    Consultant creerConsultant(String nom, String prenom, String mail, String tel, ProfilTechnique profil, float plafondDelegation, Agence agence, List<Offre> offres);

    Consultant modifierConsultant(Consultant c, String nom, String prenom, String mail, String tel, ProfilTechnique profil, boolean actifInactif, float plafondDelegation, List<Offre> offres, Agence a);

    
}
