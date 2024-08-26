package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.Implement;

import java.util.List;

public interface ImplementDAO {
    Implement findById(Integer id);
    List<Implement> findAll();
}
