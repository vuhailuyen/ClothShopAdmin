package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.BinhLuan;
import pojos.GioHang;
import pojos.LoaiSanPham;

public class GioHangDAO extends GenericDAO<GioHang, Integer> {
	public List<GioHang> findAll(int pageNo, int recordsPerPage) {
        List<GioHang> list = new ArrayList<GioHang>();
        try {
            startOperation();
            String hql = "select obj from " + this.getClassName() + " obj where tinh_trang=:stt order by obj.id asc";
            Query query = _hibernateSession.createQuery(hql);
            query.setInteger("stt", 0);
            query.setFirstResult((pageNo - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            
            list = query.list();
        } catch (HibernateException e) {
        } finally {
            _hibernateSession.close();
        }
        return list;

    }
	
	public void deleteById(Integer id) {
		GioHang entity = findById(id);
        entity.setTinhTrang(new Integer(0));
        super.save(entity);
    }
	public Long countAll(GioHang cartQuery) {
		Long all = new Long(-1);
		Session _session = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity());
			searchByInstanceCriteria(crit, cartQuery);
			crit.setProjection(Projections.rowCount());
			all = (Long) crit.uniqueResult();

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}
		return all;
	}

	public List<GioHang> searchByInstance(GioHang cartQuery, Integer page, Integer recordsPerPage) {
		List<GioHang> _blList = new ArrayList<GioHang>();
		Session _session = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity());
			crit.addOrder(Order.asc("id"));
			searchByInstanceCriteria(crit, cartQuery);

			crit.setFirstResult((page - 1) * recordsPerPage);
			crit.setMaxResults(recordsPerPage);

			_blList = crit.list();

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}
		return _blList;
	}
	
	private void searchByInstanceCriteria(Criteria crit, GioHang entity) {
		if (entity.getTinhTrang() != -1) {
			crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
		}
		if (entity.getThanhVien().getTenDangNhap() != "") {
			crit.createAlias("thanhVien", "gh_tv");
			crit.add(Restrictions.ilike("gh_tv.tenDangNhap", "%"
					+ entity.getThanhVien().getTenDangNhap() + "%"));
		}
		if (entity.getNgayCapNhat() != null) {
			crit.add(Restrictions.eq("ngayCapNhat", entity.getNgayCapNhat()));
		}
		
	}

}
