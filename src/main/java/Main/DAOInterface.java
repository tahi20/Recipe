package Main;

import java.util.List;

public interface DAOInterface<T> {
    public int insert(T entity);

    public T read(int id);

    public void update(T entity);

    public void delete(T entity);

    public List<T> findAll();


}

