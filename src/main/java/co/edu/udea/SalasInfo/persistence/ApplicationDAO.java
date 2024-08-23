package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.Application;

import java.util.List;

public interface ApplicationDAO{
    Application findById(Integer id);
    List<Application> findAll();
}
