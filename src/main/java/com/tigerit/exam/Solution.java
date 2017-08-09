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

    private Table[] tables;
    private Map<String, Table> tableMap;


    public void input() {
        Integer kase = readLineAsInteger();
        while (kase > 0) {
            Integer noOfTable = readLineAsInteger();
            this.tables = new Table[noOfTable];
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
        if(selection.equals("*")) {
            Integer table1column = table1.getColumnCount();
            Integer table2column = table2.getColumnCount();
            Integer[][] table1Data = table1.getData();
            Integer[][] table2Data = table2.getData();
            String out = "";
            for(int i = 0; i < table1column; i++) {
                out += getKeyByValue(table1.getColumnName(), i);
                out += " ";
            }
            for(int i = 0; i < table2column; i++) {
                out += getKeyByValue(table2.getColumnName(), i);
                out += " ";
            }
            printLine(out.trim());
            out = "";
            for(int i = 0; i < table1column; i++) {
                out += table1Data[table1row][i].toString();
                out += " ";
            }
            for(int i = 0; i < table2column; i++) {
                out += table2Data[table2row][i].toString();
                out += " ";
            }
            printLine(out.trim());
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
            String[] param1 = conditionParams[0].trim().split("\\.");
            String[] param2 = conditionParams[1].trim().split("\\.");

            String param1Table = param1[0].trim();
            String param1Column = param1[1].trim();
            String param2Table = param2[0].trim();
            String param2Column = param2[1].trim();

            Integer param1ColumnIndex = tableMap.get(param1Table).getColumnIndex(param1Column);
            Integer param2ColumnIndex = tableMap.get(param2Table).getColumnIndex(param2Column);

            Integer table1Rows = tableMap.get(param1Table).getRowCount();
            Integer[][] table1Data = tableMap.get(param1Table).getData();
            Integer table2Rows = tableMap.get(param2Table).getRowCount();
            Integer[][] table2Data = tableMap.get(param2Table).getData();

            Integer[] table1ColumnData = new Integer[table1Rows];
            for(int i = 0; i < table1Rows; i++) {
                table1ColumnData[i] = table1Data[i][param1ColumnIndex];
            }
            Integer[] table2ColumnData = new Integer[table2Rows];
            for(int i = 0; i < table2Rows; i++) {
                table2ColumnData[i] = table2Data[i][param2ColumnIndex];
            }

            for(int i = 0; i < table1Rows; i++) {
                for(int j = 0; j < table2Rows; j++) {
                    if(table1ColumnData[i].equals(table2ColumnData[j])) {
                        processResult(selection, i, j, tableMap.get(param1Table), tableMap.get(param2Table));
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