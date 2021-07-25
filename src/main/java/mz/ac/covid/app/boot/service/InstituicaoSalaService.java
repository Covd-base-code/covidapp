package mz.ac.covid.app.boot.service;

import java.util.List;

import mz.ac.covid.app.boot.domain.InstituicaoSala;

public interface InstituicaoSalaService {

    void registar(InstituicaoSala instituicaoSala);

    void editar(InstituicaoSala instituicaoSala);

    void apagar(Long id);

    InstituicaoSala pesquisarPorId(Long id);

    List<InstituicaoSala> pesquisarTodos();
}
