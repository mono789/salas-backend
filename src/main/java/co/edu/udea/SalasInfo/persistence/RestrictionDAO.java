package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.Restriction;

import java.util.List;


public interface RestrictionDAO{
    Restriction findById(Integer id);
    List<Restriction> findAll();
    Restriction save(Restriction restriction);
    Restriction findByDescription(String description);

    void deleteById(Integer id);
}
