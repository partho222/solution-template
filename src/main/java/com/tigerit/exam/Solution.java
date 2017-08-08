package com.tigerit.exam;

import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
/***************************************************************************************/
    /*
    * Created inner class 'Table' as
    * I have to implement application
    * logic inside solution class
    * */

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
/***************************************************************************************/
    public void input() {
        Integer kase = readLineAsInteger();
        while (kase > 0) {
            Integer noOfTable = readLineAsInteger();
        }
    }

    public void solve() {

    }

    @Override
    public void run() {
        // your application entry point

        // sample input process
        String string = readLine();

        Integer integer = readLineAsInteger();

        // sample output process
        printLine(string);
        printLine(integer);
    }
}