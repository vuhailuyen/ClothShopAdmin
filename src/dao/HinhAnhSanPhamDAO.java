/*
O * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pojos.HinhAnhSanPham;

/**
 *
 * @author ElC
 */
public class HinhAnhSanPhamDAO extends GenericDAO<HinhAnhSanPham, Integer> {
     @Override
    public void deleteById(Integer id) {
        HinhAnhSanPham entity = findById(id);
        entity.setTinhTrang(new Integer(1));
        super.deleteById(id);
    }

    @Override
    public Integer insert(HinhAnhSanPham entity) {
    	Integer id =  new Integer(getMaxId() + 1);
        entity.setId(id);
        super.save(entity);
        return id;
    }
    
    public List<HinhAnhSanPham> findBySanPham(Integer sanPhamId){
    	List<HinhAnhSanPham> list = new ArrayList<HinhAnhSanPham>();
        try {
            startOperation();
            String hql = "select obj from " + this.getClassName() + " obj where san_pham=:sanpham order by obj.id asc";
            Query query = _hibernateSession.createQuery(hql);
            query.setInteger("sanpham", sanPhamId);           
            list = query.list();
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }
        return list;
    }
}
