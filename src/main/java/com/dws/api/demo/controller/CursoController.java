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
public class CursoController {
    @Autowired
    CursoService cursoService;
    @Autowired
    AlumnoService alumnoService;

    @GetMapping()
    public ModelAndView index()
    {
        return new ModelAndView("index.html");
    }
    @GetMapping("/curso")
    public List<CursoModel> getCursos()
    {
        return cursoService.getAllCursos();
    }
    @GetMapping("/curso/{id}/alumno")
    public List<AlumnoModel> obtenerAlumnosPorCurso(@PathVariable Long id) {
        return alumnoService.cursoByAlumno(id);
    }
    @GetMapping("/curso/{id}/alumno/{id2}")
    public AlumnoModel datosDeAlumno(@PathVariable Long id,@PathVariable Long id2) {
        return alumnoService.datosAlumnoEnCurso(id,id2);
    }
    @GetMapping("/curso/{id}")
    public ResponseEntity<CursoModel> buscarPorId(@PathVariable Long id) {
        Optional<CursoModel> alumnoOptional = cursoService.findCurso(id);
        return alumnoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/curso")
    public CursoModel saveCurso(@RequestBody CursoModel curso)
    {
        return cursoService.saveCurso(curso);
    }
    @PutMapping("/curso/{id}")
    public boolean updateCurso(@PathVariable Long id, @RequestBody CursoModel curso)
    {
        return cursoService.updateCurso(id,curso);
    }

    @DeleteMapping("/curso/{id}")
    public boolean deleteCurso(@PathVariable Long id)
    {
        return cursoService.deleteCurso(id);
    }
    @PostMapping("/curso/id/alumno")
    public AlumnoModel saveAlumno(@RequestBody AlumnoModel alumno)
    {
        return alumnoService.saveAlumno(alumno);
    }
    @PutMapping("/clase/id/alumno/{id}")
    public boolean updateAlumno(@PathVariable Long id, @RequestBody AlumnoModel alumno)
    {
        return alumnoService.updateAlumno(id,alumno);
    }
    @DeleteMapping("/clase/id/alumno/{id}")
    public boolean deleteAlumno(@PathVariable Long id)
    {
        return alumnoService.deleteAlumno(id);
    }
}
