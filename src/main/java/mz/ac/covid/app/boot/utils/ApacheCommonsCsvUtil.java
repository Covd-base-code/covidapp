package mz.ac.covid.app.boot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import mz.ac.covid.app.boot.domain.Customer;

public class ApacheCommonsCsvUtil {

    private static String csvExtension = "csv";

    public static void customersToCsv(Writer writer, List<Customer> customers) throws IOException {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer,
                CSVFormat.DEFAULT.withHeader("id", "nome", "telefone", "empresa", "email", "dataVacinacao",
                        "horaVacinacao", "salaVacinacao", "telefoneGestor"));) {
            for (Customer customer : customers) {
                List<String> data = Arrays.asList(String.valueOf(customer.getId()), customer.getNome(),
                        customer.getTelefone(), customer.getEmpresa(), customer.getEmail(),
                        String.valueOf(customer.getDataVacinacao()), String.valueOf(customer.getSalaVacinacao()),
                        String.valueOf(customer.getHoraVacinacao()), String.valueOf(customer.getTelefoneGestor()));

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static List<Customer> parseCsvFile(InputStream is) {
        BufferedReader fileReader = null;
        CSVParser csvParser = null;

        List<Customer> customers = new ArrayList<Customer>();

        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Customer customer = new Customer(csvRecord.get("nome"), csvRecord.get("telefone"),
                        csvRecord.get("empresa"), csvRecord.get("email"), csvRecord.get("dataVacinacao"),
                        csvRecord.get("salaVacinacao"), csvRecord.get("horaVacinacao"), csvRecord.get("dataVacinacao"));

                customers.add(customer);
            }

        } catch (Exception e) {
            System.out.println("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (IOException e) {
                System.out.println("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }

        return customers;
    }

    public static boolean isCSVFile(MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[1];

        if (!extension.equals(csvExtension)) {
            return false;
        }

        return true;
    }
}
