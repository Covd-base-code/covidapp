package mz.ac.covid.app.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.covid.app.boot.dao.CustomerDao;
import mz.ac.covid.app.boot.domain.Customer;
import mz.ac.covid.app.boot.repository.CustomerRepository;

@Service
@Transactional(readOnly = false)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public void registar(Customer customer) {
        dao.save(customer);

    }

    @Override
    public void editar(Customer customer) {

        dao.update(customer);
    }

    @Override
    public void apagar(Long id) {

        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer buscarPorId(Long id) {

        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> buscarTodos() {

        return dao.findAll();
    }

}
