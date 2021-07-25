package mz.ac.covid.app.boot.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "LISTA_VACINACAO")
public class ListaVacinacao extends AbstractEntity<Long> {

  @ManyToOne
  @JoinColumn(name = "instituicao_id_fk")
  private Instituicao instituicao;

  @ManyToOne
  @JoinColumn(name = "funcionario_id_fk")
  private Funcionario funcionario;

  @OneToMany(mappedBy = "departamento")
  private List<Funcionario> funcionarios;
  // @ManyToOne
  // @JoinColumn(name = "intituicao_sala_id_fk")
  // private InstituicaoSala instituicaoSala;

  @ManyToOne
  @JoinColumn(name = "sala_id_fk")
  private Sala sala;

  // @Transient
  // private Sala sala;

  public Instituicao getInstituicao() {
    return instituicao;
  }

  public void setInstituicao(Instituicao instituicao) {
    this.instituicao = instituicao;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  // public InstituicaoSala getInstituicaoSala() {
  // return instituicaoSala;
  // }

  // public void setInstituicaoSala(InstituicaoSala instituicaoSala) {
  // this.instituicaoSala = instituicaoSala;
  // }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  // public Sala getSala() {
  // return sala;
  // }

  // public void setSala(Sala sala) {
  // this.sala = sala;
  // }

}
