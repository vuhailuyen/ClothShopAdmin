/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ElC
 */
public interface IDao<T, PK extends Serializable> {

    /**
     * Get the class Entity
     */
    Class<T> getClassEntity();
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Find by Id
     *
     * @param PK id return T
     */
    T findById(PK id);
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Find objects by pageNo and recordsPerPage
     *
     * @param int pageNo
     * @param int recordsPerPage return List<T>
     */
    List<T> findAll(int pageNo, int recordsPerPage);

    List<T> findAll();

    List<T> findByQuery(String query);
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(final T entity);
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    Number countAll(boolean stt);
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Max id
     *
     * @return the max Id
     */
    int getMaxId();
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Delete by Id
     *
     * @param id
     * @return the max Id
     */
    void deleteById(PK id);

    /**
     * insert new row
     *
     * @param entity
     * @return the max Id
     */
    Integer insert(T entity);
}
