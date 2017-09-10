package fragmentlab.ian.fragmentlab;
import java.util.*;

public class Order {
	String firstName;
	String lastName;
	String chocolateType;
	int numOfBarsPurchased;
	boolean shippingType;
	
	public Order() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(String chocolateType) {
        this.chocolateType = chocolateType;
    }

    public int getNumOfBarsPurchased() {
        return numOfBarsPurchased;
    }

    public void setNumOfBarsPurchased(int numOfBarsPurchased) {
        this.numOfBarsPurchased = numOfBarsPurchased;
    }

    public boolean getShippingType() {
        return shippingType;
    }

    public void setShippingType(boolean shippingType) {
        this.shippingType = shippingType;
    }

    public int getNumberOfBars() {
        return numOfBarsPurchased;
    }
}
