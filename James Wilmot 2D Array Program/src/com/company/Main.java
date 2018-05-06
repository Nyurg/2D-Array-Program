package com.company;

import com.company.Customer;

import java.io.*;
import java.util.*;

public class Main {

    static Seat seats[][] = new Seat[12][6];
    static Customer customerSeats[][] = new Customer[12][6];
    static String dataTextFile;

    public static String printSeats(Customer[][] seats) throws IOException {
        String seatsText = "";
        seatsText += "        A B C   D E F\n";
        int rows = 1;
        for (Customer[] r : seats) {
            String printLine = "";
            if (rows < 10) {
                printLine += ("Row " + rows + "  ");
            } else {
                printLine += ("Row " + rows + " ");
            }

            int columns = 1;
            for (Customer c : r) {

                if (columns == 3) {
                    if (c == null) {
                        printLine += " *  ";
                    } else if (c.isAdult()) {
                        printLine += " A  ";
                    } else {
                        printLine += " C  ";
                    }
                } else {
                    if (c == null) {
                        printLine += " *";
                    } else if (c.isAdult()) {
                        printLine += " A";
                    } else {
                        printLine += " C";
                    }
                }
                columns++;
            }
            seatsText += printLine + "\n";
            rows++;
        }
        return seatsText;
    }

    private static void AllocateSeat(Customer customer) throws IOException {
        boolean customerAdded = false;

        int rowNumber = 0;
        for (Seat[] r : seats) {

            int colNumber = 0;
            for (Seat c : r) {
                if (customer.getSeatClass() == c.getSeatClass() &&
                        customer.getSeatPref() == c.getSeatPref() &&
                        !customerAdded) {
                    customer.setSeatClass(c.getSeatClass());
                    customer.setSeatPref(c.getSeatPref());
                    customerSeats[rowNumber][colNumber] = customer;
                    customerAdded = true;
                }
                colNumber++;
            }
            rowNumber++;
        }
    }

    public static void initialiseSeats() throws IOException {
        String seatPref;
        String seatClass;

        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 6; c++) {

                if (c == 0 || c == 5) {
                    seatPref = "Window";
                } else if (c == 1 || c == 4) {
                    seatPref = "Middle";
                } else {
                    seatPref = "Aisle";
                }

                if (r == 0 || r == 1) {
                    seatClass = "First";
                } else if (r == 2 || r == 3 ||
                        r == 4 || r == 5) {
                    seatClass = "Business";
                } else {
                    seatClass = "Economy";
                }

                seats[r][c] = new Seat(seatClass, seatPref);
            }
        }

    }

    public static String readBytes(String file, int position, int length) {
        String record = null;
        try {
            RandomAccessFile fileStore = new RandomAccessFile(file, "r");
            fileStore.seek(position);
            byte[] bytes = new byte[length];
            fileStore.read(bytes);
            record = new String(bytes);
            fileStore.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return record;
    }

    public static void writeToFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("seats.txt", "rw");
        /*
        dataTextFile = "";

        int rowNumber = 0;
        for (Customer[] c : customerSeats)
        {
            dataTextFile += "Row: " + (rowNumber + 1) + "\n";

            int colNumber = 0;
            for (Customer r: c)
            {
                if (r != null)
                {
                    dataTextFile += "Column: " + colNumber + r.getName() +
                            r.getSeatClass() + r.getSeatPref() + r.isAdult() + "\n";
                }
                colNumber++;
            }
            rowNumber++;
        }
        */
        raf.seek(10);
        raf.writeUTF(printSeats(customerSeats));
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        initialiseSeats();
        System.out.println(printSeats(customerSeats));

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("Press 1 to add a booking");
            System.out.println("Press 2 to remove a booking");
            System.out.println("Press 3 to exit the program");
            Scanner scannerIn = new Scanner(System.in);
            int choice = scannerIn.nextInt();

            if (choice == 1) {
                boolean isAdult = true;
                System.out.println("Please enter your name");
                String name = new Scanner(System.in).nextLine();
                String customerClass = "";
                String seatType = "";

                System.out.println("Are you an adult? (Over 18)");
                System.out.println("Press 1 if yes");
                System.out.println("Press 2 if no");
                choice = scannerIn.nextInt();
                if (choice == 1) {
                    isAdult = true;
                } else if (choice == 2) {
                    isAdult = false;
                }

                System.out.println("What class would you like?");
                System.out.println("Press 1 for first class");
                System.out.println("Press 2 for business class");
                System.out.println("Press 3 for economy class");
                choice = scannerIn.nextInt();
                if (choice == 1) {
                    customerClass = "First";
                } else if (choice == 2) {
                    customerClass = "Business";
                } else if (choice == 3) {
                    customerClass = "Economy";
                }

                System.out.println("What is your preferred seat type");
                System.out.println("Press 1 for window");
                System.out.println("Press 2 for aisle");
                System.out.println("Press 3 for middle");
                choice = scannerIn.nextInt();
                if (choice == 1) {
                    seatType = "Window";
                } else if (choice == 2) {
                    seatType = "Aisle";
                } else if (choice == 3) {
                    seatType = "Middle";
                }

                System.out.println("\nYou are " + name + ", Adult = " + isAdult);
                System.out.println("You have chosen " + customerClass + " class " +
                        "and you would prefer a " + seatType + " seat");
                System.out.println("Is this correct?");
                System.out.println("Press 1 if yes");
                System.out.println("Press 2 if no");
                choice = scannerIn.nextInt();
                if (choice == 1) {
                    Seat newSeat = new Seat(customerClass, seatType);
                    Customer newCustomer = new Customer(name, isAdult, newSeat);
                    AllocateSeat(newCustomer);
                    System.out.println(printSeats(customerSeats));
                    writeToFile();
                }

            }
            else if (choice == 2)
            {
                int rowToDelete;
                int columnToDelete;

                System.out.println("From which row would you like to delete?");
                rowToDelete = scannerIn.nextInt() - 1;

                System.out.println("From which column would you like to delete? Enter the corresponding number\n" +
                        "A B C D E F\n" +
                        "1 2 3 4 5 6");
                columnToDelete = scannerIn.nextInt() - 1;

                customerSeats[rowToDelete][columnToDelete] = null;
                System.out.println(printSeats(customerSeats));
            }
            else if (choice == 3)
            {
                exit = true;
            }
            else {
                System.out.println("Invalid Input");
            }
        }
    }
}
