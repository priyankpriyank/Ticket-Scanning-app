//@Author: Priyank Vora , 000922930

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The class Table is the main Class that contains many Instance Variables and constructor that have different work.
 */
public class Table
{

    //instance variables
    private String tablename;
    private int numRows;
    private int numCols;
    private String[][] grid;



    /**
     *
     * Initialize the class with the name of the tab delimited text file you wish to manage.
     *
     * @param filename  the name of tab delimited text file in the Table Class in the form of String.
     */

    public Table( String filename )
    { //table
        tablename = filename;
        numRows=0;
        numCols=0;
        String s;
        int r;
        String[] item;



        //Pass1:  going through the Table file that counts the
        //        numRows and numCols in the grid
        try {


            Scanner theFile = new Scanner(new FileInputStream(new File (tablename)));
            while ( theFile.hasNextLine() )
            {
                s = theFile.nextLine();
                item = s.split("\t", 0);


                if (item.length > numCols)
                    numCols = item.length;

                numRows++;

            }
            theFile.close();
        }
        catch (FileNotFoundException  e)
        {
            System.out.println("Table class Error 1: file not found.");
        }



        grid = new String[numRows][numCols];


        //Pass2:  populate the grid
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File (tablename)));
            r=0;
            while ( theFile.hasNextLine() )
            {
                s = theFile.nextLine();
                item = s.split("\t", 0);

                for(int c=0; c < numCols; c++) {



                    if ( item[c].length() == 0)
                        grid[r][c] = "";
                    else
                        grid[r][c] = item[c];


                }
                r++;


            }
            theFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Table class error 2: file not found.");
        }





    } //table


//------------------------------------------------------------------------------------------

    /**
     *
     * Display menu class has all the code that displays the menu according to the selected Value.
     *
     */
    public void displayMenu(){


        for(int r = 0; r< grid.length; r++){
            for(int c=0; c<grid[r].length; c++) {
                System.out.print(grid[r][c] + "\t");
            }
            System.out.println();
        }

    }


    /**
     *
     * Search tickets code lookup for the code of the Ticket that has in the codes.txt file
     *
     * @param key  the key is might be the Primary Barcode that compare with the valuein the grid in the form of the String.
     * @return integer r.
     */
    public int searchTicketsCode(String key){

        for(int r = 0; r<grid.length; r++){
            if(key.equals(grid[r][0]))
                return r;
        }
        return -1;
    }



    /**
     *
     * Gets the number of Column that is in the Grid.
     *
     * @return the Number of Column
     */
    public int getNumCols() {

        return numCols;
    }



    /**
     *
     * Gets the matches clas helps to search for the barcode.
     *
     * @param key the key that might be the Barcode or the input.
     * @return the matches
     */
    public String[] getMatches (String key) {


        String[] str = new String[numCols];

        int rowNum = searchTicketsCode(key);
        if (rowNum == -1)
            return str;

        int i = 0;
        for(int c = 0; c < numCols; c++)
            str[c] = grid[rowNum][c];
        return str;

    }


    /**
     *
     * Gets the data value in the form of Row and Columns and get the value using the nested if statement.
     *
     * @param row  the row in the form of integer.
     * @param col  the col as an integer.
     * @return the data in the form of grid.
     */
    public String getDataValue(int row,int col){

        if(row>=0 && row<numRows)
            if(col>=0 && col<numCols)
                return grid[row][col];
        return "";

    }

    /**
     *
     * Sets the data value in the form of Row and Columns and get the value using the nested if statement.
     *
     * @param row  the row as an integer.
     * @param col  the col as an integer.
     * @param value the value in the form of String.
     */
    public void setDataValue(int row,int col , String value){

        if(row>=0 && row<numRows)
            if(col>=0 && col<numCols)
                grid[row][col] = value;


    }


    /**
     *
     * Purchase ticket class helps to check whether the ticket is purchased or not.
     *
     * @param barcode the barcode in the form of String.
     * @return boolean that gives the Output in True or False.
     */
    public boolean purchaseTicket(String barcode) {

        // Check if the ticket corresponding to the barcode is purchased
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return grid[r][1].equals("Y");
        }
        return false;
    }



    /**
     *
     * Validate tickets class helps to validate tickets and that give the output according to that.
     *
     * @param barcode  the barcode in the form of String.
     * @return boolean that gives the Output in True or False.
     */
    public boolean validateTickets(String barcode) {

        // Check if the given barcode is valid
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return true;
        }
        return false;
    }


    /**
     *
     * Validate ticket entries class that helps to validate the Ticket Entries.
     *
     * @param barcode  the barcode as String.
     * @return boolean that gives the Output in True or False.
     */
    public boolean validateTicketEntries(String barcode) {

        // Check if the ticket corresponding to the barcode is already entered
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode))
                return grid[r][2].equals("Y");
        }
        return false;
    }


    /**
     *
     * To save class includes the Table (Codes.txt) using the Try and Catch
     *
     */
    public void toSave() {

        // Update the file with the modified data
        try (FileWriter writer = new FileWriter(new File(tablename))) {
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    writer.write(grid[r][c]);
                    if (c < numCols - 1)
                        writer.write("\t");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating file.");
        }
    }


    /**
     *
     * Ticketentry class accept the Barcode as String and gives the Output whether the Entered Ticket is Duplicate or not.
     *
     * @param barcode  the barcode as String.
     */
    public void ticketentry(String barcode) {

        // Mark the ticket corresponding to the barcode as entered
        for (int r = 0; r < numRows; r++) {
            if (grid[r][0].equals(barcode)) {
                grid[r][2] = "Y";
                break;
            }
        }
        // Update the file after marking the ticket as entered
        toSave();
    }


    /**
     *
     * Reset ticket class works when the Reset Button Clicked.
     *
     */
    public void resetTicket() {

        // Reset all ticket entries to "N"
        for (int r = 0; r < numRows; r++) {
            grid[r][2] = "N";
        }
        // Update the file after resetting ticket entries
        toSave();
    }



    /**
     *
     * To string returns the output with Barcode and Number of Rows and Columns.
     *
     * @return String in the proper form of given Below.
     */
    public String toString() {


        return ("Table: " + tablename + "  rows = " + numRows + "  cols = " + numCols);
    }

} //class



