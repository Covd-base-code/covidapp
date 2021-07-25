package mz.ac.covid.app.boot.dao;

import java.util.List;

import mz.ac.covid.app.boot.domain.Funcionario;
import mz.ac.covid.app.boot.domain.ListaVacinacao;

public interface ListaVacinacaoDao {

  void save(ListaVacinacao listaVacinacao);

  void update(ListaVacinacao listaVacinacao);

  void delete(Long id);

  ListaVacinacao findById(Long id);

  List<ListaVacinacao> findAll();
}
