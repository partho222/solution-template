package com.tigerit.exam.tariq.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the basic structure
 * of a database table and holds data
 */

public class Table {
    private Map<String, Integer> columnName;
    private Integer noOfColumn;
    private Integer noOfRow;
    private Integer[][] data;

    public Table(Integer noOfColumn, Integer noOfRow) {
        this.noOfColumn = noOfColumn;
        this.noOfRow = noOfRow;
        this.data = new Integer[noOfRow][noOfColumn];
    }

    public void insertColumnName(String... names) {
        this.columnName = new HashMap<>();
        for(Integer i = 0; i < names.length; i++) {
            this.columnName.put(names[i], i);
        }
    }

    public void insertValue(Integer row, Integer... values) {
        for(Integer i = 0; i < values.length; i++) {
            this.data[row][i] = values[i];
        }
    }

    public Integer getColumnCount() {
        return this.noOfColumn;
    }

    public Integer getRowCount() {
        return this.noOfRow;
    }

    public Integer getColumnIndex(String columnName) {
        return this.columnName.get(columnName);
    }

    public Integer[][] getData() {
        return this.data;
    }

    public Map<String, Integer> getColumnName() {
        return this.columnName;
    }
}
