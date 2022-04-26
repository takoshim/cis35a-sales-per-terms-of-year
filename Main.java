import java.util.Scanner;

public class Main {

    // Defines static variables
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 4;

    public static void main(String[] args) {

        String[] userDivisions = new String[NUMBER_OF_DIVISION];
        getNamesInTime( userDivisions, NUMBER_OF_DIVISION );
        printNames( userDivisions );

        double[] dev1Sales = new double[NUMBER_OF_TERMS_PER_YEAR];
        getSalesInTerm( dev1Sales, NUMBER_OF_TERMS_PER_YEAR);

        double[] dev2Sales = new double[NUMBER_OF_TERMS_PER_YEAR];
        getSalesInTerm( dev2Sales, NUMBER_OF_TERMS_PER_YEAR);

        double[] dev1SalesDiffsBetweenTerms = calculateDiffsBetweenTerms(dev1Sales);
        double[] dev2SalesDiffsBetweenTerms = calculateDiffsBetweenTerms(dev2Sales);

        double companySalesPerTerm[] = toTotalSalesPerTerm( dev1Sales, dev2Sales );
        System.out.println("\n");
        for (int i=0; i< dev1Sales.length; i++) {
            System.out.println(i + "total: " + companySalesPerTerm[i]);
        }

        double[] companySalesDiffsBetweenTerms = calculateDiffsBetweenTerms(companySalesPerTerm);
        System.out.println("\n");
        for (int i=0; i< dev1Sales.length -1; i++) {
            System.out.println(i + "diff: " + companySalesDiffsBetweenTerms[i]);
        }

        double[] dev1SalesAveragePerTerm = calculateAveragePerTerm(dev1Sales, dev2Sales);
        System.out.println("\n");
        for (int i=0; i< dev1Sales.length; i++) {
            System.out.println(i + "average: " + dev1SalesAveragePerTerm[i]);
        }

//        printHeaderLine();
//        printContents(userNamesOfDivision, userQuarterlySalesPerDivision, userDiffsBetweenTerms);
    }

    /**
     * This void function takes two parameters.
     * And prompts user to enter names for times specified by the second arg.
     * @param array
     * @param time
     */
    public static void getNamesInTime(String[] array, int time) {

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < time; i++) {
            System.out.printf("Type name %d: ", i + 1);
            array[i] = in.nextLine();
        }
    }

    /**
     * This void function prints Strings passed by arg.
     * Each value in the array of String passed are printed with preceding number of line.
     * @param names
     */
    public static void printNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d: %20s\n", i +1, names[i]);
        }
    }

    /**
     * This void function takes two parameters.
     * And prompts user to enter sales for times of terms specified by the second arg.
     * @param sales
     * @param terms
     */
    public static void getSalesInTerm (double[] sales, int terms) {

        // Prompts user to enter sales for times specified by second arg
        Scanner in = new Scanner(System.in);

        for (int i=0; i< terms; i++) {
            System.out.printf("Sales for Term %d?\n", i +1);
            sales[i] = in.nextDouble();
        }
    }

    /**
     * This function return differences (changes) of sales between each terms.
     * Returned data consists of array of doulbe type size of the arg minus one.
     * Ex: if passed sales data are for total of 4 terms, returned array will consist of 3 elements.
     * Respectively represents: 1st data for diff between term2 and term1,
     * 2nd data for diff between term3 and term2, 3rd data for diff between term4 and term3.
     * @param sales
     * @return
     */
    public static double[] calculateDiffsBetweenTerms(double[] sales){

        double[] diffsAmongTerms = new double[sales.length -1];
        for (int i=0; i < sales.length -1; i++) {
            diffsAmongTerms[i] = sales[i +1] - sales[i];
        }
        return diffsAmongTerms;
    }

    /**
     * This function returns total sales for each term.
     * At this moment, implementation is done for 2 parameters version and 6 parameters.
     * @param sales1
     * @param sales2
     * @return
     */
    public static double[] toTotalSalesPerTerm(double[] sales1, double[] sales2) {
        // Checks if the sizes of the passed arrays are the same

        // Assume the sizes of all the passed arrays are the same
        double[] totalSalesPerTerm = new double[sales1.length];
        for (int i=0; i<sales1.length; i++) {
            totalSalesPerTerm[i] = sales1[i] + sales2[i];
        }
        return totalSalesPerTerm;
    }
    public static double[] toTotalSalesPerTerm(double[] sales1, double[] sales2,
                                                double[] sales3, double[] sales4,
                                               double[] sales5, double[] sales6 ) {
        // Checks if the sizes of the passed arrays are the same

        // Assume the sizes of all the passed arrays are the same
        double[] totalSalesPerTerm = new double[sales1.length];
        for (int i=0; i<sales1.length; i++) {
            totalSalesPerTerm[i] = sales1[i] + sales2[i] + sales3[i] + sales4[i] + sales5[i] +sales6[i] ;
        }
        return totalSalesPerTerm;
    }


    public static double[] calculateAveragePerTerm(double[] sales1, double[] sales2) {
        // Checks if the sizes of the passed arrays are the same

        // Assume the sizes of all the passed arrays are the same
        double[] averageSalesPerTerm = new double[sales1.length];
        for (int i=0; i<sales1.length; i++) {
            double total = 0;
            total += sales1[i] + sales2[i];
            averageSalesPerTerm[i] = total / 2;
        }
        return averageSalesPerTerm;


    }





    public static void printHeaderLine() {
        // Loop for printing header line
        // Print "Division" at 1st column
        System.out.printf("%20s%16s", "Division", "Term1");

        // For Term2 and over
        for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {

            // Construct colum header String
            String termNum = "Term";
            termNum = termNum.concat(Integer.toString(term + 1));

            System.out.printf("%16s", termNum);
            System.out.printf("%14s", "(+/-)");
        }
        System.out.printf("\n");
    }

    /**
     *
     * @param namesOfDivision
     * @param quarterlySalesPerDivision
     * @param diffsBetweenTerms
     */
    public static void printContents(   String[] namesOfDivision,
                                        double[][] quarterlySalesPerDivision,
                                        double[][] diffsBetweenTerms) {
        // Loop for printing the contents
        for (int div = 0; div < NUMBER_OF_DIVISION; div++) {

            // Prints division name
            System.out.printf("%20s", namesOfDivision[div]);

            // Prints the first term
            System.out.printf("%,16.2f", quarterlySalesPerDivision[div][0]);

            // Prints the second term and over
            for (int term = 1; term < NUMBER_OF_TERMS_PER_YEAR; term++) {
                System.out.printf("%,16.2f", quarterlySalesPerDivision[div][term]);
                System.out.printf("(%+,14.2f)", diffsBetweenTerms[div][term - 1]);
            }
            System.out.printf("\n");
        }
    }
}
