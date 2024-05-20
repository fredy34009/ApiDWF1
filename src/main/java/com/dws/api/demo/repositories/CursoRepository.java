package com.dws.api.demo.repositories;

import com.dws.api.demo.models.CursoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<CursoModel,Long> {

}