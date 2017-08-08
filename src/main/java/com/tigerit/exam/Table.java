package com.tigerit.exam;

public class Table {
    private String name;
    private String alias;
    private String[] columnName;
    private Integer noOfColumn;
    private Integer noOfRow;
    private Integer[][] data;

    public Table(String name, Integer noOfColumn, Integer noOfRow) {
        this.name = name;
        this.noOfColumn = noOfColumn;
        this.noOfRow = noOfRow;
        this.columnName = new String[noOfColumn];
        this.data = new Integer[noOfColumn][noOfRow];
    }

    public void insertColumnName(String... names) {
        for(Integer i = 0; i < names.length; i++) {
            this.columnName[i] = names[i];
        }
    }

    public void insertValue(Integer row, Integer... values) {
        for(Integer i = 0; i < values.length; i++) {
            this.data[row][i] = values[i];
        }
    }
}
