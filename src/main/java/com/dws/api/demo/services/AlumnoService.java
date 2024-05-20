package com.dws.api.demo.services;

import com.dws.api.demo.models.AlumnoModel;
import com.dws.api.demo.models.CursoModel;
import com.dws.api.demo.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    AlumnoRepository alumnoRepository;

    public List<AlumnoModel> getAllAlumnos(){
        return (List<AlumnoModel>) alumnoRepository.findAll();
    }

    public AlumnoModel saveAlumno(AlumnoModel alumno)
    {
        return alumnoRepository.save(alumno);
    }
    public boolean updateAlumno(Long id, AlumnoModel alumnoModel) {
        Optional<AlumnoModel> alumnoModelOptional = alumnoRepository.findById(id);
        if (alumnoModelOptional.isPresent()) {
            AlumnoModel alumno = alumnoModelOptional.get();
            alumno.setNombreAlumno(alumnoModel.getNombreAlumno());
            alumno.setEmail(alumnoModel.getEmail());
            alumno.setFechaNacimiento(alumnoModel.getFechaNacimiento());
            alumno.setNumeroTelefono(alumnoModel.getNumeroTelefono());
            CursoModel c=new CursoModel();
            c.setId(alumnoModel.getCurso().getId());
            alumno.setCurso(c);
            alumnoRepository.save(alumno);
            return true;
        } else {
            return false;
        }

    }
    public boolean deleteAlumno(Long id)
    {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Optional<AlumnoModel> findAlumno(Long id)
    {
        return alumnoRepository.findById(id);
    }
    public List<AlumnoModel> cursoByAlumno(Long id)
    {
        return alumnoRepository.cursoByAlumno(id);
    }
    public AlumnoModel datosAlumnoEnCurso(Long idCurso,Long idAlumno)
    {
        return alumnoRepository.datosAlumnoEnCurso(idCurso,idAlumno);
    }
}
