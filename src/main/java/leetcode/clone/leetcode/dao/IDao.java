package leetcode.clone.leetcode.dao;

import java.util.List;

public interface IDao<T> {

    public T get(Long id);
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(Integer id);
}
