/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.NhaSanXuat;

/**
 *
 * @author ElC
 */
public class NhaSanXuatDAO extends GenericDAO<NhaSanXuat, Integer> {

   
    @Override
    public void deleteById(Integer id) {
        NhaSanXuat entity = findById(id);
        entity.setTinhTrang(new Integer(0));
        super.save(entity);
    }

    @Override
    public Integer insert(NhaSanXuat entity) {
    	Integer id =  new Integer(getMaxId() + 1);
        entity.setId(id);
        super.save(entity);
        return id;
    }
    public Long countAll(NhaSanXuat entity){
    	Long all = new Long(-1);
        Session _session = null;
        try{
            startOperation();
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            searchByInstanceCriteria(crit, entity);
            crit.setProjection(Projections.rowCount());
            all =  (Long)crit.uniqueResult();
            
        }catch(HibernateException ex){
            
        }finally{
            closeSession();
        }
        return all;
        
    }
    @SuppressWarnings("unchecked")
	public List<NhaSanXuat> searchByInstance(NhaSanXuat entity, int pageNo, int recordsPerPage) {
        List<NhaSanXuat> _nsxList = new ArrayList<NhaSanXuat>();
        Session _session = null;
        try{
            startOperation();  
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            crit.addOrder(Order.asc("id"));
            searchByInstanceCriteria(crit, entity);
            
            crit.setFirstResult((pageNo - 1) * recordsPerPage);
            crit.setMaxResults(recordsPerPage);
            
            _nsxList = crit.list();
            
        }catch(HibernateException ex){
            
        }finally{
            closeSession();
        }
        
        return _nsxList;  
    }
    private void searchByInstanceCriteria(Criteria crit, NhaSanXuat entity){
        if(entity.getId() != -1 ){
            crit.add(Restrictions.eq("id", entity.getId()));
        }
        if(entity.getTenNhaSanXuat() != ""){
            crit.add(Restrictions.ilike("tenNhaSanXuat", "%" + entity.getTenNhaSanXuat() + "%"));
        }
        if(entity.getTinhTrang() != -1){
            crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
        }
        if(entity.getQuocGia().getId() != -1){
            crit.add(Restrictions.eq("quocGia", entity.getQuocGia()));
        }
    }
    
}
