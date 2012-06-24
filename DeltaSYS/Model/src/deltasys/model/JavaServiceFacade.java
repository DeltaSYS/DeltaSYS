package deltasys.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.sf.json.JSONObject;

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

    public DsUsuarios persistDsUsuarios(DsUsuarios dsUsuarios) {
        return (DsUsuarios)entityManagerHelper.persistEntity(dsUsuarios);
    }

    public DsUsuarios mergeDsUsuarios(DsUsuarios dsUsuarios) {
        return (DsUsuarios)entityManagerHelper.mergeEntity(dsUsuarios);
    }

    public void removeDsUsuarios(DsUsuarios dsUsuarios) {
        dsUsuarios = entityManagerHelper.getEntityManager().find(DsUsuarios.class, dsUsuarios.getOid());
        entityManagerHelper.removeEntity(dsUsuarios);
    }

    /** <code>select o from DsUsuarios o</code> */
    public List<DsUsuarios> getDsUsuariosFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsUsuarios.findAll").getResultList();
    }
    
    public DsUsuarios getDsUsuariosFindUsuario(String oid) 
    {
        Query objFind = entityManagerHelper.getEntityManager().createNamedQuery("DsUsuarios.findUsuario");
        objFind.setParameter("oid", oid);
        return (DsUsuarios)objFind.getResultList().get(0);
    }
    
    public List<DsUsuarios> getDsUsuariosCatalogo(String qry,JSONObject parameters) 
    {
        Query q = entityManagerHelper.getEntityManager().createNamedQuery(qry);
               
        q.setParameter("oid", parameters.getString("oid"));
        q.setParameter("nombre", parameters.getString("nombre"));
        q.setParameter("id_perfil", parameters.getLong("id_perfil"));
        q.setParameter("all", parameters.getLong("all"));
      
      return q.getResultList();
    }    
    
    public List<DsUsuarios> getDsUsuariosLogin(String oid,String password) 
    {
        Query q = entityManagerHelper.getEntityManager().createNamedQuery("DsUsuarios.findLogin");
               
        q.setParameter("oid",oid);
        q.setParameter("password",password);
      
        return q.getResultList();
    }

    public DsSectores persistDsSectores(DsSectores dsSectores) {
        return (DsSectores)entityManagerHelper.persistEntity(dsSectores);
    }

    public DsSectores mergeDsSectores(DsSectores dsSectores) {
        return (DsSectores)entityManagerHelper.mergeEntity(dsSectores);
    }

    public void removeDsSectores(DsSectores dsSectores) {
        dsSectores = entityManagerHelper.getEntityManager().find(DsSectores.class, dsSectores.getId_setor());
        entityManagerHelper.removeEntity(dsSectores);
    }

    /** <code>select o from DsSectores o</code> */
    public List<DsSectores> getDsSectoresFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsSectores.findAll").getResultList();
    }

    public DsInfracciones persistDsInfracciones(DsInfracciones dsInfracciones) {
        return (DsInfracciones)entityManagerHelper.persistEntity(dsInfracciones);
    }

    public DsInfracciones mergeDsInfracciones(DsInfracciones dsInfracciones) {
        return (DsInfracciones)entityManagerHelper.mergeEntity(dsInfracciones);
    }

    public void removeDsInfracciones(DsInfracciones dsInfracciones) {
        dsInfracciones = entityManagerHelper.getEntityManager().find(DsInfracciones.class, dsInfracciones.getId_folio());
        entityManagerHelper.removeEntity(dsInfracciones);
    }

    /** <code>select o from DsInfracciones o</code> */
    public List<DsInfracciones> getDsInfraccionesFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsInfracciones.findAll").getResultList();
    }

    public DsIntervalo persistDsIntervalo(DsIntervalo dsIntervalo) {
        return (DsIntervalo)entityManagerHelper.persistEntity(dsIntervalo);
    }

    public DsIntervalo mergeDsIntervalo(DsIntervalo dsIntervalo) {
        return (DsIntervalo)entityManagerHelper.mergeEntity(dsIntervalo);
    }

    public void removeDsIntervalo(DsIntervalo dsIntervalo) {
        dsIntervalo = entityManagerHelper.getEntityManager().find(DsIntervalo.class, dsIntervalo.getId_intervalo());
        entityManagerHelper.removeEntity(dsIntervalo);
    }

    /** <code>select o from DsIntervalo o</code> */
    public List<DsIntervalo> getDsIntervaloFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsIntervalo.findAll").getResultList();
    }

    public DsSectorOficial persistDsSectorOficial(DsSectorOficial dsSectorOficial) {
        return (DsSectorOficial)entityManagerHelper.persistEntity(dsSectorOficial);
    }

    public DsSectorOficial mergeDsSectorOficial(DsSectorOficial dsSectorOficial) {
        return (DsSectorOficial)entityManagerHelper.mergeEntity(dsSectorOficial);
    }

    public void removeDsSectorOficial(DsSectorOficial dsSectorOficial) {
        dsSectorOficial = entityManagerHelper.getEntityManager().find(DsSectorOficial.class, new DsSectorOficialPK(dsSectorOficial.getId_sector(), dsSectorOficial.getOid()));
        entityManagerHelper.removeEntity(dsSectorOficial);
    }

    /** <code>select o from DsSectorOficial o</code> */
    public List<DsSectorOficial> getDsSectorOficialFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsSectorOficial.findAll").getResultList();
    }

    public DsPerfiles persistDsPerfiles(DsPerfiles dsPerfiles) {
        return (DsPerfiles)entityManagerHelper.persistEntity(dsPerfiles);
    }

    public DsPerfiles mergeDsPerfiles(DsPerfiles dsPerfiles) {
        return (DsPerfiles)entityManagerHelper.mergeEntity(dsPerfiles);
    }

    public void removeDsPerfiles(DsPerfiles dsPerfiles) {
        dsPerfiles = entityManagerHelper.getEntityManager().find(DsPerfiles.class, dsPerfiles.getId_perfil());
        entityManagerHelper.removeEntity(dsPerfiles);
    }

    /** <code>select o from DsPerfiles o</code> */
    public List<DsPerfiles> getDsPerfilesFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsPerfiles.findAll").getResultList();
    }
    public DsPerfiles getDsPerfilesFindPerfil(Long id_perfil) 
    {
      Query objFind = entityManagerHelper.getEntityManager().createNamedQuery("DsPerfiles.findPerfil");
      objFind.setParameter("id_perfil", id_perfil);
      return (DsPerfiles)objFind.getResultList().get(0);
    }

    public DsFotos persistDsFotos(DsFotos dsFotos) {
        return (DsFotos)entityManagerHelper.persistEntity(dsFotos);
    }

    public DsFotos mergeDsFotos(DsFotos dsFotos) {
        return (DsFotos)entityManagerHelper.mergeEntity(dsFotos);
    }

    public void removeDsFotos(DsFotos dsFotos) {
        dsFotos = entityManagerHelper.getEntityManager().find(DsFotos.class, new DsFotosPK(dsFotos.getId_folio(), dsFotos.getId_foto()));
        entityManagerHelper.removeEntity(dsFotos);
    }

    /** <code>select o from DsFotos o</code> */
    public List<DsFotos> getDsFotosFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsFotos.findAll").getResultList();
    }

    public DsCancelaciones persistDsCancelaciones(DsCancelaciones dsCancelaciones) {
        return (DsCancelaciones)entityManagerHelper.persistEntity(dsCancelaciones);
    }

    public DsCancelaciones mergeDsCancelaciones(DsCancelaciones dsCancelaciones) {
        return (DsCancelaciones)entityManagerHelper.mergeEntity(dsCancelaciones);
    }

    public void removeDsCancelaciones(DsCancelaciones dsCancelaciones) {
        dsCancelaciones = entityManagerHelper.getEntityManager().find(DsCancelaciones.class, dsCancelaciones.getId_folio());
        entityManagerHelper.removeEntity(dsCancelaciones);
    }

    /** <code>select o from DsCancelaciones o</code> */
    public List<DsCancelaciones> getDsCancelacionesFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsCancelaciones.findAll").getResultList();
    }

    public DsPerfilesReglamento persistDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        return (DsPerfilesReglamento)entityManagerHelper.persistEntity(dsPerfilesReglamento);
    }

    public DsPerfilesReglamento mergeDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        return (DsPerfilesReglamento)entityManagerHelper.mergeEntity(dsPerfilesReglamento);
    }

    public void removeDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        dsPerfilesReglamento = entityManagerHelper.getEntityManager().find(DsPerfilesReglamento.class, new DsPerfilesReglamentoPK(dsPerfilesReglamento.getId_articulo(), dsPerfilesReglamento.getId_fraccion(), dsPerfilesReglamento.getId_inciso(), dsPerfilesReglamento.getId_perfil()));
        entityManagerHelper.removeEntity(dsPerfilesReglamento);
    }

    /** <code>select o from DsPerfilesReglamento o</code> */
    public List<DsPerfilesReglamento> getDsPerfilesReglamentoFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsPerfilesReglamento.findAll").getResultList();
    }

    public DsUbicaciones persistDsUbicaciones(DsUbicaciones dsUbicaciones) {
        return (DsUbicaciones)entityManagerHelper.persistEntity(dsUbicaciones);
    }

    public DsUbicaciones mergeDsUbicaciones(DsUbicaciones dsUbicaciones) {
        return (DsUbicaciones)entityManagerHelper.mergeEntity(dsUbicaciones);
    }

    public void removeDsUbicaciones(DsUbicaciones dsUbicaciones) {
        dsUbicaciones = entityManagerHelper.getEntityManager().find(DsUbicaciones.class, dsUbicaciones.getOid());
        entityManagerHelper.removeEntity(dsUbicaciones);
    }

    /** <code>select o from DsUbicaciones o</code> */
    public List<DsUbicaciones> getDsUbicacionesFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsUbicaciones.findAll").getResultList();
    }

    public DsReglamento persistDsReglamento(DsReglamento dsReglamento) {
        return (DsReglamento)entityManagerHelper.persistEntity(dsReglamento);
    }

    public DsReglamento mergeDsReglamento(DsReglamento dsReglamento) {
        return (DsReglamento)entityManagerHelper.mergeEntity(dsReglamento);
    }

    public void removeDsReglamento(DsReglamento dsReglamento) {
        dsReglamento = entityManagerHelper.getEntityManager().find(DsReglamento.class, new DsReglamentoPK(dsReglamento.getId_articulo(), dsReglamento.getId_fraccion(), dsReglamento.getId_inciso()));
        entityManagerHelper.removeEntity(dsReglamento);
    }

    /** <code>select o from DsReglamento o</code> */
    public List<DsReglamento> getDsReglamentoFindAll() {
        return entityManagerHelper.getEntityManager().createNamedQuery("DsReglamento.findAll").getResultList();
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
