/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

/**
 *
 * @author ElC
 */
public abstract class GenericDAO<T, PK extends Serializable> implements IDao<T, PK> {

    protected Session _hibernateSession;
    private Class<T> _entity;
    protected Transaction _transaction;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
	public GenericDAO() {
        this._entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    protected void startOperation() throws HibernateException {
        _hibernateSession = HibernateUtil.getSessionFactory().openSession();
        _transaction = _hibernateSession.beginTransaction();
    }

    @Override
    public Class<T> getClassEntity() {
        return this._entity;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T findById(PK id) {
        T t = null;
        try {
            startOperation();
            t = (T) _hibernateSession.get(getClassEntity(), id);
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }

        return t;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll(int pageNo, int recordsPerPage) {
        List<T> list = new ArrayList<T>();
        try {
            startOperation();
            String hql = "select obj from " + this.getClassName() + " obj where tinh_trang=:stt order by obj.id asc";
            Query query = _hibernateSession.createQuery(hql);
            query.setInteger("stt", 1);
            query.setFirstResult((pageNo - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            
            list = query.list();
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }
        return list;

    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        try {
            startOperation();
            String hql = "select obj from " + this.getClassName() + " obj where tinh_trang=:stt order by obj.id asc";
            Query query = _hibernateSession.createQuery(hql);
            query.setInteger("stt", 1);
            list = query.list();
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }
        return list;

    }

    @Override
    public T save(T entity) {
        try {
            startOperation();
            _hibernateSession.saveOrUpdate(entity);
            _transaction.commit();
        } catch (HibernateException e) {
            _transaction.rollback();
        } finally {
            _hibernateSession.close();
        }
        return entity;
    }

    @Override
    public Long countAll(boolean stt) {
    	Long all;
        all = new Long(-1);
        Session _session = null;
        try {
        	startOperation();
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            if(stt == true){
            	crit.add(Restrictions.eq("tinhTrang", new Integer(1)));
            }
            if(stt == false){
            	crit.add(Restrictions.eq("tinhTrang", new Integer(0)));
            }
            crit.setProjection(Projections.rowCount());
            all =  (Long)crit.uniqueResult();
        } catch (HibernateException e) {
            _transaction.rollback();
        } finally {
            _hibernateSession.close();
        }
        return all;
    }

    protected String getClassName() {
        return this._entity.getName();
    }

    @Override
    public int getMaxId() {
        Integer max = -1;
        try {
            startOperation();
            String hql = "select max(id) from " + this.getClassName();
            Query query = _hibernateSession.createQuery(hql);
            max = (Integer) query.list().get(0);
        } catch (HibernateException e) {
            _transaction.rollback();
        } finally {
            _hibernateSession.close();
        }
        return max;
    }

    @Override
    public void deleteById(PK id) {
    }

    @Override
    public Integer insert(T entity) {
    	return null;
    }

    @Override
    public List<T> findByQuery(String query) {
        return null;
    }

    protected void rollback() throws HibernateException {
        this._transaction.rollback();
    }
    protected void commit() throws HibernateException{
        this._transaction.commit();
    }
    protected void closeSession() throws HibernateException {
        this._hibernateSession.close();
    }
    protected Session getSession(){
        return this._hibernateSession;
        
    }
}
