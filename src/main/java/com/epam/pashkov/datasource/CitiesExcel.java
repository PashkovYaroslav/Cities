package com.epam.pashkov.datasource;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by Yaroslav on 14.05.2015.
 */
public class CitiesExcel implements CitiesDAO {
    private List<String> cities;

    public CitiesExcel() {
        getAllCities();
    }

    public List<String> getAllCities() {
        cities = new ArrayList<String>();

        try {
            FileInputStream file = new FileInputStream(new File("cities.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cities.add(cell.getStringCellValue());
                    }
                }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public void writeCitiesListToFile(List<String> cities){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i<cities.size(); i++) {
            Row row = sheet.createRow(i);
            String city = cities.get(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(city);
            }

        try {
            FileOutputStream out = new FileOutputStream(new File("cities.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean getCity(String title) {
        if(cities.contains(title)){
            return true;
        }
        else{
            return false;
        }
    }
}
