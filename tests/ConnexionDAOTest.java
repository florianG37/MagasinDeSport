import controleur.dao.ConnexionDAO;
import modele.ChefMagasin;
import modele.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionDAOTest {

    @Mock
    EntityManager entityManagerMock;
    @Mock
    private Query queryMock;

    @BeforeEach
    public void setup(){
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void devrait_etre_un_chef_qui_se_connecte(){
        when(entityManagerMock.createQuery("from ChefMagasin chef where chef.identifiant =  ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"Id")).thenReturn(queryMock);

        ChefMagasin chef = new ChefMagasin();
        when(queryMock.getSingleResult()).thenReturn(chef);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);
        assertEquals(con.leChefSeConnecte("Id"),true);
    }

    @Test
    public void devrait_etre_un_vendeur_qui_se_connecte(){
        when(entityManagerMock.createQuery("from ChefMagasin chef where chef.identifiant =  ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"Id")).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(null);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);
        assertEquals(con.leChefSeConnecte("Id"),false);
    }

    @Test
    public void identifiant_correct(){
        when(entityManagerMock.createQuery(" from Personne personne where personne.identifiant = ?1 ")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"id")).thenReturn(queryMock);

        Personne vendeur = new  Personne();
        when(queryMock.getSingleResult()).thenReturn(vendeur);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);

        assertEquals(con.verifierIdentifiant("id"),true);

    }
    @Test
    public void identifiant_incorrect(){
        when(entityManagerMock.createQuery(" from Personne personne where personne.identifiant = ?1 ")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"id")).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(null);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);

        assertEquals(con.verifierIdentifiant("id"),false);
    }

    @Test
    public void motDePasse_associe_a_un_identifiant_est_incorrect(){
        when(entityManagerMock.createQuery(" from Personne personne where personne.identifiant = ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"id")).thenReturn(queryMock);

        Personne personne = new Personne();
        personne.setMotDePasse("mdpdifferent");
        when(queryMock.getSingleResult()).thenReturn(personne);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);

        assertEquals(con.verifierMotDePasse("id", "mdp"),false);
    }

    @Test
    public void motDePasse_associe_a_un_identifiant_est_correct(){

        when(entityManagerMock.createQuery(" from Personne personne where personne.identifiant = ?1")).thenReturn(queryMock);
        when(queryMock.setParameter(1,"id")).thenReturn(queryMock);

        Personne personne = new Personne();
        personne.setMotDePasse("mdp");
        when(queryMock.getSingleResult()).thenReturn(personne);

        ConnexionDAO con = new ConnexionDAO();
        con.setEntityManager(entityManagerMock);

        assertEquals(con.verifierMotDePasse("id", "mdp"),true);
    }
}