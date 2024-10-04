import java.util.*;
import java.lang.*;
import java.io.*;

// Updated to be a Linked List - Abby 
public class WishList {
    private String clientID; // Stores the client ID associated with this wishlist.
    //Updated to allow for a quantity to be addded
    private List<WishListItem> items = new LinkedList<WishListItem>();

    // Constructor to initialize the Wishlist with a specific client ID.
    public WishList(String clientID) {
        this.clientID = clientID;
    }

    // Getter method to retrieve the client ID.
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String ClientID) {
        this.clientID = ClientID;
    }

    // add is part of the linked list library - abby 
    public boolean add(WishListItem W) {
        items.add(W);
        return true; // Return true indicating successful addition.
    }

    public Iterator getItems() {
        return items.iterator();
    }

    // Method to update a product's details in the wishlist.
    public WishListItem find(String pID)
    {
        String findID = "P" + pID;
        for (int i=0; i < items.size(); i++)
        {
            if (items.get(i).getProductId() == findID) 
            {
                return items.get(i);
            }
        }
        return null;
    }

    /* 
    // Method to remove a product from the wishlist by its ID.
    public boolean remove(String productID) {
        // Remove the product if its ID matches the given productID.
        return products.removeIf(p -> p.getProductID().equals(productID));
    }
    */
}
