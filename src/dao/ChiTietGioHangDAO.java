package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.ChiTietGioHang;
import pojos.GioHang;

public class ChiTietGioHangDAO extends GenericDAO<ChiTietGioHang, Integer> {

	public Long countByCart(Integer id) {
		Long all;
        all = new Long(-1);
        GioHang gioHang = new GioHang(id);
        Session _session = null;
        try {
        	startOperation();
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            crit.setProjection(Projections.rowCount());
            crit.add(Restrictions.eq("gioHang", gioHang));
            all =  (Long)crit.uniqueResult();
        } catch (HibernateException e) {
            _transaction.rollback();
        } finally {
            _hibernateSession.close();
        }
        return all;
	}
	public Map getTopTenSellingProducts(){
    	Session _session = null;
    	Map<String, String> result = null;
        try{
            startOperation();  
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity(), "ctgh");
            crit.createAlias("sanPham", "sp");
            ProjectionList projectionList = Projections.projectionList();
            
            projectionList.add(Projections.groupProperty("sp.id"));
            projectionList.add(Projections.groupProperty("sp.tenSanPham"));
            projectionList.add(Projections.alias(Projections.sum("ctgh.soLuong"), "ooo.soluong"));
           
            //crit.add(Restrictions.eq("sp.id", 26));
            crit.addOrder(Order.desc("ooo.soluong"));
            crit.setProjection(projectionList);
            crit.setMaxResults(10);
			List<Object> results = crit.list();

            result = new LinkedHashMap<String, String>();
            for (Object object : results) {
				Object[] obj = (Object[]) object;
				result.put((String) obj[1], obj[2].toString());
			}
             
        }catch(HibernateException ex){
            
        }finally{
            closeSession();
        }
        return result;
    }
	@SuppressWarnings("unchecked")
	public List<ChiTietGioHang> findByCart(Integer id, Integer page,
			Integer recordsPerPage) {
		List<ChiTietGioHang> list = new ArrayList<ChiTietGioHang>();
		GioHang gioHang = new GioHang(id);
		Session _session = null;
        try {
            startOperation();
            _session = getSession();
            Criteria crit = _session.createCriteria(getClassEntity());
            crit.add(Restrictions.eq("gioHang", gioHang));
            crit.setFirstResult((page - 1) * recordsPerPage);
            crit.setMaxResults(recordsPerPage);
            list = crit.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            _hibernateSession.close();
        }
        return list;
	}

}
