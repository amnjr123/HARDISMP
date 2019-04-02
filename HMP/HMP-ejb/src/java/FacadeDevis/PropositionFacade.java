/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.DevisNonStandard;
import GestionDevis.Proposition;
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
public class PropositionFacade extends AbstractFacade<Proposition> implements PropositionFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropositionFacade() {
        super(Proposition.class);
    }
    
    
    @Override
    public Proposition creerProposition(Date dateDebutValidite, Date dateFinValidite, String cheminDocument, UtilisateurHardis uh, DevisNonStandard devisNonStandard){
        Proposition p = new Proposition();
        //Est-ce que la date de début de validité d'une proposition = date de création de la proposition ?? ou date choisie par l'utilisateur ?
        p.setDateDebutValidite(dateDebutValidite);
        p.setDateFinValidite(dateFinValidite);
        p.setCheminDocument(cheminDocument);
        p.setUtilisateurHardis(uh);
        p.setDevisNonStandard(devisNonStandard);
        devisNonStandard.getPropositions().add(p);
        create(p);  
        devisNonStandard.getPropositions().add(p);
        em.merge(p);
        uh.getPropositions().add(p);
        em.merge(uh);
        return p;
    }
    
    @Override
    public Proposition modifierProposition(Proposition p, Date dateDebutValidite, Date dateFinValidite, String cheminDocument, UtilisateurHardis uh){
        p.setDateDebutValidite(dateDebutValidite);
        p.setDateFinValidite(dateFinValidite);
        p.setCheminDocument(cheminDocument);
        p.setUtilisateurHardis(uh);
        edit(p);
        return p;
    }
    
    @Override
    public Proposition supprimerProposition(Proposition p){
        remove(p);
        return p;
    }
    
    @Override
    public Proposition rechercheProposition(long id){
        return find(id);
    }
    
    @Override
    public List<Proposition> rechercheProposition(){
        return findAll();
    }
    
}
