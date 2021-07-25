package mz.ac.covid.app.boot.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ac.covid.app.boot.dao.ListaVacinacaoDao;
import mz.ac.covid.app.boot.domain.ListaVacinacao;

@Service
@Transactional(readOnly = false)
public class ListaVacinacaoServiceImpl implements ListaVacinacaoService {

  @Autowired(required = false)
  private ListaVacinacaoDao dao;

  @Override
  public void registar(ListaVacinacao listaVacinacao) {
    dao.save(listaVacinacao);

  }

  @Override
  public void editar(ListaVacinacao listaVacinacao) {
    dao.update(listaVacinacao);

  }

  @Override
  public void apagar(Long id) {
    dao.delete(id);

  }

  @Override
  public ListaVacinacao pesquisarPorId(Long id) {

    return dao.findById(id);
  }

  @Override
  public List<ListaVacinacao> pesquisarTodos() {
    return dao.findAll();
  }

}
