package com.dws.api.demo.controller;

import com.dws.api.demo.models.AlumnoModel;
import com.dws.api.demo.models.CursoModel;
import com.dws.api.demo.services.AlumnoService;
import com.dws.api.demo.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
//controlador de la api en el path /
public class CursoController {
    @Autowired
    CursoService cursoService;
    @Autowired
    AlumnoService alumnoService;

    //retorna la vista del diccionario de servicios
    @GetMapping()
    public ModelAndView index()
    {
        return new ModelAndView("index.html");
    }
    //retorna los cursos
    @GetMapping("/curso")
    public List<CursoModel> getCursos()
    {
        return cursoService.getAllCursos();
    }
    //retorna que alumnos estan inscritos en el curso pasando el {id} del curso
    @GetMapping("/curso/{id}/alumno")
    public List<AlumnoModel> obtenerAlumnosPorCurso(@PathVariable Long id) {
        return alumnoService.cursoByAlumno(id);
    }
    //muestra detalles de un alumno en especifico de un curso
    @GetMapping("/curso/{id}/alumno/{id2}")
    public AlumnoModel datosDeAlumno(@PathVariable Long id,@PathVariable Long id2) {
        return alumnoService.datosAlumnoEnCurso(id,id2);
    }

    //busca un curso pasando el id
    @GetMapping("/curso/{id}")
    public ResponseEntity<CursoModel> buscarPorId(@PathVariable Long id) {
        Optional<CursoModel> alumnoOptional = cursoService.findCurso(id);
        return alumnoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //crea un nuevo curso en la base de datos
    @PostMapping("/curso")
    public CursoModel saveCurso(@RequestBody CursoModel curso)
    {
        return cursoService.saveCurso(curso);
    }
    //Actualiza un registro de tipo curso en la base de datos
    @PutMapping("/curso/{id}")
    public boolean updateCurso(@PathVariable Long id, @RequestBody CursoModel curso)
    {
        return cursoService.updateCurso(id,curso);
    }
    // elimina un curso de la base de datos
    @DeleteMapping("/curso/{id}")
    public boolean deleteCurso(@PathVariable Long id)
    {
        return cursoService.deleteCurso(id);
    }
    //registra un nuevo alumno en l a base de datos
    @PostMapping("/curso/id/alumno")
    public AlumnoModel saveAlumno(@RequestBody AlumnoModel alumno)
    {
        return alumnoService.saveAlumno(alumno);
    }
    //actualiza un registro de un alumno pasando el id del registro
    @PutMapping("/clase/id/alumno/{id}")
    public boolean updateAlumno(@PathVariable Long id, @RequestBody AlumnoModel alumno)
    {
        return alumnoService.updateAlumno(id,alumno);
    }
    //elimina un alumno pasando el id del registro
    @DeleteMapping("/clase/id/alumno/{id}")
    public boolean deleteAlumno(@PathVariable Long id)
    {
        return alumnoService.deleteAlumno(id);
    }
}
