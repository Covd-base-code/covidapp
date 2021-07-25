package mz.ac.covid.app.boot.domain;

import java.util.List;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Departamentos")
public class Departamento extends AbstractEntity<Long> {

  @Column(name = "nome", nullable = false, unique = true, length = 60)
  private String nome;

  @OneToMany(mappedBy = "departamento")
  private List<Funcionario> funcionarios;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }

}
