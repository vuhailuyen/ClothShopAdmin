/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ElC
 */
public class PagingHelper {

    public static List<Integer> PagingCaculator(int currentPage, int recordsPerPage, long totalRecords) {
        List<Integer> pagingNumbers = new ArrayList<Integer>();
        int lastPage = TotalPagesCaculator(recordsPerPage, totalRecords);
        int pagesPerView = 15;

        int pagesBeforeCurrentOne = pagesPerView / 2;
        int pagesAfterCurrentOne = pagesPerView / 2;
        if (pagesPerView % 2 == 0) {
            pagesAfterCurrentOne -= 1;
        }

        int startIndex = 1;
        int lastIndex = 15;

        int minIndex = 1;
        int maxIndex = lastPage;

        if (lastPage <= pagesPerView) {
            lastIndex = lastPage;
        } else {
            startIndex = currentPage - pagesBeforeCurrentOne;
            lastIndex = currentPage + pagesAfterCurrentOne;
            if(startIndex < minIndex){
                startIndex = minIndex;
            }
            if (lastIndex >= maxIndex) {
                lastIndex = maxIndex;
                //startIndex = maxIndex - pagesPerView + 1;
            }
        }

        addNumbersToList(startIndex, lastIndex, pagingNumbers);

        return pagingNumbers;
    }

    private static void addNumbersToList(int startIndex, int lastIndex, List<Integer> pagingNumbers) {
        for (int i = startIndex; i <= lastIndex; i++) {
            pagingNumbers.add(new Integer(i));
        }
    }

    public static int TotalPagesCaculator(int recordsPerPage, long totalRecords) {
        int total = 0;
        if (totalRecords % recordsPerPage == 0) {
            total = (int) (totalRecords / recordsPerPage);
        } else {
            total = (int) (totalRecords / recordsPerPage + 1);
        }
        return total;
    }
}
