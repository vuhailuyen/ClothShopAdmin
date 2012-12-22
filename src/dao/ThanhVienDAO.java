/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.NhaSanXuat;
import pojos.ThanhVien;

/**
 *
 * @author ElC
 */
public class ThanhVienDAO extends GenericDAO<ThanhVien, Integer> {

  
    @Override
    public void deleteById(Integer id) {
        ThanhVien entity = findById(id);
        entity.setTinhTrang(new Integer(0));
        super.deleteById(id);
    }

    @Override
    public Integer insert(ThanhVien entity) {
    	Integer id =  new Integer(getMaxId() + 1);
        entity.setId(id);
        super.save(entity);
        return id;
    }
    
    public void banUser(Date _expireDate){
        
    }
    @SuppressWarnings("unchecked")
	public List<ThanhVien> searchByInstance(ThanhVien entity, int pageNo, int recordsPerPage) {
        List<ThanhVien> _tvList = new ArrayList<ThanhVien>();
        Session _session = null;
        try{
            startOperation();  
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            crit.addOrder(Order.asc("id"));
            searchByInstanceCriteria(crit, entity);
            
            crit.setFirstResult((pageNo - 1) * recordsPerPage);
            crit.setMaxResults(recordsPerPage);
            
            _tvList = crit.list();
            
        }catch(HibernateException ex){
            
        }finally{
            closeSession();
        }
        
        return _tvList;  
    }
    public Long countAll(ThanhVien entity){
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
    private void searchByInstanceCriteria(Criteria crit, ThanhVien entity){
        if(entity.getId() != -1 ){
            crit.add(Restrictions.eq("id", entity.getId()));
        }
        if(entity.getHoVaTen() != ""){
            crit.add(Restrictions.ilike("hoVaTen", "%" + entity.getHoVaTen() + "%"));
        }
        if(entity.getTinhTrang() != -1){
            crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
        }
        if(entity.getTenDangNhap() != ""){
            crit.add(Restrictions.ilike("tenDangNhap", "%" +  entity.getTenDangNhap() + "%"));
        }
    }
    
}
