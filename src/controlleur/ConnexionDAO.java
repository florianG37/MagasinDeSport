package controlleur;

import modele.Personne;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.ChefMagasin;

import java.util.List;

public class ConnexionDAO {

    private EntityManager entityManager;

    //Getters et setters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Vérifie si c'est l'identifiant du chef
     * @param identifiant champs entrée par l'utilisateur
     * @return vrai si c'est le chef faux sinon
     */
    public boolean leChefSeConnecte(String identifiant){
        ChefMagasin chef = null;
        try {
            Query query = entityManager.createQuery("from ChefMagasin chef where chef.identifiant =  ?1");
            chef = (ChefMagasin) query.setParameter(1, identifiant).getSingleResult();

        }catch(Exception e)
        {
            System.out.println("Ce n'est pas un chef");
        }

        if(chef == null)
        {
            return false;
        }else{
            return true;
        }
    }

    /**
     * Recupere la saisie de lutilisateur et compare dans la base de donnée si il existe
     * @param identifiant champs entrée par l'utilisateur
     * @return  vrai si c'est un identifiant valide faux sinon
     */
    public boolean verifierIdentifiant(String identifiant){

        identifiant = identifiant.toLowerCase();
        Query query = entityManager.createQuery(" from Personne personne where personne.identifiant = ?1 ");
        Personne personne = (Personne) query.setParameter(1, identifiant).getSingleResult();

        if(personne == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Est APPELE si verifier identifant est vrai, elle verifie si le mot de passe correspond a lidentifant
     * @param identifiant identifiant valide
     * @param motDePasse mot de passe donnee
     * @return vrai si il mot de passe est bon faux sinon
     */
    public boolean verifierMotDePasse(String identifiant, String motDePasse){
        Query query = entityManager.createQuery(" from Personne personne where personne.identifiant = ?1 and personne.motDePasse = ?2");
        query.setParameter(1, identifiant);
        query.setParameter(2, motDePasse);
        List<Personne> listP =  query.getResultList();
        if(listP.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
