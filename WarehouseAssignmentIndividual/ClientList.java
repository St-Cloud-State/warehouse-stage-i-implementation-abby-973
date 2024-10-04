import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ClientList {
    // Creates a linked list for client
    private LinkedList<Client> clients;

    public ClientList() {
        clients = new LinkedList<>();
    }

    public LinkedList<Client> getList()
    {
        return clients;
    }
    //Add clients to list
    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Client added: " + client);
    }

    //Displays client list
    public void displayClients() {
            System.out.println("Client List:");
            for (Client client : clients) {
                System.out.println(client.toString());
            }
    }



}

