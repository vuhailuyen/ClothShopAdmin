
package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.LoaiSanPham;

/**
 *
 * @author ElC
 */
public class LoaiSanPhamDAO extends GenericDAO<LoaiSanPham, Integer> {

    
    @Override
    public void deleteById(Integer id) {
        LoaiSanPham entity = findById(id);
        entity.setTinhTrang(new Integer(0));
        super.save(entity);
    }

    @Override
    public Integer insert(LoaiSanPham entity) {
    	Integer id =  new Integer(getMaxId() + 1);
        entity.setId(id);
        super.save(entity);
        return id;
    }
    
    public Long countAll(LoaiSanPham entity){
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
	public List<LoaiSanPham> searchByInstance(LoaiSanPham entity, int pageNo, int recordsPerPage) {
        List<LoaiSanPham> _lspList = new ArrayList<LoaiSanPham>();
        Session _session = null;
        try{
            startOperation();  
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            crit.addOrder(Order.asc("id"));
            searchByInstanceCriteria(crit, entity);
            
            crit.setFirstResult((pageNo - 1) * recordsPerPage);
            crit.setMaxResults(recordsPerPage);
            
            _lspList = crit.list();
            
        }catch(HibernateException ex){
            
        }finally{
            closeSession();
        }
        
        return _lspList;
        
    }
    private void searchByInstanceCriteria(Criteria crit, LoaiSanPham entity){
        if(entity.getId() != -1 ){
            crit.add(Restrictions.eq("id", entity.getId()));
        }
        if(entity.getTenLoai() != ""){
        	Disjunction or = Restrictions.disjunction();
            or.add(Restrictions.ilike("tenLoai", "%" + entity.getTenLoai() + "%"));
            or.add(Restrictions.ilike("tenLoai", entity.getTenLoai() + "%"));
            or.add(Restrictions.ilike("tenLoai", "%" + entity.getTenLoai()));
            or.add(Restrictions.ilike("tenLoai", entity.getTenLoai()));
            crit.add(or);
        }
        if(entity.getTinhTrang() != -1){
            crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
        }
    }
    
}
