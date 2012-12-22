/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojos.SanPham;

/**
 * 
 * @author ElC
 */
public class SanPhamDAO extends GenericDAO<SanPham, Integer> {
	@Override
	public void deleteById(Integer id) {
		SanPham entity = this.findById(id);
		entity.setTinhTrang(new Integer(0));
		super.save(entity);
	}

	@Override
	public Integer insert(SanPham entity) {
		Integer id = new Integer(getMaxId() + 1);
		entity.setId(id);
		super.save(entity);
		return id;
	}

	public Map getTopTenCategoriesInStock() {
		Session _session = null;
		Map<String, String> result = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity(), "sp");
			crit.createAlias("loaiSanPham", "lsp");
			ProjectionList projectionList = Projections.projectionList();

			projectionList.add(Projections.groupProperty("lsp.id"));
			projectionList.add(Projections.groupProperty("lsp.tenLoai"));
			projectionList.add(Projections.alias(
					Projections.sum("sp.soLuongTon"), "ooo.soluong"));

			// crit.add(Restrictions.eq("sp.id", 26));
			crit.addOrder(Order.desc("ooo.soluong"));
			crit.setProjection(projectionList);
			crit.setMaxResults(10);
			List<Object> results = crit.list();

			result = new LinkedHashMap<String, String>();
			for (Object object : results) {
				Object[] obj = (Object[]) object;
				result.put((String) obj[1], obj[2].toString());

			}
			System.out.println(result.toString());

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<SanPham> searchByInstance(SanPham entity, int pageNo,
			int recordsPerPage) {
		List<SanPham> _spList = new ArrayList<SanPham>();
		Session _session = null;
		try {
			startOperation();
			_session = getSession();
			Criteria crit = _session.createCriteria(getClassEntity());
			crit.addOrder(Order.asc("id"));
			searchByInstanceCriteria(crit, entity);

			crit.setFirstResult((pageNo - 1) * recordsPerPage);
			crit.setMaxResults(recordsPerPage);

			_spList = crit.list();

		} catch (HibernateException ex) {

		} finally {
			closeSession();
		}

		return _spList;

	}

	public Number countAll(SanPham entity) {
		Number all = new Integer(-1);
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

	private void searchByInstanceCriteria(Criteria crit, SanPham entity) {
		if (entity.getId() != -1) {
			crit.add(Restrictions.eq("id", entity.getId()));
		}
		if (entity.getNhaSanXuat().getId() != -1) {
			crit.add(Restrictions.eq("nhaSanXuat", entity.getNhaSanXuat()));
		}
		if (entity.getDoiTuong().getId() != -1) {
			crit.add(Restrictions.eq("doiTuong", entity.getDoiTuong()));
		}
		if (entity.getLoaiSanPham().getId() != -1) {
			crit.add(Restrictions.eq("loaiSanPham", entity.getLoaiSanPham()));
		}
		if (entity.getTenSanPham() != "") {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("tenSanPham",
					"%" + entity.getTenSanPham() + "%"));
			or.add(Restrictions.ilike("tenSanPham", entity.getTenSanPham()
					+ "%"));
			or.add(Restrictions.ilike("tenSanPham",
					"%" + entity.getTenSanPham()));
			or.add(Restrictions.ilike("tenSanPham", entity.getTenSanPham()));
			crit.add(or);
		}
		if (entity.getTinhTrang() != -1) {
			crit.add(Restrictions.eq("tinhTrang", entity.getTinhTrang()));
		}
	}

}
