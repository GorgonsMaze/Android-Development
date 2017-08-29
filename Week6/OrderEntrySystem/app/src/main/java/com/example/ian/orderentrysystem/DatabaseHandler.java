package com.example.ian.orderentrysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.key;
import static android.R.attr.order;


public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "orderManager";

	// Contacts table name
	private static final String TABLE_ORDERS = "orders";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_FIRST_NAME = "first_name";
	private static final String KEY_LAST_NAME = "last_name";
	private static final String KEY_CHOCOLATE_TYPE = "chocolate_type";
	private static final String KEY_NUM_OF_BARS = "number_of_bars";
	private static final String KEY_SHIPPING_TYPE = "shipping_type";
	private static final String KEY_PRICE = "price";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT,"
				+ KEY_LAST_NAME + " TEXT," + KEY_CHOCOLATE_TYPE + " TEXT,"
				+ KEY_NUM_OF_BARS + " INTEGER," +KEY_SHIPPING_TYPE + " INTEGER,"
				+ KEY_PRICE + " REAL" + ")";
		/**
		 *  KEY PRICE INTEGER OR STRING?
		 */
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new Order to SQL DB
	void addOrder(Order order) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, order.getFirstName()); // Order First Name
		values.put(KEY_LAST_NAME, order.getLastName()); // Order Last Name
		values.put(KEY_CHOCOLATE_TYPE, order.getChocolateType()); // Order Chocolate Type
		values.put(KEY_NUM_OF_BARS, order.getNumOfBarsPurchased()); // Order Number of Bars
		/** SHIPPING TYPE IS BOOLEAN -- NEED TO CHECK HERE**/
		int shipType;
		if (order.getShippingType() == true) {
			shipType = 1;
		} else {
			shipType = 0;
		}
		values.put(KEY_SHIPPING_TYPE, shipType);
		/*** PRICE IS FLOAT -- NEED TO CHECK HERE -- **/
		values.put(KEY_PRICE, order.getPrice());

		// Inserting Row
		db.insert(TABLE_ORDERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single order
	Order getOrderByID(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE " + KEY_ID + " = " + "'" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();

        boolean shipping_type = false;

        if (cursor.getInt(5) == 1) {
            shipping_type = true;
        }

        Order order = new Order(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                shipping_type,
                cursor.getFloat(6));

		// return order
		return order;
	}


	// Getting All Orders greater than price
	public List<Order> getOrderByPrice(float price) {
		List<Order> orderList = new ArrayList<Order>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE " + KEY_PRICE + " >= " + "'" + price + "'";

        SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
                Order order = new Order();
                order.setId(Integer.parseInt(cursor.getString(0)));
                order.setFirstName(cursor.getString(1));
                order.setLastName(cursor.getString(2));
                order.setChocolateType(cursor.getString(3));
                order.setNumOfBarsPurchased(cursor.getInt(4));

                int shippingType = cursor.getInt(5);
                if (shippingType == 1) {
                    order.setShippingType(true);
                } else {
                    order.setShippingType(false);
                }
                order.setPrice(cursor.getFloat(6));


                // Adding order to list
                orderList.add(order);
			} while (cursor.moveToNext());
		}

		// return order list
		return orderList;
	}

	// Getting All Orders
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Order order = new Order();
				order.setId(Integer.parseInt(cursor.getString(0)));
				order.setFirstName(cursor.getString(1));
				order.setLastName(cursor.getString(2));
				order.setChocolateType(cursor.getString(3));
				order.setNumOfBarsPurchased(cursor.getInt(4));

				int shippingType = cursor.getInt(5);
				if (shippingType == 1) {
					order.setShippingType(true);
				} else {
					order.setShippingType(false);
				}
				order.setPrice(cursor.getFloat(6));


				// Adding order to list
				orderList.add(order);
			} while (cursor.moveToNext());
		}

		// return order list
		return orderList;
	}


	// Getting Orders Count
	public int getOrdersCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ORDERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int county = cursor.getCount();
		cursor.close();

		// return count
		return county;
	}
}
