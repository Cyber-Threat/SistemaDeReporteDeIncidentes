package edu.utn.SistemaDeReporteDeIncidentes.DataAccessObjects;

import edu.utn.SistemaDeReporteDeIncidentes.Controladores.EntityManagerConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class DataAccessObject<T extends Serializable>  {
    private Class< T > clazz;
    EntityManager entityManager = EntityManagerConfig.getEntityManager();
    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }
    public T findOne( int id ){
        return entityManager.find( clazz, id );
    }
    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }
    public void create( T entity ){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist( entity );
        tx.commit();
    }
    public T update( T entity ){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T entityMerged = entityManager.merge( entity );
        tx.commit();
        return entityMerged;
    }
    public void delete( T entity ){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove( entity );
        tx.commit();
    }
}
