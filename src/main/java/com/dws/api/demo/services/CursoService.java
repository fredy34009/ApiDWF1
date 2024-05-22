package com.dws.api.demo.services;

import com.dws.api.demo.models.CursoModel;
import com.dws.api.demo.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    //Contiene todos los metodos crud de un curso
    @Autowired
    CursoRepository cursoRepository;

    public List<CursoModel> getAllCursos(){
        return (List<CursoModel>) cursoRepository.findAll();
    }
    public CursoModel saveCurso(CursoModel curso)
    {
        return cursoRepository.save(curso);
    }
    public boolean updateCurso(Long id, CursoModel nuevoCurso) {
        Optional<CursoModel> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            CursoModel curso = cursoOptional.get();
            curso.setNombreCurso(nuevoCurso.getNombreCurso());
            curso.setEmail(nuevoCurso.getEmail());
            curso.setNombreProfesor(nuevoCurso.getNombreProfesor());
            curso.setNumeroTelefono(nuevoCurso.getNumeroTelefono());
            cursoRepository.save(curso);
            return true;
        } else {
            return false;
        }

    }
    public boolean deleteCurso(Long id)
    {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Optional<CursoModel> findCurso(Long id)
    {
        return cursoRepository.findById(id);
    }
}
