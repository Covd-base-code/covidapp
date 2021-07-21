package mz.ac.covid.app.boot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mz.ac.covid.app.boot.domain.Customer;
import mz.ac.covid.app.boot.helper.ExcelHelper;
import mz.ac.covid.app.boot.repository.CustomerRepository;

@Service
public class ExcelService {

    @Autowired
    CustomerRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            repository.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Customer> customers = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.excelToCustomers(customers);
        return in;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
