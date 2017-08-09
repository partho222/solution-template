package com.tigerit.exam.tariq.solution;

import java.util.Comparator;

import static com.tigerit.exam.tariq.solution.Utils.*;

public class LexicographicComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        Integer[] listA = getIntFromLine(a);
        Integer[] listB = getIntFromLine(b);

        for(int i = 0; i < listA.length; i++ ) {
            if( listA[i] < listB[i] )   return -1;
            else if( listA[i].equals(listB[i]) ) continue;
            else if( listA[i] > listB[i] ) return 1;
        }
        return 0;
    }
}
