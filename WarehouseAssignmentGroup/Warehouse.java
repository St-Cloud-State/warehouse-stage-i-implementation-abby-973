import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
    private ClientList clientList;
    private ProductList productList;
    private static Warehouse warehouse;

    private Warehouse(){
        clientList = ClientList.getInstance();
        productList = ProductList.getInstance();
    }

public static Warehouse instance() {
    if (warehouse == null) {
      ClientIDServer.instance(); // instantiate all singletons
      ProductIDServer.getInstance();
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Client addClient(String FirstName, String LastName, String Email, String Address, String PhoneNumber) {
    Client client = new Client(FirstName, LastName, Email, Address, PhoneNumber);
    if (clientList.addClient(client)) {
      return (client);
    }
    return null;
  }
  public Product addProduct(String name, double salePrice, int initialQuantity) {
    Product product = new Product(name, salePrice, initialQuantity);
    if (productList.addProduct(product)) {
      return (product);
    }
    return null;
  }

  public Iterator getClients() {
      return clientList.getClient();
  }

  public Iterator getProducts() {
      return productList.getProducts();
  }

  public WishListItem addToWishlist(int cID, int pID, int initialQuantity)
  {
    WishList userWishlist = clientList.find(cID).getWishList();
    WishListItem item = new WishListItem(productList.find(pID), initialQuantity); 
    userWishlist.add(item);
    return item;
  }
  public Iterator getWishlist(int cID)
  {
    WishList userWishlist = clientList.find(cID).getWishList();
    return userWishlist.getItems();
  }

  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIDServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIDServer.instance());
      output.writeObject(ProductIDServer.getInstance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String toString() {
    return clientList + "\n" + productList;
  } 
}

