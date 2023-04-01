package io.github.pessoaapi.service;

import io.github.pessoaapi.dto.PessoaDto;
import io.github.pessoaapi.entity.PessoaEntity;
import io.github.pessoaapi.exception.PessoaNaoEncontradaException;
import io.github.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaDto cadastrar(PessoaDto pessoaDto) {
        PessoaEntity pessoaSalva = pessoaRepository.save(pessoaDto.toEntity());
        return new PessoaDto(pessoaSalva);
    }

    public PessoaDto pesquisarPorID(Long id) {
        PessoaEntity entity = getPessoaEntity(id);

        return new PessoaDto(entity);
    }

    public List<PessoaDto> retornarTodos() {
        List<PessoaEntity> pessoas = pessoaRepository.findAll();

        return PessoaDto.toDtoList(pessoas);
    }

    public PessoaDto atualizar(Long id, PessoaDto pessoaDto) {
        PessoaEntity pessoaEntity = getPessoaEntity(id);
        pessoaEntity.setNome(pessoaDto.getNome());
        pessoaEntity.setIdade(pessoaDto.getIdade());
        pessoaEntity.setCpf(pessoaDto.getCpf());

        PessoaEntity pessoaAtualizada = pessoaRepository.save(pessoaEntity);
        return new PessoaDto(pessoaAtualizada);
    }

    public void excluir(Long id) {
        PessoaEntity pessoaEntity = getPessoaEntity(id);
        pessoaRepository.delete(pessoaEntity);
    }

    private PessoaEntity getPessoaEntity(Long id) {
        PessoaEntity entity = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException(id));
        return entity;
    }


}
