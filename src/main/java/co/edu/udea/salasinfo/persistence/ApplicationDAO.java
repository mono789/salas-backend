package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.Application;

import java.util.List;

public interface ApplicationDAO{
    Application findById(Integer id);
    List<Application> findAll();
}
