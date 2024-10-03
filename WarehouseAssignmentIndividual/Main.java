import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

class Main 
{
    // Store function to store each Client in a linked list
      public static void store(Scanner scanner, LinkedList<Client> list) {
        System.out.println("How many clients would you like to add?");
        // Convert into an int
        int clientNum = Integer.parseInt(scanner.nextLine());  

        // Ask for client information
        System.out.println("Enter the client's information:");
        for (int i = 0; i < clientNum; i++) {
            System.out.print("Please enter the client's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Please enter the client's last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Please enter the client's email: ");
            String email = scanner.nextLine();
            System.out.print("Please enter the client's ID (Should consist of only 6 digits): ");
            String clientId = scanner.nextLine();
            System.out.print("Please enter the client's Address: ");
            String address = scanner.nextLine();
            System.out.print("Please enter the client's phone number: ");
            String phoneNumber = scanner.nextLine();

            list.add(new Client(firstName, lastName, email, clientId, address, phoneNumber));
        }
    }

    // Display function to print out the linked list to the file 
    public static void display(PrintStream outputStream, LinkedList<Client> list) {
        for (int i = 0; i < list.size(); i++) {
            outputStream.println(list.get(i).toString());
        }
    }

    public static void main(String[] s) throws FileNotFoundException {
        String outFileName;
        String findAnother;
        boolean findNext = true;
        int findID;
        ClientList clientList = new ClientList();
        Scanner keyScanner = new Scanner(System.in);

        // Ask user to name the output file
        System.out.println("Please enter name of your output file: ");
        outFileName = keyScanner.nextLine();
        PrintStream printer = new PrintStream(outFileName);

        // Store function to input client manually
        store(keyScanner, clientList.getList());
        
        //System.out.println("Displaying every person in the list:");
        display(printer, clientList.getList());

        int index = 0;

        // Displaying client list to user
        if(index != -1){
        System.out.println("Client list: " + clientList.getList().get(index).toString());
    }
    clientList.displayClients();

        keyScanner.close();

        
   
}
}
