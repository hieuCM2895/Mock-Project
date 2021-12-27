package fa.training.impsystem.service;

import fa.training.impsystem.dto.ResponseDTO;

import java.util.List;

public interface CommonParent<T, K> {

    /**
     * create a new entity in database
     *
     * @param entity
     * @param responseDTO
     * @return
     */
    public boolean create(T entity, ResponseDTO responseDTO);

    /**
     * read entity info by id
     *
     * @param id
     * @param responseDTO
     * @return
     */
    public T readById(K id, ResponseDTO responseDTO);

    /**
     * read all entity in database
     *
     * @param responseDTO
     * @return
     */
    public List<T> readAll(ResponseDTO responseDTO);

    /**
     * update entity info database
     *
     * @param entity
     * @param responseDTO
     * @return
     */
    public boolean update(T entity, ResponseDTO responseDTO);

    /**
     * delete entity info by id
     *
     * @param id
     * @param responseDTO
     * @return
     */
    public boolean deleteById(K id, ResponseDTO responseDTO);
}
