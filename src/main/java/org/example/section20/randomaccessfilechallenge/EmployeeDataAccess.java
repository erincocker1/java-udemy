package org.example.section20.randomaccessfilechallenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeDataAccess {

    private static int recordsInFile = 0;

    private static Map<Integer, Long> indexedIds = new LinkedHashMap<>();

    public static void main(String[] args) {
        String pathName = "src/main/java/org/example/section20/randomaccessfilechallenge/employees.dat";

        try (RandomAccessFile ra = new RandomAccessFile(pathName, "r")) {
            loadIndex(ra, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("--------------------------------");
        indexedIds.forEach((k, v) -> System.out.println(k));
        System.out.println("--------------------------------");

        changeEmployeeSalaries(pathName);
    }

    private static void changeEmployeeSalaries(String pathName) {
        Scanner scanner = new Scanner(System.in);
        int employeeId = 1;


        try (RandomAccessFile ra = new RandomAccessFile(pathName, "rw")) {
            while (true) {
                System.out.print("Enter an employee id or enter 0 to quit: ");
                employeeId = Integer.parseInt(scanner.nextLine());
                if (employeeId < 1) break;
                ra.seek(indexedIds.get(employeeId));
                System.out.printf("Employee ID: %d, Salary: £%.2f, Name: %s %s\n",
                        ra.readInt(), ra.readDouble(), ra.readUTF(), ra.readUTF());

                System.out.println("Enter the employee's new salary, or just press enter to leave it unchanged: ");
                String userInput = scanner.nextLine();
                if (!userInput.equals("")) {
                    ra.seek(indexedIds.get(employeeId));
                    ra.readInt();
                    ra.writeDouble(Double.parseDouble(userInput));
                }

                System.out.println("--------------------------------");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(indexPosition);
            recordsInFile = ra.readInt();
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readInt(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(recordsInFile + " records found");
    }
}
