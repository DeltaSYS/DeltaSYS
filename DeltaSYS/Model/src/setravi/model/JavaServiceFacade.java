package setravi.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
    private static final boolean isAutoCommit = true;
    private final EntityManagerHelper entityManagerHelper;

    public JavaServiceFacade() {
        entityManagerHelper = new EntityManagerHelper("ModelFacade", isAutoCommit);
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = entityManagerHelper.getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public SetraviPuntos persistSetraviPuntos(SetraviPuntos setraviPuntos) {
        return (SetraviPuntos)entityManagerHelper.persistEntity(setraviPuntos);
    }

    public SetraviPuntos mergeSetraviPuntos(SetraviPuntos setraviPuntos) {
        return (SetraviPuntos)entityManagerHelper.mergeEntity(setraviPuntos);
    }

    public void removeSetraviPuntos(SetraviPuntos setraviPuntos) {
        setraviPuntos = entityManagerHelper.getEntityManager().find(SetraviPuntos.class, new SetraviPuntosPK(setraviPuntos.getFecha_hora(), setraviPuntos.getLicencia()));
        entityManagerHelper.removeEntity(setraviPuntos);
    }

    /** <code>select o from SetraviPuntos o</code> */
    public List<SetraviPuntos> getSetraviPuntosFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("SetraviPuntos.findAll").getResultList();
    }

    public SetraviDatos persistSetraviDatos(SetraviDatos setraviDatos) {
        return (SetraviDatos)entityManagerHelper.persistEntity(setraviDatos);
    }

    public SetraviDatos mergeSetraviDatos(SetraviDatos setraviDatos) {
        return (SetraviDatos)entityManagerHelper.mergeEntity(setraviDatos);
    }

    public void removeSetraviDatos(SetraviDatos setraviDatos) {
        setraviDatos = entityManagerHelper.getEntityManager().find(SetraviDatos.class, setraviDatos.getNum_placa());
        entityManagerHelper.removeEntity(setraviDatos);
    }

    /** <code>select o from SetraviDatos o</code> */
    public List<SetraviDatos> getSetraviDatosFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("SetraviDatos.findAll").getResultList();
    }
    
    public SetraviDatos getSetraviDatosFindDatosPlaca(String num_placa) 
    {
        Query objFind = entityManagerHelper.getEntityManager().createNamedQuery("SetraviDatos.findDatosPlaca");
        objFind.setParameter("num_placa", num_placa);
        return (SetraviDatos)objFind.getResultList().get(0);
    }

    private class EntityManagerHelper {
        final private EntityManagerFactory _entityManagerFactory;
        final private boolean _isAutoCommit;

        private EntityManager _entityManager;

        EntityManagerHelper(String persistenceUnit, boolean isAutoCommit) {
            _entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
            _isAutoCommit = isAutoCommit;
        }

        public EntityManager getEntityManager() {
            if (_entityManager == null) {
                _entityManager = _entityManagerFactory.createEntityManager();
            }

            return _entityManager;
        }

        public EntityTransaction getEntityTransaction() {
            return getEntityManager().getTransaction();
        }

        public void commitTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.commit();
            }

            _closeEntityManager();
        }

        public void rollbackTransaction() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            _closeEntityManager();
        }

        public boolean isTransactionDirty() {
            return (!_isAutoCommit && getEntityTransaction().isActive());
        }

        public Object persistEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.persist(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public Object mergeEntity(Object entity) {
            _beginTransactionIfNeeded();
            entity = _entityManager.merge(entity);
            _commitTransactionIfNeeded();

            return entity;
        }

        public void removeEntity(Object entity) {
            _beginTransactionIfNeeded();
            _entityManager.remove(entity);
            _commitTransactionIfNeeded();
        }

        private void _beginTransactionIfNeeded() {
            final EntityTransaction entityTransaction = getEntityTransaction();
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
        }

        private void _commitTransactionIfNeeded() {
            if (_isAutoCommit) {
                commitTransaction();
            }
        }

        private void _closeEntityManager() {
            if (_entityManager != null && _entityManager.isOpen()) {
                _entityManager.close();
            }

            _entityManager = null;
        }
    }
}
