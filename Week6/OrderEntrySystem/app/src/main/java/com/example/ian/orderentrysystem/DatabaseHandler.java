package com.example.ian.orderentrysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "orderManager";

	// Contacts table name
	private static final String TABLE_ORDERS = "orders";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_FIRST_NAME = "first_name";
	private static final String KEY_LAST_NAME = "last_name";
	private static final String KEY_CHOCOLATE_TYPE = "chocolate_type";
	private static final String KEY_NUM_OF_BARS = "number_of_bars"
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
				+ KEY_PRICE + " REAL," + ")";
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

	// Getting single contact
	Order getOrder(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + TABLE_ORDERS + " WHERE " + id + " = "
		Cursor cursor = db.query(TABLE_ORDERS, new String[] { KEY_ID,
				KEY_NAME, KEY_TYPE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		bankAccount account = new bankAccount(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		return account;
	}


	// Getting All Accounts by name
	public List<bankAccount> getAccountByName(String name) {
		List<bankAccount> contactList = new ArrayList<bankAccount>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE " + KEY_NAME + " = " + "'" + name + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				bankAccount account = new bankAccount();
				account.setID(Integer.parseInt(cursor.getString(0)));
				account.setName(cursor.getString(1));
				account.set_type(cursor.getString(2));
				// Adding contact to list
				contactList.add(account);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Getting All Contacts
	public List<bankAccount> getAllAccounts() {
		List<bankAccount> contactList = new ArrayList<bankAccount>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				bankAccount account = new bankAccount();
				account.setID(Integer.parseInt(cursor.getString(0)));
				account.setName(cursor.getString(1));
				account.set_type(cursor.getString(2));
				// Adding contact to list
				contactList.add(account);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateAccount(bankAccount account) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, account.getName());
		values.put(KEY_TYPE, account.get_type());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(account.getID()) });
	}

	// Deleting single contact
	public void deleteAccount(bankAccount account) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ORDERS, KEY_ID + " = ?",
				new String[] { String.valueOf(account.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getAccountsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int county = cursor.getCount();
		cursor.close();

		// return count
		return county;
	}
}
