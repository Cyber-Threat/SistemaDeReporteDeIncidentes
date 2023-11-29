package edu.utn.SistemaDeReporteDeIncidentes.DataAccessObjects;

import edu.utn.SistemaDeReporteDeIncidentes.Controladores.EntityManagerConfig;
import edu.utn.SistemaDeReporteDeIncidentes.Modelos.Persona;
import edu.utn.SistemaDeReporteDeIncidentes.Modelos.PersonaCliente;
import edu.utn.SistemaDeReporteDeIncidentes.Modelos.PersonalTecnico;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Personas_DataAccessObject extends  DataAccessObject<Persona> {
    private static EntityManager entityManager = EntityManagerConfig.getEntityManager();
    private static EntityTransaction transaction  = entityManager.getTransaction();
    public void create(Persona p){
        transaction.begin();
            entityManager.persist(p); // PERSISTENCIA DEL OBJETO P
        transaction.commit();
    }
    public Persona findOne(Persona p){
        return entityManager.find(Persona.class, p.getCuit());
    }
    public PersonaCliente update(PersonaCliente personaCliente){
        transaction.begin();
            PersonaCliente entityMerged = entityManager.merge(personaCliente);
        transaction.commit();
        return entityMerged;
    }
    public PersonalTecnico update(PersonalTecnico personalTecnico){
        transaction.begin();
        PersonalTecnico entityMerged = entityManager.merge(personalTecnico);
        transaction.commit();
        return entityMerged;
    }
    public void delete(PersonaCliente personaCliente){
        transaction.begin();
            entityManager.remove(personaCliente);
        transaction.commit();
    }
    public void delete(PersonalTecnico personalTecnico){
        transaction.begin();
            entityManager.remove(personalTecnico);
        transaction.commit();
    }
    public List<Persona> findAll(){ return null;}
}
