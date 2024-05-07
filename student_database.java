import java.util.*;
import java.io.*;

public class student_database {

    public static class student
    {
        private final String fileName = "src/student.txt";

        public void add_student()
        {
            Scanner sc = new Scanner(System.in);

            // taking input from user
            System.out.print("Enter Roll Number : ");
            int rollNumber = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter Name : ");
            String name = sc.nextLine();
            System.out.print("Enter Gr. Number : ");
            int grNo = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter Class Name : ");
            String className = sc.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(rollNumber + "," + name + "," + grNo + "," + className);
                writer.newLine();
                System.out.println("Student details added to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void display_all_students()
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String data;
                while((data = reader.readLine()) != null)
                {
                    System.out.println(data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void search_student()
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("enter roll number of the student: ");
            int rollNo = sc.nextInt();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String data;
                boolean found = false;
                while((data = reader.readLine()) != null)
                {
                    String[] arr = data.split(",");
                    if (Integer.parseInt(arr[0]) == rollNo)
                    {
                        System.out.println("data found");
                        found = true;
                        break;
                    }
                }

                if (!found)
                {
                    System.out.println("data not found!!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        student std = new student();
        boolean flag = true;
        while(flag)
        {
            // display choices:
            System.out.println("\n");
            System.out.println("Options Avaliable :");
            System.out.println("1. Add Student");
            System.out.println("2. Read student data from File");
            System.out.println("3. Search student data");
            System.out.println("4. Exit");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice)
            {
                case 1:
                    // add student
                    std.add_student();
                    break;

                case 2:
                    // Read file
                    std.display_all_students();
                    break;

                case 3:
                    // search
                    std.search_student();
                    break;

                case 4:
                    flag = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        }
    }
}
