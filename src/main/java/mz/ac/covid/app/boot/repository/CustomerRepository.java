package mz.ac.covid.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mz.ac.covid.app.boot.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(value = "SELECT * FROM customer GROUP BY empresa", nativeQuery = true)
  public List<Customer> getAllInstitutions();

  @Query(value = "SELECT * FROM customer WHERE empresa=:empresaName GROUP BY sala_vacinacao", nativeQuery = true)
  public List<Customer> getAllSalas(@Param("empresaName") String empresaName);

  @Query(value = "SELECT * FROM customer WHERE empresa=:empresaName AND sala_vacinacao=:SalaNumber", nativeQuery = true)
  public List<Customer> getAllCustomers(@Param("empresaName") String empresaName,
      @Param("SalaNumber") String SalaNumber);

  @Query(value = "SELECT * FROM customer WHERE id=:Id", nativeQuery = true)
  public Customer getCustomerById(@Param("Id") Long Id);

  @Query(value = "SELECT * FROM customer WHERE estado_vacinacao=:estado AND empresa=:instituicao AND sala_vacinacao=:sala", nativeQuery = true)
  public List<Customer> search(@Param("estado") Integer estado, @Param("instituicao") String instituicao,
      @Param("sala") String sala);

}
