package database;

import java.util.List;

public interface CRUD {
    public Object create(Object object);
    public boolean delete(Object object);
    public boolean update(Object object);
    public List<Object> findAll();
    public Object findById(int id);

    List<Object> findBookByAuthor(int id);

}
