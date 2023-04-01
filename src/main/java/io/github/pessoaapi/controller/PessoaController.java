package io.github.pessoaapi.controller;

import io.github.pessoaapi.dto.PessoaDto;
import io.github.pessoaapi.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> pesquisarPorId(@PathVariable Long id) {
        PessoaDto pessoaDto = pessoaService.pesquisarPorID(id);
        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping()
    public ResponseEntity<?> pesquisarTodos() {
        List<PessoaDto> pessoaDtos = pessoaService.retornarTodos();
        if(pessoaDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pessoaDtos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PessoaDto pessoaDto) {
        PessoaDto pessoaCadastrada = pessoaService.cadastrar(pessoaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
        PessoaDto pessoaAtualizada = pessoaService.atualizar(id, pessoaDto);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
