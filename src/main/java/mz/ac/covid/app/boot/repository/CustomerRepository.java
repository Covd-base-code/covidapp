package mz.ac.covid.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mz.ac.covid.app.boot.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value="SELECT empresa FROM customer GROUP BY empresa", nativeQuery = true)
    public List<String> getAllInstitutions();

    @Query(value="SELECT sala_vacinacao FROM customer GROUP BY sala_vacinacao", nativeQuery = true)
    public List<String> getAllSalas();
}
