//@Author: Priyank Vora , 000922930
import java.util.Scanner;

/**
 * The class Table test program is the main class consists Main Static class that includes the Menu and the Different
   Public classes from the Table Class.
 */
public class TableTestProgram
{
    /**
     *
     * Main class initialize Some useful Values and help to print the output when run the TestProgram.
     * It uses the If else Statement and Choice according to the option that we select and it triggerred
       the public class form the Table class respectively.
     *
     * @param args the args that runs the main class.
     * @throws Exception

     */
    public static void main(String[] args ) throws Exception
    {


        Scanner in = new Scanner(System.in);
        String tablename = "";
        String choice = "";
        int row = -1;
        int colNum=1;
        String key = "";
        String s_colNum = "";
        String newValue = "";
        String target = "";


        System.out.print("Enter the name of the tab delimited text file you wish to manage (e.g. codes.txt) > ");
        tablename = in.nextLine();
        Table t = new Table(tablename);
        System.out.println("Successfully loaded: " + t);


        while (true) {
            System.out.println("\n\nTable Testing Menu\n");

            System.out.println("1. Display all data");
            System.out.println("2. Lookup");
            System.out.println("3. Search");
            System.out.println("4. Change");
            System.out.println("5. Save data to " + tablename);
            System.out.println("6. Get Single Cell Value");
            System.out.println("7. Save Single Cell Value");
            System.out.println("8. Quit");
            System.out.print("Select > ");
            choice = in.nextLine();

            if (choice.equals("8")) break;
            if(choice.equals("1")) t.displayMenu();


            else if (choice.equals("2")) {
                System.out.print("Enter the Primary key > ");
                target = in.nextLine();
                int rowNum = t.searchTicketsCode(target);
                if(rowNum == -1)
                    System.out.print(target + " not Found. ");
                else
                    System.out.print(target + " found at the row number " + String.format("%d", rowNum));

            }

            else if (choice.equals("3")) {
                String[] str = new String[t.getNumCols()];
                System.out.print("Enter the Primary key(Barcode) > ");
                target = in.nextLine();
                int rowNum = t.searchTicketsCode(target);
                if(rowNum == -1)
                    System.out.println(target + " not Found. ");
                else
                    str = t.getMatches(target);
                for(int s = 0; s< str.length; s++){
                    System.out.printf("[%d] - %s\n",s,str[s]);
                }

            }

            else if(choice.equals("4")){
                System.out.print("Enter Row Number: ");
                int rowNo = in.nextInt();
                in.nextLine();
                System.out.print("Enter Column Number: ");
                int ColNo = in.nextInt();
                in.nextLine();
                target = t.getDataValue(rowNo, ColNo);
                System.out.println("The cell Value is : " + target);

                System.out.println("Please Enter a New Value: ");
                String change = in.nextLine();
                t.setDataValue(rowNo, ColNo , change);
                System.out.println("Data Chnaged!");

            }

            else if (choice.equals("5")){
                t.toSave();
                System.out.println("Saving" + tablename);
            }

            else if(choice.equals("6")){
                System.out.print("Enter Row Number: ");
                int rowNo = in.nextInt();
                in.nextLine();
                System.out.print("Enter Column Number: ");
                int ColNo = in.nextInt();
                in.nextLine();
                target = t.getDataValue(rowNo, ColNo);
                System.out.println("The cell Value is : " + target);
            }
            else if (choice.equals("7")) {
                System.out.print("Enter Row Number: ");
                int rowNo7 = in.nextInt();
                in.nextLine();
                System.out.print("Enter Column Number: ");
                int ColNo7 = in.nextInt();
                in.nextLine();
                System.out.print("Enter a New Value: ");
                String newvalue7 = in.nextLine();
                t.setDataValue(rowNo7, ColNo7,newvalue7);
                System.out.println("The single cell changed successfully");
            }

            else
                System.out.println("Invalid Input! Try again..");
        }
        System.out.println("Thank-you, good bye!");

    }
}
       
       

