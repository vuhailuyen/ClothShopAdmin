package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pojos.QuocGia;
public class QuocGiaDAO extends GenericDAO<QuocGia, Integer> {
	public List<QuocGia> findAll() {
        List<QuocGia> list = new ArrayList<QuocGia>();
        try {
            startOperation();
            String hql = "select obj from " + this.getClassName() + " obj order by obj.tenQuocGia asc";
            Query query = _hibernateSession.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }
        return list;

    }
}
