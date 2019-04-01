/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import Enum.StatutDevis;
import GestionCatalogue.ServiceStandard;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151882
 */
@Stateless
public class DevisStandardFacade extends AbstractFacade<DevisStandard> implements DevisStandardFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevisStandardFacade() {
        super(DevisStandard.class);
    }
    
    /*RAJOUTER CLIENT*/
    @Override
    public DevisStandard creerDevisStandard(ServiceStandard serviceStandard, Client c){
        DevisStandard d = new DevisStandard();
        d.setStatut(StatutDevis.Incomplet);    
        d.setDateCreation(new Date());
        d.setServiceStandard(serviceStandard);
        d.setDtype("DevisStandard");
        d.setClient(c);
        create(d);  
        c.getDeviss().add(d);
        em.merge(c);
        return d;
    }
    
    //Méthode pour qu'un client puisse compléter son devis
    @Override
    public DevisStandard modifierDevisStandard(DevisStandard d, String commentaireClient,ReferentLocal rl, Agence agence){
        if(commentaireClient==null || commentaireClient.equalsIgnoreCase("")){
            d.setStatut(StatutDevis.Incomplet);
        }
        else{
            d.setStatut(StatutDevis.Envoye);
            d.setCommentaireClient(commentaireClient);
            d.setAgence(agence);
            d.setMontant(d.getServiceStandard().getCout());
            d.setUtilisateurHardis(rl); 
        }
        edit(d);
        return d;
    }
    
    //Méthode pour qu'un utilisateur Hardis puisse modifier le prix
    @Override
    public DevisStandard modifierDevisStandard(DevisStandard d, float montant){
        d.setMontant(montant);
        edit(d);
        return d;
    }
    
    @Override
    public DevisStandard transfererDevisStandard(DevisStandard d, UtilisateurHardis uh){
        d.setUtilisateurHardis(uh);
        edit(d);
        return d;
    }
    
    @Override
    public DevisStandard envoyerDevisStandard(DevisStandard d){
        d.setStatut(StatutDevis.Envoye);
        d.setDateEnvoi(new Date());
        edit(d);
        return d;
    }
    
    @Override
    public DevisStandard validerDevisStandard(DevisStandard d){
        d.setStatut(StatutDevis.Valide);
        d.setDateReponse(new Date());
        edit(d);
        return d;
    }
    
    @Override
    public DevisStandard refuserDevisStandard(DevisStandard d, String motifRefus){
        d.setStatut(StatutDevis.Refuse);
        d.setMotifRefus(motifRefus);
        d.setDateReponse(new Date());
        edit(d);
        return d;
    }
    
    @Override
    public DevisStandard acompteDevisStandard(DevisStandard d){
        d.setStatut(StatutDevis.AcompteRegle);
        d.setDateAcompte(new Date());
        edit(d);
        return d;
    }
    
    //Méthode pour qu'un client puisse supprimer un devis INCOMPLET uniquement
    @Override
    public DevisStandard supprimerDevisStandard(DevisStandard d){
        remove(d);
        return d;
    }
    
    @Override
    public DevisStandard rechercheDevisStandard(long id){
        return find(id);
    }
    
    @Override
    public List<DevisStandard> rechercheDevisStandard(){
        return findAll();
    }
    
}
