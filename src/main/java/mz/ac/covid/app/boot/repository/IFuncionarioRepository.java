package mz.ac.covid.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mz.ac.covid.app.boot.domain.InstituicaoSala;

import java.util.List;

public interface IFuncionarioRepository extends JpaRepository<InstituicaoSala, Long> {

  // @Query(value = "SELECT it.nome AS instituicaoNome, s.nome salaNome, f.nome
  // funcionarioNome FROM instituicao_sala i INNER JOIN sala s ON (s.id =
  // i.sala_id_fk) INNER JOIN instituicao it ON (it.id = i.instituicao_id_fk)
  // INNER JOIN funcionarios f ON (f.instituicao_id_fk = it.id) WHERE it.id = ?",
  // nativeQuery = true)
  @Query(value = "Select inst FROM instituicao_sala inst JOIN FETCH inst.instituicao ins JOIN FETCH inst.sala sal where inst.id = ?1", nativeQuery = true)

  public List<InstituicaoSala> listaInstituicaoSalas(Long Id);

  /// Select ist From instituicao ist join fetch ist.sala sal join fetch
  /// ist.funcionario fun where ist.id =
}
