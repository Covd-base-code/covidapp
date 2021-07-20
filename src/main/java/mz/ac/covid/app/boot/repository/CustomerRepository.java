package mz.ac.covid.app.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mz.ac.covid.app.boot.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
