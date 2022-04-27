import java.util.Scanner;

public class Main {

    // Defines constants
    private static final int NUMBER_OF_DIVISION = 2;
    private static final int NUMBER_OF_TERMS_PER_YEAR = 2;

    public static void main(String[] args) {

        // Collects divisions' names
        String[] namesOfDivisions = new String[NUMBER_OF_DIVISION];
        getNames(namesOfDivisions, NUMBER_OF_DIVISION);

        // Constructs sales data
        double[][] salesData = constructSalesData(namesOfDivisions, NUMBER_OF_TERMS_PER_YEAR);

        // Calculates the differences between terms for each division
        double[][] diffsPerDivision = new double[NUMBER_OF_DIVISION][NUMBER_OF_TERMS_PER_YEAR - 1];
        for (int div = 0; div < NUMBER_OF_DIVISION; div++) {
            diffsPerDivision[div] = calculateDiffsBetweenTerms(salesData[div]);
        }

        // Calculates the differences between terms for company
        double[] totals = calculateTotalsPerTerm(salesData);
        double[] diffsForCompany = calculateDiffsBetweenTerms(totals);

        // Calculates the averages of each term
        double[] averagesPerTerm = calculateAveragesOfTerm(salesData);

        // Get the indices of divisions with the highest sales
        int[] highestSalesPerTerm = getHighestSales(salesData);

        // Prints in form of table
        printHeaderLine(salesData);
        printContents(namesOfDivisions, salesData, diffsPerDivision);
        printTotalRow (totals, diffsForCompany);
        printAverageRow (averagesPerTerm);
        printHighestDivisionRow (namesOfDivisions, highestSalesPerTerm);
    }


    /**
     * This void function takes two parameters.
     * And prompts user to enter names for times specified by the second arg.
     * @param names
     * @param numberOfTimes
     */
    public static void getNames(String[] names, int numberOfTimes) {

        // Prompts user to enter names of each division of company
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < numberOfTimes; i++) {
            do {
                System.out.printf("Type name %d: ", i + 1);
                names[i] = in.nextLine();
                if (names[i].length() >15) {
                    System.out.println("Too long.");
                }
            } while (names[i].length() >15);
        }
    }

    /**
     * This function construct sales data for provided number of divisions and terms.
     * By prompting user to input sales data for specified number of term,
     * this function populate sales data for one division.
     * @param divisions
     * @param terms
     * @return
     */
    public static double[][] constructSalesData(String[] divisions, int terms) {
        Scanner in = new Scanner(System.in);

        // Define returned variable
        double[][] salesData = new double[divisions.length][terms];

        // Outer loop for each division
        for (int div=0; div<divisions.length; div++) {

            // Inner loop for each term
            for (int term=0; term<terms; term++) {
                System.out.printf("Division %d sales for Term %d? ", div, term);
                salesData[div][term] = in.nextDouble();
            }
        }
        return salesData;
    }

    /**
     * This function return differences (changes) of sales between each term.
     * Returned data consists of array of double type size of the arg minus one.
     * Ex: if passed sales data are for total of 4 terms, returned array will consist of 3 elements.
     * Respectively represents: 1st data for diff between term2 and term1,
     * 2nd data for diff between term3 and term2, 3rd data for diff between term4 and term3.
     * @param sales
     * @return diffsAmongTerms
     */
    public static double[] calculateDiffsBetweenTerms(double[] sales){

        double[] diffsAmongTerms = new double[sales.length -1];
        for (int i=0; i < sales.length -1; i++) {
            diffsAmongTerms[i] = sales[i +1] - sales[i];
        }
        return diffsAmongTerms;
    }

    /**
     * This function returns total sum of sales per terms.
     * This function goes through sales of all the divisions.
     * @param salesData
     * @return totalsPerTerm
     */
    public static double[] calculateTotalsPerTerm(double[][] salesData) {

        // Determines the numbers of divisions and terms
        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[0].length;

        // Define returned variable
        double[] totalsPerTerm = new double[numberOfTerms];

        // Outer loop for each term
        for (int term=0; term < numberOfTerms; term++) {

            // Loop through divisions to sum up
            double totalOfTerm = 0;
            for (int div=0; div < numberOfDivisions; div++) {
                totalOfTerm += salesData[div][term];
            }
            // Store total for this term
            totalsPerTerm[term] = totalOfTerm;
        }
        return totalsPerTerm;
    }

    /**
     * This function returns sales averages per term.
     * This function calls function calculateTotalsPerTerm(salesData).
     * @param salesData
     * @return averagesOfTerm
     */
    public static double[] calculateAveragesOfTerm(double[][] salesData) {

        // Determines the numbers of divisions and terms
        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[0].length;

        // Calls calculateTotalsPerTerm to get totals
        double[] totalsPerTerm = calculateTotalsPerTerm(salesData);

        // Define returned variable
        double[] averagesOfTerm = new double[numberOfTerms];

        // Loop through all terms
        for (int term=0; term<numberOfTerms; term++) {
            averagesOfTerm[term] = totalsPerTerm[term] / numberOfDivisions;
        }
        return averagesOfTerm;
    }

    /**
     * This function returns array of int that represents the highest sales division
     * for each term.
     * @param salesData
     * @return divisions
     */
    public static int[] getHighestSales(double[][] salesData) {

        // Determines the numbers of divisions and terms
        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[0].length;

        // Define returned variable
        int[] divisions = new int[numberOfTerms];

        // Outer loop goes through terms
        for (int term=0; term<numberOfTerms; term++) {

            // Inner loop goes through divisions to find the largest
            int highest =0;
            for (int div=0; div<numberOfDivisions; div++) {
                if (salesData[div][term] > salesData[0][term]) {
                    highest = div;
                }
            }
            // Stores the index of the highest for this term
            divisions[term] = highest;
        }
        return divisions;
    }

    /**
     * This function prints header line for the table.
     * @param salesData
     */
    public static void printHeaderLine(double[][] salesData) {

        // Determine the number of terms
        int numberOfTerms = salesData[0].length;

        // Prints  1st column
        System.out.printf("%20s%16s", "Division", "Term1");

        // For Term2 and over
        for (int term = 1; term < numberOfTerms; term++) {

            // Construct colum header String
            String termNum = "Term";
            termNum = termNum.concat(Integer.toString(term + 1));

            System.out.printf("%16s", termNum);
            System.out.printf("%14s", "(+/-)");
        }
        System.out.printf("\n");
    }

    /**
     * This function prints contents for the table.
     * @param namesOfDivisions
     * @param salesData
     * @param diffsPerDivision
     */
    public static void printContents(String[] namesOfDivisions, double[][] salesData, double[][] diffsPerDivision) {

        // Determine the sizes of divisions and terms
        int numberOfDivisions = salesData.length;
        int numberOfTerms = salesData[0].length;

        // Loop for printing the contents
        for (int div = 0; div < numberOfDivisions; div++) {

            // Prints division name
            System.out.printf("%20s", namesOfDivisions[div]);

            // Prints the first term
            System.out.printf("%,16.2f", salesData[div][0]);

            // Prints the second term and over
            for (int term = 1; term < numberOfTerms; term++) {
                System.out.printf("%,16.2f", salesData[div][term]);
                System.out.printf("%+,14.2f", diffsPerDivision[div][term - 1]);
            }
            System.out.printf("\n");
        }
    }

    /**
     * This function prints the total sales row of the table.
     * @param totals
     * @param diffs
     */
    public static void printTotalRow(double[] totals, double[] diffs) {

        // Determine the number of terms
        int numberOfTerms = totals.length;

        // Prints first column
        System.out.printf("%20s", "Total");

        // Prints the first term
        System.out.printf("%,16.2f", totals[0]);

        // Prints the second term and over
        for (int term = 1; term < numberOfTerms; term++) {
            System.out.printf("%,16.2f", totals[term]);
            System.out.printf("%+,14.2f", diffs[term - 1]);
        }
            System.out.printf("\n");
    }

    /**
     * This function prints the average row of the table.
     * @param averages
     */
    public static void printAverageRow(double[] averages) {
        // Determine the number of terms
        int numberOfTerms = averages.length;

        // Prints first column
        System.out.printf("%20s", "Average");

        // Prints the first term
        System.out.printf("%,16.2f", averages[0]);

        // Prints the second term and over
        for (int term = 1; term < numberOfTerms; term++) {
            System.out.printf("%,16.2f", averages[term]);
            System.out.printf("%14s", "-");
        }
        System.out.printf("\n");
    }

    /**
     * This function prints the row of divisions with the highest sales in each term.
     * @param namesOfDivisions
     * @param highest
     */
    public static void printHighestDivisionRow(String[] namesOfDivisions, int[] highest) {

        // Determine the number of terms
        int numberOfTerms = highest.length;

        // Prints first column
        System.out.printf("%20s", "Highest");

        // Prints the first term
        System.out.printf("%16s", namesOfDivisions[ (highest[0]) ]);

        // Prints the second term and over
        for (int term = 1; term < numberOfTerms; term++) {
            System.out.printf("%16s", namesOfDivisions[ (highest[term]) ]);
            System.out.printf("%14s", "-");
        }
        System.out.printf("\n");
    }

}
