package mz.ac.covid.app.boot.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import mz.ac.covid.app.boot.domain.Customer;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "nome", "telefone", "empresa", "email", "dataVacinacao", "salaVacinacao",
            "horaVacinacao", "telefoneGestor", "notificar", "estadoVacinacao" };
    static String SHEET = "Customers";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream excelToCustomers(List<Customer> customers) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getNome());
                row.createCell(2).setCellValue(customer.getTelefone());
                row.createCell(3).setCellValue(customer.getEmpresa());
                row.createCell(4).setCellValue(customer.getDataVacinacao());
                row.createCell(5).setCellValue(customer.getSalaVacinacao());
                row.createCell(6).setCellValue(customer.getHoraVacinacao());
                row.createCell(7).setCellValue(customer.getTelefone());
                row.createCell(8).setCellValue(customer.getNotificar());
                row.createCell(9).setCellValue(customer.getEstadoVacinacao());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Customer> excelToCustomers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Customer> customers = new ArrayList<Customer>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Customer customer = new Customer();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            customer.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            customer.setNome(currentCell.getStringCellValue());
                            break;

                        case 2:
                            customer.setTelefone(currentCell.getStringCellValue());
                            break;

                        case 3:
                            customer.setEmpresa(currentCell.getStringCellValue());
                            break;

                        case 4:
                            customer.setEmail(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customer.setDataVacinacao(currentCell.getStringCellValue());
                            break;

                        case 6:
                            customer.setSalaVacinacao(currentCell.getStringCellValue());
                            break;

                        case 7:
                            customer.setHoraVacinacao(currentCell.getStringCellValue());
                            break;

                        case 8:
                            customer.setTelefoneGestor(currentCell.getStringCellValue());
                            break;

                        case 9:
                            customer.setNotificar(currentCell.getStringCellValue());
                            break;

                        case 10:
                            customer.setEstadoVacinacao(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                customers.add(customer);
            }

            workbook.close();

            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
