package mz.ac.covid.app.boot.service;

import java.util.List;

import mz.ac.covid.app.boot.domain.Customer;

public interface CustomerService {

    void registar(Customer customer);

    void editar(Customer customer);

    void apagar(Long id);

    Customer buscarPorId(Long id);

    List<Customer> buscarTodos();
}
