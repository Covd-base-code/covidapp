package mz.ac.covid.app.boot.dao;

import java.util.List;

import mz.ac.covid.app.boot.domain.InstituicaoSala;

public interface InstituicaoSalaDao {

    void save(InstituicaoSala instituicaoSala);

    void update(InstituicaoSala instituicaoSala);

    void delete(Long id);

    InstituicaoSala findById(Long id);

    List<InstituicaoSala> findAll();
}
