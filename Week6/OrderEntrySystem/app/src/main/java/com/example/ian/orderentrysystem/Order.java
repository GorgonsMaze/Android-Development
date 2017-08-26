package com.example.ian.orderentrysystem;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

public class Order implements Parcelable {
    int id;
	String firstName;
	String lastName;
	String chocolateType;
	int numOfBarsPurchased;
	boolean shippingType;
    float price;
	
	public Order() {}

    public Order(int id, String fName, String lName, String chocType, int numPurchased, boolean shippingType, float price) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.chocolateType = chocType;
        this.numOfBarsPurchased = numPurchased;
        this.shippingType = shippingType;
        this.price = price;
    }

    public Order(String fName, String lName, String chocType, int numPurchased, boolean shippingType, float price) {
        this.firstName = fName;
        this.lastName = lName;
        this.chocolateType = chocType;
        this.numOfBarsPurchased = numPurchased;
        this.shippingType = shippingType;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isShippingType() {
        return shippingType;
    }

    public void setShippingType(boolean shippingType) {
        this.shippingType = shippingType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.chocolateType);
        dest.writeInt(this.numOfBarsPurchased);
        dest.writeByte(this.shippingType ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.price);
    }

    protected Order(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.chocolateType = in.readString();
        this.numOfBarsPurchased = in.readInt();
        this.shippingType = in.readByte() != 0;
        this.price = in.readFloat();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
