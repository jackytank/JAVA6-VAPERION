package com.edu.java6assm.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.edu.java6assm.entity.Order;
import com.edu.java6assm.entity.Product;
import com.edu.java6assm.entity.User;

public class CsvExporter<T> {

    private List<T> list;
    private String filename;

    public CsvExporter(List<T> list, String filename) {
        this.list = list;
        this.filename = filename;
    }

    public void export(Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (T entity : list) {
                if (entity instanceof Product) {
                    Product product = (Product) entity;
                    csvPrinter.printRecord(product.getId(), product.getName(), product.getPrice(), product.getImage(),
                            product.getAvailable(), product.getCreate_date().toString(),
                            product.getCategory().getName());
                }
                if (entity instanceof User) {
                    User user = (User) entity;
                    csvPrinter.printRecord(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                            user.getPhone(), user.getImage_url(), user.getEnabled(), user.getProvider().toString(),
                            "");
                }
                if (entity instanceof Order) {
                    Order order = (Order) entity;
                    csvPrinter.printRecord(order.getId(), order.getAddress(), order.getCreate_date().toString(),
                            order.getUser().getUsername());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
