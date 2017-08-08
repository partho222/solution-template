package com.tigerit.exam;

import static com.tigerit.exam.IO.*;
import static com.tigerit.exam.Utils.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {

    private Table[] tables;

    public void input() {
        Integer kase = readLineAsInteger();
        while (kase > 0) {
            Integer noOfTable = readLineAsInteger();
            this.tables = new Table[noOfTable];
            Integer itr = 0;
            while (noOfTable > 0) {
                String tableName = readLine();
                Integer[] values = getIntFromLine(readLine());
                Table currentTable = new Table(tableName, values[0], values[1]);
                /* data insertion operation */
                String[] columnNames = getStringArrayFromLine(readLine());
                currentTable.insertColumnName(columnNames);
                for(int i = 0; i < currentTable.getRowCount(); i++) {
                    values = getIntFromLine(readLine());
                    currentTable.insertValue(i, values);
                }
                this.tables[itr++] = currentTable;
                noOfTable--;
            }
            kase--;
        }
    }

    public void solve() {
        input();
    }

    @Override
    public void run() {
        solve();
        printLine("ok");
//        // your application entry point
//
//        // sample input process
//        String string = readLine();
//
//        Integer integer = readLineAsInteger();
//
//        // sample output process
//        printLine(string);
//        printLine(integer);
    }
}