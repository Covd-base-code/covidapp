package mz.ac.covid.app.boot.dao;

import java.util.List;

import mz.ac.covid.app.boot.domain.Customer;

public interface CustomerDao {

    void save(Customer customer);

    void update(Customer customer);

    void delete(Long id);

    Customer findById(Long id);

    List<Customer> findAll();
}
