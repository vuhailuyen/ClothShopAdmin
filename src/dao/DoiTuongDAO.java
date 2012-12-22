/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import pojos.DoiTuong;

/**
 *
 * @author ElC
 */
public class DoiTuongDAO extends GenericDAO<DoiTuong, Integer>{

    @Override
    public void deleteById(Integer id) {
        DoiTuong entity = findById(id);
        entity.setTinhTrang(new Integer(0));
        super.deleteById(id);
    }

    @Override
    public Integer insert(DoiTuong entity) {
    	Integer id =  new Integer(getMaxId() + 1);
        entity.setId(id);
        super.save(entity);
        return id;
    }    
}
