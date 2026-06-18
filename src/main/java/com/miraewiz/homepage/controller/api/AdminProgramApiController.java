package com.miraewiz.homepage.controller.api;

import com.miraewiz.homepage.mapper.ProgramMapper;
import com.miraewiz.homepage.model.Program;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/programs")
public class AdminProgramApiController {

    private final ProgramMapper programMapper;

    public AdminProgramApiController(ProgramMapper programMapper) {
        this.programMapper = programMapper;
    }

    @GetMapping
    public List<Program> getPrograms() {
        return programMapper.findAll();
    }

    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        programMapper.insert(program);
        return ResponseEntity.ok(program);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program program) {
        program.setId(id);
        programMapper.update(program);
        return ResponseEntity.ok(program);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programMapper.delete(id);
        return ResponseEntity.noContent().build();
    }
}
