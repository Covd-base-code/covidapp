package mz.ac.covid.app.boot.service;

import java.util.List;

import mz.ac.covid.app.boot.domain.ListaVacinacao;

public interface ListaVacinacaoService {

	void registar(ListaVacinacao listaVacinacao);

	void editar(ListaVacinacao listaVacinacao);

	void apagar(Long id);

	ListaVacinacao pesquisarPorId(Long id);

	List<ListaVacinacao> pesquisarTodos();

}
