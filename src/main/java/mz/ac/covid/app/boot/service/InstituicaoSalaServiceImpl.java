package mz.ac.covid.app.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.covid.app.boot.dao.InstituicaoSalaDao;
import mz.ac.covid.app.boot.domain.InstituicaoSala;

@Service
@Transactional(readOnly = false)
public class InstituicaoSalaServiceImpl implements InstituicaoSalaService {

    @Autowired
    private InstituicaoSalaDao dao;

    @Override
    public void registar(InstituicaoSala instituicaoSala) {
        dao.save(instituicaoSala);

    }

    @Override
    public void editar(InstituicaoSala instituicaoSala) {
        dao.update(instituicaoSala);
    }

    @Override
    public void apagar(Long id) {
        dao.delete(id);
    }

    @Override
    public InstituicaoSala pesquisarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<InstituicaoSala> pesquisarTodos() {
        return dao.findAll();
    }

}
