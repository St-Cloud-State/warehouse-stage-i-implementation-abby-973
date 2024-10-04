import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int SHOW_CLIENT_WISHLIST = 3;
  private static final int ADD_PRODUCT_TO_WISHLIST = 4;
  private static final int SHOW_CLIENTS = 5;
  private static final int ADD_STOCK= 6;
  private static final int SHOW_PRODUCTS = 7;
  private static final int RETRIEVE = 8;
  private static final int SAVE = 9;
  private static final int HELP = 10;
  private UserInterface() {
    if (yesOrNo("Would you like to access the saved data? (yes/no):")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[yes] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client.");
    System.out.println(ADD_PRODUCT + " to  add a product.");
    System.out.println(SHOW_CLIENT_WISHLIST + " to  print client's for wishlist.");
    System.out.println(ADD_PRODUCT_TO_WISHLIST + " to  add product to a client's wishlist.");
    System.out.println(SHOW_CLIENTS + " to  print clients list:");
    System.out.println(ADD_STOCK + " to  add stock to a product.");
    System.out.println(SHOW_PRODUCTS + " to  print products:");
    System.out.println(SAVE + " to  save data.");
    System.out.println(RETRIEVE + " to  retrieve.");
    System.out.println(HELP + " for help to display options again.");
  }

  public void addClient() {
    String FirstName = getToken("Enter the client's first name:");
    String LastName = getToken("Enter the client's last name:");
    String Email = getToken("Enter the clients email:");
    String ClientID = getToken("Enter the clients ID:");
    String Address = getToken("Enter the client's address:");
    String PhoneNumber = getToken("Enter the client's phone number:");
    Client result;
    result = warehouse.addClient(FirstName, LastName, Email, Address, PhoneNumber);
    if (result == null) {
      System.out.println("Could not add client.");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter the product's name:");
      double salePrice = Double.parseDouble(getToken("Enter the product's price:"));
      int initialQuantity = Integer.parseInt(getToken("Enter the prodcut's quantity:"));
      result = warehouse.addProduct(name, salePrice, initialQuantity);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Would you like ot add more products??")) {
        break;
      }
    } while (true);
  }
  public void addToWishlist()
  {
    WishListItem result;
    int cID = Integer.parseInt(getToken("Enter the client's Id:"));
    int pID = Integer.parseInt(getToken("Enter the product's Id:"));
    int initialQantity = Integer.parseInt(getToken("Enter the quantity desired:"));
    result = warehouse.addToWishlist(cID, pID, initialQantity);
    if (result == null) {
      System.out.println("Could not add to wishlist.");
    }
    System.out.println(result);
  }

  public void showProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
        Product product = (Product)(allProducts.next());
        System.out.println(product.toString());
      }
  }

  public void showClients(){
      Iterator allClients = warehouse.getClients();
      while (allClients.hasNext()){
        Client client = (Client)(allClients.next());
        System.out.println(client.toString());
      }
  }
  public void showWishlist() {
    int cID = Integer.parseInt(getToken("Enter the client's ID:"));
    Iterator w = warehouse.getWishlist(cID); 
    while (w.hasNext()){
      WishListItem wi = (WishListItem)(w.next());
      System.out.println(wi.toString());
    }
}

  public void addStock() {
      System.out.println("Dummy Action");
  }
  public void getTransactions() {
      System.out.println("Dummy Action");   
  }

  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has save the data. \n" );
    } else {
      System.out.println(" There was an error while saving. \n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has retrieved data. \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist." );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:      addClient();
                                break;
        case ADD_PRODUCT:       addProducts();
                                break;
        case ADD_PRODUCT_TO_WISHLIST:      
                                addToWishlist();
                                break;
        case SHOW_CLIENT_WISHLIST:      
                                showWishlist();
                                break;
        case ADD_STOCK:         addStock();
                                break;
        case SAVE:              save();
                                break;
        case RETRIEVE:          retrieve();
                                break;
        case SHOW_CLIENTS:	    showClients();
                                break; 		
        case SHOW_PRODUCTS:	    showProducts();
                                break; 		
        case HELP:              help();
                                break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
