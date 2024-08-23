package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.Implement;

import java.util.List;

public interface ImplementDAO {
    Implement findById(Integer id);
    List<Implement> findAll();
}
