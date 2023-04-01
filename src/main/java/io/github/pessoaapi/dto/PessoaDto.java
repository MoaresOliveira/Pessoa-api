package io.github.pessoaapi.dto;

import io.github.pessoaapi.entity.PessoaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PessoaDto {

    private Long id;

    private String nome;

    @Min(value = 1)
    private Integer idade;

    @CPF
    private String cpf;

    public PessoaDto(PessoaEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.idade = entity.getIdade();
        this.cpf = entity.getCpf();
    }

    public PessoaEntity toEntity() {
        return PessoaEntity.builder()
                .id(id)
                .nome(nome)
                .idade(idade)
                .cpf(cpf)
                .build();
    }

    public static List<PessoaEntity> toEntityList(List<PessoaDto> dtos) {
        return dtos.stream().map(PessoaDto::toEntity).collect(Collectors.toList());
    }

    public static List<PessoaDto> toDtoList(List<PessoaEntity> entities) {
        return entities.stream().map(PessoaDto::new).collect(Collectors.toList());
    }

}
