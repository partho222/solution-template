package com.tigerit.exam;

import java.util.HashMap;
import java.util.Map;

import static com.tigerit.exam.IO.*;
import static com.tigerit.exam.Utils.*;
import static com.tigerit.exam.QueryParser.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    
    private Map<String, Table> tableMap;
    boolean isSameQuery;


    public void input() {
        Integer kase = readLineAsInteger();
        while (kase > 0) {
            Integer noOfTable = readLineAsInteger();
            tableMap = new HashMap<>();
            while (noOfTable > 0) {
                /* table creation operation */
                String tableName = readLine();
                Integer[] values = getIntFromLine(readLine());
                Table currentTable = new Table(values[0], values[1]);
                /* data insertion operation */
                String[] columnNames = getStringArrayFromLine(readLine());
                currentTable.insertColumnName(columnNames);
                for(int i = 0; i < currentTable.getRowCount(); i++) {
                    values = getIntFromLine(readLine());
                    currentTable.insertValue(i, values);
                }
                tableMap.put(tableName, currentTable);
                noOfTable--;
            }
            kase--;
        }
    }

    public void aliasDetection(String line) {
        String[] data = getStringArrayFromLine(line);
        if(data.length > 1) {
            this.tableMap.put(data[1].trim(), this.tableMap.get(data[0].trim()));
        }
    }

    public void processResult(String selection, Integer table1row, Integer table2row, Table table1, Table table2) {
        String columnNameOut, valueOut;
        if(selection.equals("*")) {
            Integer table1column = table1.getColumnCount();
            Integer table2column = table2.getColumnCount();
            Integer[][] table1Data = table1.getData();
            Integer[][] table2Data = table2.getData();

            columnNameOut = "";
            for(int i = 0; i < table1column; i++) {
                columnNameOut += getKeyByValue(table1.getColumnName(), i);
                columnNameOut += " ";
            }
            for(int i = 0; i < table2column; i++) {
                columnNameOut += getKeyByValue(table2.getColumnName(), i);
                columnNameOut += " ";
            }
            if(!isSameQuery)    printLine(columnNameOut.trim());

            valueOut = "";
            for(int i = 0; i < table1column; i++) {
                valueOut += table1Data[table1row][i].toString();
                valueOut += " ";
            }
            for(int i = 0; i < table2column; i++) {
                valueOut += table2Data[table2row][i].toString();
                valueOut += " ";
            }
            printLine(valueOut.trim());
        } else {
            String[] selectionParams = selection.split(",");
            String[][] selectedTableAndColumn = new String[selectionParams.length][];
            columnNameOut = "";
            valueOut = "";
            for(int i = 0; i < selectionParams.length; i++) {
                selectedTableAndColumn[i] = getTableAndColumnName(selectionParams[i]);
                columnNameOut += selectedTableAndColumn[i][1];
                columnNameOut += " ";
                Table currentTable = this.tableMap.get(selectedTableAndColumn[i][0]);
                Integer columnIndex = currentTable.getColumnIndex(selectedTableAndColumn[i][1]);
                if(currentTable.equals(table1)) {
                    valueOut += currentTable.getData()[table1row][columnIndex];
                    valueOut += " ";
                } else if(currentTable.equals(table2)) {
                    valueOut += currentTable.getData()[table2row][columnIndex];
                    valueOut += " ";
                }
            }
            if(!isSameQuery)    printLine(columnNameOut.trim());
            printLine(valueOut.trim());
        }
    }

    public void processQuery() {
        Integer noOfQuery = readLineAsInteger();
        while (noOfQuery > 0) {
            String selection = selectLineData(readLine()).trim();
            String fromTable = fromLineData(readLine()).trim();
            aliasDetection(fromTable);
            String joinTable = joinLineData(readLine()).trim();
            aliasDetection(joinTable);
            String onCondition = onLineData(readLine()).trim();

            String[] conditionParams = onCondition.split("=");
            String[] tableColumnName1 = getTableAndColumnName(conditionParams[0]);
            String[] tableColumnName2 = getTableAndColumnName(conditionParams[1]);
            
            Table table1 = this.tableMap.get(tableColumnName1[0]);
            Table table2 = this.tableMap.get(tableColumnName2[0]);

            Integer param1ColumnIndex = table1.getColumnIndex(tableColumnName1[1]);
            Integer param2ColumnIndex = table2.getColumnIndex(tableColumnName2[1]);

            Integer table1Rows = table1.getRowCount();
            Integer[][] table1Data = table1.getData();
            Integer table2Rows = table2.getRowCount();
            Integer[][] table2Data = table2.getData();

            Integer[] table1ColumnData = new Integer[table1Rows];
            for(int i = 0; i < table1Rows; i++) {
                table1ColumnData[i] = table1Data[i][param1ColumnIndex];
            }
            Integer[] table2ColumnData = new Integer[table2Rows];
            for(int i = 0; i < table2Rows; i++) {
                table2ColumnData[i] = table2Data[i][param2ColumnIndex];
            }

            this.isSameQuery = false;
            for(int i = 0; i < table1Rows; i++) {
                for(int j = 0; j < table2Rows; j++) {
                    if(table1ColumnData[i].equals(table2ColumnData[j])) {
                        processResult(selection, i, j, table1, table2);
                        this.isSameQuery = true;
                        break;
                    }
                }
            }
            noOfQuery--;
        }
    }


    public void solve() {
        input();
        processQuery();
    }

    @Override
    public void run() {
        solve();
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