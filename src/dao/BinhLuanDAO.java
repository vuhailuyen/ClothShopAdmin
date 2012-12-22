/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * 
 * @author ElC
 */
public class BinhLuanDAO extends GenericDAO<BinhLuan, Integer> {

	@Override
	public void deleteById(Integer id) {
		BinhLuan entity = findById(id);
		entity.setTinhTrang(new Integer(0));
		super.save(entity);
	}

	@Override
	public Integer insert(BinhLuan entity) {
		Integer id = new Integer(getMaxId() + 1);
		entity.setId(id);
		super.insert(entity);
		return id;
	}

	public List<BinhLuan> findAll(int pageNo, int recordsPerPage) {
		List<BinhLuan> list = new ArrayList<BinhLuan>();
		try {
			startOperation();
			String hql = "select obj from " + this.getClassName()
					+ " obj where tinh_trang=:stt order by obj.ngayDang desc";
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

	public void validateComment(String task) {

	}

	public Long countAll(BinhLuan entity) {
		Long all = new Long(-1);
		Session _session = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity());
			searchByInstanceCriteria(crit, entity);
			crit.setProjection(Projections.rowCount());
			all = (Long) crit.uniqueResult();

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}
		return all;
	}

	@SuppressWarnings("unchecked")
	public List<BinhLuan> searchByInstance(BinhLuan entity, Integer pageNo,
			Integer recordsPerPage) {
		List<BinhLuan> _blList = new ArrayList<BinhLuan>();
		Session _session = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity());
			crit.addOrder(Order.asc("id"));
			searchByInstanceCriteria(crit, entity);

			crit.setFirstResult((pageNo - 1) * recordsPerPage);
			crit.setMaxResults(recordsPerPage);

			_blList = crit.list();

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}

		return _blList;
	}

	private void searchByInstanceCriteria(Criteria crit, BinhLuan entity) {

		if (entity.getSanPham().getTenSanPham() != "") {
			crit.createAlias("sanPham", "bl_sp");
			crit.add(Restrictions.ilike("bl_sp.tenSanPham", "%"
					+ entity.getSanPham().getTenSanPham() + "%"));
			System.out.println(entity.getSanPham().getTenSanPham());
		}
		if (entity.getTinhTrang() != -2) {
			crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
		}
		if (entity.getThanhVien().getTenDangNhap() != "") {
			crit.createAlias("thanhVien", "bl_tv");
			crit.add(Restrictions.ilike("bl_tv.tenDangNhap", "%"
					+ entity.getThanhVien().getTenDangNhap() + "%"));
		}
		if (entity.getNgayDang() != null) {
			crit.add(Restrictions.eq("ngayDang", entity.getNgayDang()));
		}
	}

	public Integer verify(List<Integer> idList, Integer value) {
		Session _session = null;
		System.out.println(idList.size());
		for(Integer i: idList){
			
			System.out.println(i.intValue());
		}
		Query _query = null;
		Integer result = new Integer(0);
		try {
			startOperation();
			_session = getSession();
			_query = _session.createQuery("update BinhLuan bl set bl.tinhTrang=:tinhTrang where bl.id in(:idList)");
			_query.setInteger("tinhTrang", value);
			
			_query.setParameterList("idList", idList);
			result = _query.executeUpdate();
			commit();
		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}
		 return result;
	}

}
