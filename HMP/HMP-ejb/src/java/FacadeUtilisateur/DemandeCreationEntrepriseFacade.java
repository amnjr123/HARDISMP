/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.Entreprise;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class DemandeCreationEntrepriseFacade extends AbstractFacade<DemandeCreationEntreprise> implements DemandeCreationEntrepriseFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeCreationEntrepriseFacade() {
        super(DemandeCreationEntreprise.class);
    }
    
    @Override
    public DemandeCreationEntreprise creerDemandeCreationEntreprise(String nom, String siret, String adresse, Agence a, Client c) {
        DemandeCreationEntreprise e = new DemandeCreationEntreprise();
        e.setNom(nom);
        e.setSiret(siret);
        e.setAdresseFacturation(adresse);
        e.setAgence(a);
        e.setClient(c);
        e.setDateDemande(new Date());
        create(e);
        return e;
    }
    
    @Override
    public DemandeCreationEntreprise supprimerDemandeCreationEntreprise(DemandeCreationEntreprise e) {
        remove(e);
        return e;
    }

    @Override
    public DemandeCreationEntreprise rechercheDemandeCreationEntreprise(long id) {
        return find(id);
    }

    @Override
    public DemandeCreationEntreprise rechercheDemandeCreationEntrepriseSiret(String siret) {
        Query requete = getEntityManager().createQuery("select e from DemandeCreationEntreprise e where e.siret=:siret");
        requete.setParameter("siret", siret);
        if (!requete.getResultList().isEmpty()) {
            return (DemandeCreationEntreprise) requete.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public List<DemandeCreationEntreprise> rechercheDemandeCreationEntreprise() {
        return findAll();
    }
    
    @Override
    public DemandeCreationEntreprise rechercheDemandeCreationEntrepriseClient(Client client) {
        Query requete = getEntityManager().createQuery("select e from DemandeCreationEntreprise e where e.client=:client");
        requete.setParameter("client", client);
        if (!requete.getResultList().isEmpty()) {
            return (DemandeCreationEntreprise) requete.getSingleResult();
        } else {
            return null;
        }
    }
}
