package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.Restriction;

import java.util.List;


public interface RestrictionDAO{
    Restriction findById(Integer id);
    List<Restriction> findAll();
    Restriction save(Restriction restriction);
    Restriction findByDescription(String description);

    void deleteById(Integer id);
}
