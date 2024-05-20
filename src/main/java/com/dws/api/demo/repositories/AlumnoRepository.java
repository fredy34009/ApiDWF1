package com.dws.api.demo.repositories;

import com.dws.api.demo.models.AlumnoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoModel,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM cursos.alumno where curso=?1")
    List<AlumnoModel> cursoByAlumno(Long id);
    @Query(nativeQuery = true, value = "SELECT * FROM alumno where curso=? and alumno.id=?")
    AlumnoModel datosAlumnoEnCurso(Long idCurso,Long idAlumno);
}
