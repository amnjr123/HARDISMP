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
    
    @Override
    public DevisStandard creerDevisStandard(float montant, String commentaireClient, ServiceStandard serviceStandard, ReferentLocal rl, Agence agence){
        DevisStandard d = new DevisStandard();
        d.setStatut(StatutDevis.ReponseEnCours);
        d.setMotifRefus("");
        d.setDateCreation(new Date());
        d.setMontant(montant);
        d.setCommentaireClient(commentaireClient);
        d.setServiceStandard(serviceStandard);
        d.setUtilisateurHardis(rl);
        d.setAgence(agence);
        create(d);  
        return d;
    }
    
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
