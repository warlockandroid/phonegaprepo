package com.teks.budgettracker.helper;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.teks.budgettracker.storage.BillStorage;
import com.teks.budgettracker.storage.BudgettrackerStorage;

public class DatabaseHelper {

	public static final String DATABASE_NAME = "B.db";
	static int DATABASE_VERSION = 1;

	private SQLiteDatabase db;
	private Context context;

	public DatabaseHelper(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
	}
	
	public void createBillTable(){
		try {
		String createTable  = "CREATE TABLE IF NOT EXISTS bill_category (id INTEGER PRIMARY KEY NOT NULL,"
			+ "amount TEXT,"
			+ "issue_date TEXT ,"
			+ "tag TEXT ,"
			+"reminder TEXT,"
			+"remind_date TEXT ,"
			+"category_name TEXT ,"
			+"notificationid  TEXT,"
			+"image_path TEXT ,"
			+"bill_paid BOOL ,"
			+"addtoexpense BOOL);";
		
		db.execSQL(createTable);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	 public void deleteFromSettingCategory(String categoryName){
	    	
		 
		 String deleteSql ="DELETE FROM   setting_category" +
		 		           " where category_name = ?";
		 
		 SQLiteStatement stmt = db.compileStatement(deleteSql);
		 try{
			 stmt.bindString(1, categoryName);
			 
			 stmt.execute();
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 finally{
			 if(stmt!=null){
				 stmt.close();
			 }
		 }
}
 public void deleteFromPlanCategory(String categoryName ,String status ){
	    	
		 
		 String deleteSql ="DELETE FROM plan_category" +
		 		           " where category_name = ? AND status = ? ";
		 
		 SQLiteStatement stmt = db.compileStatement(deleteSql);
	try{ 
		 stmt.bindString(1, categoryName);
		 stmt.bindString(2, status);
		 stmt.execute();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		if(stmt!=null){
			stmt.close();
		}
	}
		
		 
}
 public void deleteFromPlanCategory(String categoryName  ){
 	
	 
	 String deleteSql ="DELETE FROM plan_category" +
	 		           " where category_name = ? ";
	 
	 SQLiteStatement stmt = db.compileStatement(deleteSql);
try{ 
	 stmt.bindString(1, categoryName);
	 
	 stmt.execute();
}
catch (Exception e) {
	// TODO: handle exception
}
finally{
	if(stmt!=null){
		stmt.close();
	}
}
	
	 
}
 public void deleteFromActualCategory(String id  ){
 	
	 
	 String deleteSql ="DELETE FROM actualplan_category" +
	 		           " where id = ? ";
	 
	 SQLiteStatement stmt = db.compileStatement(deleteSql);
	 try{
	   stmt.bindString(1, id);
	
	 
	   stmt.execute();
	 }catch (Exception e) {
		// TODO: handle exception
	}
	 finally{
		 if(stmt!=null){
			 stmt.close();
		 }
	 }
}
public void deleteActualCategory(String CategoryName  ){
 	
	 
	 String deleteSql ="DELETE FROM actualplan_category" +
	 		           " where category_name  = ? ";
	 
	 SQLiteStatement stmt = db.compileStatement(deleteSql);
	 try{
	   stmt.bindString(1, CategoryName);
	
	 
	   stmt.execute();
	 }catch (Exception e) {
		// TODO: handle exception
	}
	 finally{
		 if(stmt!=null){
			 stmt.close();
		 }
	 }
}
	
	
	
	
	/*create total_amount table */
	public void createTotalAmountTable() {
		try {
			String createActualTable = "CREATE TABLE IF NOT EXISTS total_amount(id INTEGER PRIMARY KEY NOT NULL,"
					+ "amount TEXT,"
					+ "issue_date TEXT ,"
					+ "status TEXT);";

			db.execSQL(createActualTable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// closeDatabase();
	}




	/** Settings module database methods */

	public void createSettingCategoryTable() {
		try {
			String createActualTable = "CREATE TABLE IF NOT EXISTS setting_category(id INTEGER PRIMARY KEY NOT NULL,"
					+ "category_name  TEXT,"
					+ "color_namelist TEXT ,"
					+ "color_namevalue TEXT);";

			db.execSQL(createActualTable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// closeDatabase();
	}

	/* is called to close the database connection* */
	public void closeDatabase() {
		this.db.close();
	}

	/* is called to select the category from the database */
	public ArrayList<BudgettrackerStorage> selectSettingCategory() {

		ArrayList<BudgettrackerStorage> arraylistStorage = new ArrayList<BudgettrackerStorage>();

		Cursor c = db
				.rawQuery(
						"SELECT  id , category_name, color_namelist, color_namevalue FROM setting_category  ",
						null);
	 int rows = c.getCount();
	 try{
		 if (rows > 0) {
			
			c.moveToFirst();
			for (int i = 0; i < rows; i++) {
				BudgettrackerStorage budgetTrackerStorage = new BudgettrackerStorage();
				budgetTrackerStorage.setRow_id(Integer.toString(c.getInt(0)));
				budgetTrackerStorage.setCategory(c.getString(1));
				budgetTrackerStorage.setColor_name(c.getString(2));
				budgetTrackerStorage.setColor_nameValue(c.getString(3));
				c.moveToNext();
				arraylistStorage.add(budgetTrackerStorage);
			}
		}

		c.close();
		
	 }catch (Exception e) {
		// TODO: handle exception
	}
	 finally{
		 if(c!=null){
			 c.close();
		 }
	 }
		return arraylistStorage;
	}

	public void insertIntoSettingCategoryTable(String category,
			String color_Name, String color_Namevalues) {

		String insetSql = "INSERT INTO setting_category"
				+ " ( category_name, color_namelist, color_namevalue ) VALUES (?,?,?)";
		SQLiteStatement stmt = db.compileStatement(insetSql);
		try{
			stmt.bindString(1, category);
			stmt.bindString(2, color_Name);
			stmt.bindString(3, color_Namevalues);
			stmt.executeInsert();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			stmt.close();
		}
	}

	public void updateSettingCategory(String categoryName, String colorName,
			String colorValue, String row_id) {

		String updateSql = "UPDATE setting_category  " + "SET "
				+ "category_name  = ?, " + "color_namelist = ?,"
				+ "color_namevalue = ?" + "WHERE id = ?";
		Log.d("upadate value", categoryName + "  " + colorName + "  "
				+ colorValue + "  " + row_id);

		SQLiteStatement stmt = db.compileStatement(updateSql);
		try{
			stmt.bindString(1, categoryName);
			stmt.bindString(2, colorName);
			stmt.bindString(3, colorValue);
			stmt.bindString(4, row_id);
	
			stmt.execute();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			stmt.close();
		}
	}

	/** Settings module database methods */

	/** plan module database methods */

	public void createPlanCategoryTable() {

		String createPlanCategoryTable = "CREATE TABLE IF NOT EXISTS plan_category(category_name  TEXT,"
				+ "amount  TEXT,"
				+ "issue_date VARCHAR ,"
				+ "category_color TEXT," + "status TEXT);";
		db.execSQL(createPlanCategoryTable);
	}

	public void insertIntoPlanCategoryTable(String category_name,
			String amount, String issue_date, String category_color,
			String status) {

		String insetSql = "INSERT INTO plan_category"
				+ " ( category_name, amount, issue_date, category_color, status ) VALUES (?,?,?,?,?)";
		SQLiteStatement stmt = db.compileStatement(insetSql);
		try{
			stmt.bindString(1, category_name);
			stmt.bindString(2, amount);
			stmt.bindString(3, issue_date);
			stmt.bindString(4, category_color);
			stmt.bindString(5, status);
			stmt.executeInsert();
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		finally{
			if(stmt!= null){
				stmt.close();
			}
		}
	}

	public int selectCount(String tableName, String issue_date, String status) {
		String countSql = "SELECT COUNT(*) FROM " + tableName
				+ "   WHERE    status  =  ?  AND   issue_date = ?";

		Cursor c = db.rawQuery(countSql, new String[] { status, issue_date });
		
		c.moveToFirst();
		int count = c.getInt(0);
		try{
			
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(c!=null){
				c.close();
			}
		}
    
		return count;

	}
	
	public int selectCount(String tableName, String categoryName) {
		String countSql = "SELECT COUNT(*) FROM " + tableName
				+ "   WHERE    category_name = ? ";

		Cursor c = db.rawQuery(countSql, new String[] { categoryName });
		
		c.moveToFirst();
		int count = c.getInt(0);
		try{
			
			
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(c!=null){
				c.close();
			}
		}
    
		return count;

	}
	

	public int selectedCount(String tableName, String startDate,
			String endDate, String status) {
		
			String countSql = "SELECT COUNT(*) FROM " + tableName
					+ "  WHERE   CAST(issue_date AS INTEGER) >= " + startDate
					+ " AND CAST(issue_date AS INTEGER) <=" + endDate;
			try {
			    Cursor c = db.rawQuery(countSql, null);
			
				c.moveToFirst();
				int count = c.getInt(0);
			
				
				c.close();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
			return 0;
		}
		
		

	}

	public int selectCount(String categoryName, String starDate, String status,
			String endDate) {

		String selectCount = "SELECT COUNT(*) FROM actualplan_category WHERE  category_name = ?  AND  CAST(issue_date AS INTEGER) >= "
				+ starDate + " AND CAST(issue_date AS INTEGER) <=" + endDate;
		Cursor c = db.rawQuery(selectCount, new String[] { categoryName });
		c.moveToFirst();
		int count = c.getInt(0);
		
		try{
			
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(c!=null){
				c.close();
			}
		}
		return count;

	}

	/* is called to select the category from the database */
	public ArrayList<BudgettrackerStorage> selectPlanCategory(
			String issue_date, String status) {

		ArrayList<BudgettrackerStorage> arraylistSelectedCtaegoryStorage = new ArrayList<BudgettrackerStorage>();

		Cursor c = db
				.rawQuery(
						"SELECT  rowid , category_name, amount, category_color, issue_date, status   FROM plan_category  WHERE  issue_date = ?  AND status = ?  ",
						new String[] { issue_date, status });

		int rows = c.getCount();
		
        try{
			if (rows > 0) {
			
				c.moveToFirst();
				for (int i = 0; i < rows; i++) {
					BudgettrackerStorage budgetTrackerStorage = new BudgettrackerStorage();
	
					budgetTrackerStorage.setRow_id(Integer.toString(c.getInt(0)));
					Log.d("rowid", Integer.toString(c.getInt(0)));
					budgetTrackerStorage.setCategory(c.getString(1));
					Log.d("category", c.getString(1));
					budgetTrackerStorage.setAmount(c.getString(2));
					
					Log.d("amount", c.getString(2));
					budgetTrackerStorage.setColor_nameValue(c.getString(3));
					Log.d("color value", c.getString(3));
					budgetTrackerStorage.setIssueDate(c.getString(4));
					budgetTrackerStorage.setStatus(c.getString(5));
	
					// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));
	
					c.moveToNext();
	
					arraylistSelectedCtaegoryStorage.add(budgetTrackerStorage);
	
				}
	
			}
	
			c.close();
        }catch (Exception e) {
			// TODO: handle exception
		}
        finally{
        	if(c!=null){
        		c.close();
        	}
        }
		return arraylistSelectedCtaegoryStorage;

	}

	public ArrayList<BudgettrackerStorage> selectedCategory( String status) {

		ArrayList<BudgettrackerStorage> arraylistStorage = null;

		Cursor c = db
				.rawQuery(
						"SELECT  id , category_name, color_namelist, color_namevalue FROM setting_category"
								+ " WHERE  category_name  NOT IN  ( SELECT category_name  FROM  plan_category WHERE  status =   ? )  ",
						new String[]{status});
		int rows = c.getCount();
		try{
			if (rows > 0) {
				arraylistStorage = new ArrayList<BudgettrackerStorage>();
				c.moveToFirst();
				for (int i = 0; i < rows; i++) {
					BudgettrackerStorage budgetTrackerStorage = new BudgettrackerStorage();
					budgetTrackerStorage.setRow_id(Integer.toString(c.getInt(0)));
					budgetTrackerStorage.setCategory(c.getString(1));
					budgetTrackerStorage.setColor_name(c.getString(2));
					budgetTrackerStorage.setColor_nameValue(c.getString(3));
					// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));
					// System.out.println("total amount"+getTotalAmount(c.getString(1)));
					c.moveToNext();
					arraylistStorage.add(budgetTrackerStorage);
				}
			}

		 c.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(c!=null){
				c.close();
			}
		}
		
		return arraylistStorage;
	}

	public double getTotalAmount1(String columnName,  String starDate , String endDate , String categoryName) {
		// TODO Auto-generated method stub
		double sum = 0;
		
			Cursor cursor = db.rawQuery("SELECT SUM(" + columnName
					+ ") FROM actualplan_category WHERE  CAST(issue_date AS INTEGER)  >=  "+starDate
					+" AND CAST(issue_date AS INTEGER) <= " + endDate +"  AND category_name = '"+categoryName+"'"
						  , null);
			try {
				if (cursor.moveToFirst()) {
					sum = cursor.getDouble(0);
				}
				cursor.close();

			
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return 0;
		}
		finally{
			if(cursor!=null){
				cursor.close();
			}
		}
		
		
	}
	////////////////////////////////
	public double getTotalAmount(String startDate,String endDate, String category,  String columnName ){
		
		
		double sum = 0;
		
		 Cursor cursor  = db.rawQuery("SELECT SUM("+ columnName +") FROM actualplan_category WHERE category_name  = ? " +
		 		"     AND  CAST(issue_date AS INTEGER)  >=  "+ startDate + "  AND CAST(issue_date AS INTEGER) <= "
				+ endDate , new String[]{category});
		 try{
			if (cursor.moveToFirst()) {
				sum = cursor.getDouble(0);
			}
			cursor.close();

		
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return 0;
		}
		finally{
			if(cursor!=null){
				cursor.close();
			}
		}
		 
	}
	
	
	public float getTotalAmountt(String startDate,String endDate, String category,  String columnName ){
		
		
		int sum = 0;
		
		 Cursor cursor  = db.rawQuery("SELECT SUM("+ columnName +") FROM actualplan_category WHERE category_name  = ? " +
		 		"     AND  CAST(issue_date AS INTEGER)  >=  "+ startDate + "  AND CAST(issue_date AS INTEGER) <= "
				+ endDate , new String[]{category});
		 try{
			if (cursor.moveToFirst()) {
				sum = cursor.getInt(0);
			}
			cursor.close();

			
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return 0;
		}
		finally{
			if(cursor!=null){
				cursor.close();
			}
		}
		 
	}
	public double getTotalAmountForActualHistory(String startDate,String endDate,  String columnName ){
		double sum = 0;
		
		 Cursor cursor  = db.rawQuery("SELECT SUM("+ columnName +") FROM actualplan_category WHERE  "+
		 		"  CAST(issue_date AS INTEGER)  >=  "+ startDate + "  AND CAST(issue_date AS INTEGER) <= "
				+ endDate , null);
		 try{
			if (cursor.moveToFirst()) {
				sum = cursor.getDouble(0);
			}
			cursor.close();

			
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return 0;
		}
		finally{
			if(cursor!=null){
				cursor.close();
			}
		}
		 
	}

	// public void updatePlanCategory(String categoryName, String amount, String
	// categoryColorValue){
	// try{
	// String updateSql = "UPDATE plan_category  "+
	// "SET "+
	// "category_name  = ?, "+
	// "amount = ?,"+
	// "category_color = ?"+
	// "WHERE category_name = ?";
	// SQLiteStatement stmt = db.compileStatement(updateSql);
	// stmt.bindString(1, categoryName);
	// stmt.bindString(2, amount);
	// stmt.bindString(3, categoryColorValue);
	// stmt.bindString(4, categoryName);
	//			
	// stmt.execute();
	// }
	// catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }

	public void updatePlanCategor(String categoryName, String amount,
			String issue_date, String category_color, String status) {
		
			String updateSql = "UPDATE plan_category  " + "SET "
					+ "category_name  = ?, " + "amount = ?,"
					+ "category_color = ?," + "issue_date =  ? ,"
					+ " status = ? " + "WHERE category_name = ? AND status = ?";
			SQLiteStatement stmt = db.compileStatement(updateSql);
			try {
				stmt.bindString(1, categoryName);
				stmt.bindString(2, amount);
				stmt.bindString(3, issue_date);
				stmt.bindString(4, category_color);
				stmt.bindString(5, status);
				stmt.bindString(6, categoryName);
				stmt.bindString(7, status);
	
				stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	public void updatePlanCategor(String categoryColor, String categoryName) {
		
			String updateSql = "UPDATE plan_category  " + "SET "
					+ "category_color  = ? WHERE category_name = ?";
			SQLiteStatement stmt = db.compileStatement(updateSql);
			try {
				stmt.bindString(1, categoryColor);
				stmt.bindString(2, categoryName);
				stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	public void updatePlanCategory(String categoryName, String amount,
			String oldDate,String newDate, String category_color, String status) {
		
			String updateSql = "UPDATE plan_category  " + "SET "
					+ "category_name  = ?, " + "amount = ?,"
					+ "category_color = ?," + "issue_date =  ? ,"
					+ " status = ? " + "WHERE category_name = ? AND issue_date =  ?";
			
			SQLiteStatement stmt = db.compileStatement(updateSql);
			try {
				stmt.bindString(1, categoryName);
				stmt.bindString(2, amount);
			    stmt.bindString(3, category_color);
	            stmt.bindString(4, newDate);
			    stmt.bindString(5, status);
				stmt.bindString(6, categoryName);
				stmt.bindString(7, oldDate);
	
	
				stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	
	/**check for existing plan in the total_amount table by the taking the issue_date in a arraylist**/
	public ArrayList<String> checkForExistingPlan(String status, String tableName,String issueDate ){
		ArrayList<String> date =   new ArrayList<String>();
		Cursor c =  db.rawQuery("SELECT  issue_date  FROM "+ tableName+" WHERE status = ? AND issue_date = ?", new String[]{status, issueDate});
		try{
			int rows = c.getCount();
			if(rows>0){
				
				c.moveToFirst();
				for(int i = 0 ; i<rows; i++){
					date.add(c.getString(0));
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(c!=null){
				c.close();
			}
		}
		return date;
	}
	
	/**Insert into total_amount table**/
	public void insertIntoTotalAmountTable(String amount, String issue_date, String status) {

		String insetSql = "INSERT INTO total_amount"
				+ " (  amount, issue_date,  status ) VALUES (?,?,?)";
		SQLiteStatement stmt = db.compileStatement(insetSql);
		try{
			stmt.bindString(1, amount);
			stmt.bindString(2, issue_date);
			stmt.bindString(3, status);
			stmt.executeInsert();
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	public void updateTotalAmounttable( String amount,
			String issue_date, String status) {
		
			String updateSql = "UPDATE total_amount  " + "SET "
					+ "amount = ?,"
				    + "issue_date =  ? ,"
					+ " status = ? " + "WHERE issue_date = ? AND status = ?";
			SQLiteStatement stmt = db.compileStatement(updateSql);
			try{
				stmt.bindString(1, amount);
				stmt.bindString(2, issue_date);
				stmt.bindString(3, status);
				stmt.bindString(4, issue_date);
				stmt.bindString(5, status);
				stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(stmt!= null){
				if(stmt!=null){
					stmt.close();
				}
			}
		}
		
	}


	/** plan module database methods */
	/* actual module database methods */

	public void createActualCategoryTable() {
		try {
			String createActualTable = "CREATE TABLE IF NOT EXISTS actualplan_category(id INTEGER PRIMARY KEY NOT NULL,"
					+ "category_name  TEXT,"
					+ "amount TEXT ,"
					+ "issue_date VARCHAR ," + "tag TEXT);";

			db.execSQL(createActualTable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// closeDatabase();
	}

	public void insertIntoActualCategoryTable(String category_name,
			String amount, String issue_date, String tag) {

		String insetSql = "INSERT INTO actualplan_category"
				+ " ( category_name, amount, issue_date, tag ) VALUES (?,?,?,?)";
		SQLiteStatement stmt = db.compileStatement(insetSql);
		try{
			stmt.bindString(1, category_name);
			stmt.bindString(2, amount);
			stmt.bindString(3, issue_date);
			stmt.bindString(4, tag);
			stmt.executeInsert();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(stmt!=null){
				stmt.close();
			}
		}
		
	}
	
	public void  updateActualCategoryTable(String categoryName,
			String amount, String issueDate, String tag, String id) {
		
		
		 String updateSql  =  "    UPDATE  actualplan_category    set   "
				                                         + " category_name  =  ?, "
				                                         +"amount  =  ? ,"
				                                         +"issue_date = ?,"
				                                         +"tag =  ?" 
				                                         +"WHERE   id =  ?" ;
		 
		                                          SQLiteStatement stmt = db.compileStatement(updateSql);
		                                            try{
				                                      
				                             			stmt.bindString(1, categoryName);
				                             			stmt.bindString(2, amount);
				                             			stmt.bindString(3, issueDate);
				                             			stmt.bindString(4, tag);
				                             			stmt.bindString(5, id);
				                             			stmt.execute();
		                                 }
		                                 catch (Exception e) {
											// TODO: handle exception
		                                	 e.printStackTrace();
										}
		                                 finally {
		                    				 if (stmt!=null) {
		                    	    				stmt.close();
		                    					}
		                    			 }
				                                      		
	}

	public void selectActualCategoryProgress(String categoryName) {
		// String countSql = "SELECT COUNT(*) FROM " + tableName ;
		//			    
		// Cursor c = db.rawQuery(countSql,null);
		// c.moveToFirst();
		// int count = c.getInt(0);
		// System.out.println("555555555555");
		// System.out.println("counter  5555555"+Integer.toString(count));
		//			   
		String countSql = "SELECT  COUNT (category_name) FROM actualplan_category WHERE category_name ='"
				+ categoryName + "'";
		Cursor c = db.rawQuery(countSql, null);
		try{
			c.moveToFirst();
			int count = c.getInt(0);
			@SuppressWarnings("unused")
			int sum = 0;
			c.close();
			if (count > 0) {
				String sumSql = "SELECT   SUM (amount)  FROM  actualplan_amount  WHERE   category_name = ' "
						+ categoryName + "'";
				Cursor c1 = db.rawQuery(sumSql, null);
				if (c1.moveToFirst()) {
					sum = c1.getInt(0);
				}
				c1.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(c!=null){
				c.close();
			}
		}
	}

	public ArrayList<BudgettrackerStorage> selectActualDetails(
			String startDate, String endDate, String categoryName) {

		ArrayList<BudgettrackerStorage> arraylistSelectedCtaegoryStorage = new ArrayList<BudgettrackerStorage>();

		Cursor c = db.rawQuery(
				"SELECT    category_name, amount, issue_date, tag , id FROM  actualplan_category "
						+ " WHERE  CAST(issue_date AS INTEGER) >=  "
						+ startDate + "  AND CAST(issue_date AS INTEGER) <= "
						+ endDate + "  AND  category_name = ? ",
				new String[] { categoryName });

		int rows = c.getCount();
		
      try{
		if (rows > 0) {
			
			c.moveToFirst();
			for (int i = 0; i < rows; i++) {
			
				BudgettrackerStorage budgetTrackerStorage = new BudgettrackerStorage();
				try{
				budgetTrackerStorage.setCategory(c.getString(0));
				Log.d("category", c.getString(0));
				budgetTrackerStorage.setAmount(c.getString(1));
				Log.d("amount", c.getString(1));
				budgetTrackerStorage.setPurchaseDate(c.getString(2));
				Log.d("color value", c.getString(2));
				budgetTrackerStorage.setTag(c.getString(3));
				Log.d("color value", c.getString(3));
				budgetTrackerStorage.setRow_id(c.getString(4));
				Log.d("color value", c.getString(4));
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				
				}
				// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));

				c.moveToNext();
				arraylistSelectedCtaegoryStorage.add(budgetTrackerStorage);
			}
		}
		c.close();
      }catch (Exception e) {
		// TODO: handle exception
	}
      finally{
    	  if(c!=null){
    		  c.close();
    	  }
      }

		return arraylistSelectedCtaegoryStorage;
	}
	
	/**History module methods**/
	public double selectSumForPlanCategory(String tableName, String issueDate, String status){
		
		String sumSql = "SELECT   SUM (amount)  FROM "+tableName+"   WHERE  issue_date = ? AND  status = ?";
			
		double sum = 0;
		Cursor c1 = db.rawQuery(sumSql, new String[] { issueDate, status});
		try{
			c1.moveToFirst(); 
			if(!c1.getString(0).equals(null) ){
			  sum = c1.getDouble(0);
			}
			c1.close();
			
		
			return sum;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return 0;
			}
		
		finally{
			if(c1!=null){
				c1.close();
			}
		}
	}
	/* is called to select the category from the database */
	public ArrayList<BudgettrackerStorage> selectPlanCategoryHistory(
			String issue_date, String status) {

		ArrayList<BudgettrackerStorage> arraylistSelectedCtaegoryStorage = new ArrayList<BudgettrackerStorage>();

		Cursor c = db
				.rawQuery(
						"SELECT   category_name   FROM plan_category  WHERE  issue_date = ?  AND status = ? ",
						new String[] { issue_date, status });

		int rows = c.getCount();
		
      try{
		if (rows > 0) {
			
			c.moveToFirst();
			for (int i = 0; i < rows; i++) {
				BudgettrackerStorage budgetTrackerStorage = new BudgettrackerStorage();

				budgetTrackerStorage.setCategory(c.getString(0));
				
		

				// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));

				c.moveToNext();

				arraylistSelectedCtaegoryStorage.add(budgetTrackerStorage);

			}

		}

		c.close();
      }catch (Exception e) {
		// TODO: handle exception
	}finally{
		if(c!=null){
			c.close();
		}
	}

		return arraylistSelectedCtaegoryStorage;

	}
	/**bill category table***/
	public void insertIntoBill_Category(String amount,
			String issueDate, String tag, String remind_date, String categoryName, String notificationId, 
			Boolean billPaid, Boolean addToExpense, String imagepath ,String reminder) {
			  String paidBill =" ";
			  String expenseToAdd = " ";
			  if(billPaid){
				  paidBill = "1";
				  
			  }else{
				  paidBill ="0";
			  }
			  if(addToExpense){
				  expenseToAdd = "1";
				  
			  }else{
				  expenseToAdd ="0";
			  }

		String insetSql = "INSERT INTO bill_category"
				+ " ( amount , issue_date, tag, remind_date , category_name, notificationid, image_path, bill_paid," +
						"  addtoexpense , reminder) VALUES (?,?,?,?,?,?,?,?,?,?)";
		SQLiteStatement stmt = db.compileStatement(insetSql);
		try{
			stmt.bindString(1, amount);
			stmt.bindString(2, issueDate);
			stmt.bindString(3, tag);
			stmt.bindString(4, remind_date);
			stmt.bindString(5, categoryName);
			stmt.bindString(6, notificationId);
			stmt.bindString(7, imagepath);
			stmt.bindString(8,  paidBill);
			stmt.bindString(9, expenseToAdd);
			stmt.bindString(10, reminder);
			stmt.executeInsert();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	
	/**bill part sql**/
    public int selectCountForBill(){
    	String countSql = "SELECT COUNT(*) FROM bill_category  WHERE bill_paid = 0  ";
            
			Cursor c = db.rawQuery(countSql, null);
			int count = 0;
			try{
				c.moveToFirst();
				 count = c.getInt(0);
				
				c.close();
		   }catch (Exception e) {
			// TODO: handle exception
			  
		}finally{
			if(c!=null){
				c.close();
			}
		}
			
			return count;

    	
    }
    
    public ArrayList<BillStorage> selectForBill() {

		ArrayList<BillStorage> arraylistBill = new ArrayList<BillStorage>();
		Cursor c = db.rawQuery(
				"SELECT     amount, issue_date, tag , remind_date, category_name ," +
				"notificationid, image_path , reminder , bill_paid , addtoexpense FROM bill_category "
						+ " WHERE bill_paid = 0  ", null);

		int rows = c.getCount();
		
		try{
			if (rows > 0) {
				
				c.moveToFirst();
				for (int i = 0; i < rows; i++) {
				
					
					BillStorage billStorage = new BillStorage();
					
					billStorage.setBillAmount(c.getString(0));
					billStorage.setBillissueDate(c.getString(1));
					billStorage.setBilltag(c.getString(2));
					billStorage.setBillRemindDate(c.getString(3));
					billStorage.setBillCategoryName(c.getString(4));
					billStorage.setBillNotificationId(c.getString(5));
					billStorage.setBillImagepath(c.getString(6));
					billStorage.setBillReminder(c.getString(7));
					billStorage.setBillPaid(c.getString(8));
					billStorage.setBillAddtoExpence(c.getString(9));
					
	//	
					
				
				// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));

				c.moveToNext();
				arraylistBill.add(billStorage);
			}
		}
		c.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}finally{
			if(c!=null){
				c.close();
			}
		}

		return arraylistBill;
	}
	
    public void  updateBillTable(String amount,
			String issueDate, String tag, String remind_date, String categoryName, String notificationId, 
			Boolean billPaid, Boolean addToExpense, String imagepath ,String reminder ,String oldNotificationId) {
    	   String paidBill =" ";
		  String expenseToAdd = " ";
		  if(billPaid){
			  paidBill = "1";
			  
		  }else{
			  paidBill ="0";
		  }
		  if(addToExpense){
			  expenseToAdd = "1";
			  
		  }else{
			  expenseToAdd ="0";
		  }
		
		 String updateSql  =  "    UPDATE  bill_category    set   "
																    + "amount = ?,"
																	+ "issue_date = ?,"
																	+ "tag =  ?,"
																	+"reminder = ?,"
																	+"remind_date = ? ,"
																	+"category_name  = ?,"
																	+"notificationid = ? ,"
																	+"image_path = ?,"
																	+"bill_paid = ?,"
																	+"addtoexpense = ? WHERE notificationid = ?";
																	SQLiteStatement stmt = db.compileStatement(updateSql);
		   
		                                 try{
				                                        
				                             			stmt.bindString(1, amount);
				                             			stmt.bindString(2, issueDate);
				                             			stmt.bindString(3, tag);
				                             			stmt.bindString(4, reminder);
				                             			stmt.bindString(5, remind_date);
				                             			stmt.bindString(6, categoryName);
				                             			stmt.bindString(7, notificationId);
				                             			stmt.bindString(8, imagepath);
				                             			stmt.bindString(9, paidBill);
				                             			stmt.bindString(10, expenseToAdd);
				                             			stmt.bindString(11, oldNotificationId);
				                             			
				                             			
				                             			
				                             			
				                             			stmt.execute();
		                                 }
		                                 catch (Exception e) {
											// TODO: handle exception
		                                	 e.printStackTrace();
										}
		                                 finally{
		                                	 if(stmt!=null){
		                                		 stmt.close();
		                                	 }
		                                 }
				                                      		
	}
    public void deleteFromBill(String notificationId){
    	
    		 
    		 String deleteSql ="DELETE FROM bill_category" +
    		 		           " where notificationid = ?";
    		 
    		 SQLiteStatement stmt = db.compileStatement(deleteSql);
    		 try{
	    		 stmt.bindString(1, notificationId);
	    		 
	    		 stmt.execute();
    		 }catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(stmt!= null){
					stmt.close();
				}
			}
    }
    
    public void setAsPaidBill(String notificationId ){
    	String updateSql  =  "   UPDATE  bill_category    set   "
														   
															+"bill_paid = ?  "+
															"WHERE notificationid = ?";
										    	SQLiteStatement stmt = db.compileStatement(updateSql);
										    	try{
													stmt.bindString(1, "1");
													stmt.bindString(2, notificationId);
													stmt.execute();
													}catch (Exception e) {
														// TODO: handle exception
													}
													finally{
														if(stmt!=null){
															stmt.close();
														}
													}
										    	
    }
    public int selectActualCountForBill( String categoryName , String amount, String issue_date, String tag){
		    	String countSql = "SELECT COUNT(*) FROM   actualplan_category "
				+ "   WHERE    category_name  =  ?  AND   amount  = ?  AND issue_date = ? AND tag = ?";
		
		Cursor c = db.rawQuery(countSql, new String[] { categoryName, amount,issue_date ,tag });
		int count = 0;
		try{
			
		
			c.moveToFirst();
			 count = c.getInt(0);
			
			
			c.close();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(c!=null){
				c.close();
			}
		}
		return count;

    	
    }
    public void  updateBillActualCategoryTable(String categoryName,
			String amount, String issueDate, String tag, String oldIssuedate ,String oldtag ) {
		
		
		 String updateSql  =  "    UPDATE  actualplan_category    set   "
				                                         + " category_name  =  ?, "
				                                         +"amount  =  ? ,"
				                                         +"issue_date = ?,"
				                                         +"tag =  ?" 
				                                         +"WHERE   issue_date =  ? "
				                                         +"AND tag = ? ";
		 
		                  
		                   SQLiteStatement stmt = db.compileStatement(updateSql);                           
		                                 try{
				                                       
				                             			stmt.bindString(1, categoryName);
				                             			stmt.bindString(2, amount);
				                             			stmt.bindString(3, issueDate);
				                             			stmt.bindString(4, tag);
				                             			stmt.bindString(5, oldIssuedate);
				                             			stmt.bindString(6, oldtag);
				                             			stmt.execute();
		                                 }
		                                 catch (Exception e) {
											// TODO: handle exception
		                                	 e.printStackTrace();
										}
		                                 finally{
		                                	 if(stmt!=null){
		                                		 stmt.close();
		                                	 }
		                                 }
				                                      		
	}
    
    /**bill part sql**/
    public int selectCountForArchive(){
    	String countSql = "SELECT COUNT(*) FROM bill_category  WHERE bill_paid = 1  ";

			Cursor c = db.rawQuery(countSql, null);
			int count = 0;
			try{
				c.moveToFirst();
				 count = c.getInt(0);
				
				c.close();
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(c!=null){
					c.close();
				}
			}
			return count;

    	
    }
    public ArrayList<BillStorage> selectForArchive() {

		ArrayList<BillStorage> arraylistBill = new ArrayList<BillStorage>();

		Cursor c = db.rawQuery(
				"SELECT     amount, issue_date, tag , remind_date, category_name ," +
				"notificationid, image_path , reminder , bill_paid , addtoexpense FROM bill_category "
						+ " WHERE bill_paid = 1  ", null);

		int rows = c.getCount();
		
       try{
			if (rows > 0) {
				
				c.moveToFirst();
				for (int i = 0; i < rows; i++) {
				
					
					BillStorage billStorage = new BillStorage();
				
					billStorage.setBillAmount(c.getString(0));
					billStorage.setBillissueDate(c.getString(1));
					billStorage.setBilltag(c.getString(2));
					billStorage.setBillRemindDate(c.getString(3));
					billStorage.setBillCategoryName(c.getString(4));
					billStorage.setBillNotificationId(c.getString(5));
					billStorage.setBillImagepath(c.getString(6));
					billStorage.setBillReminder(c.getString(7));
					billStorage.setBillPaid(c.getString(8));
					billStorage.setBillAddtoExpence(c.getString(9));
					
	//	
					
					
					// budgetTrackerStorage.setTotalAmount(""+getTotalAmount(c.getString(1)));
	
					c.moveToNext();
					arraylistBill.add(billStorage);
			}
		}
		c.close();
       }catch (Exception e) {
		// TODO: handle exception
	}finally{
		if(c!=null){
			c.close();
		}
	}
		return arraylistBill;
	}
	
    
	/* inner class to extending the SQLiteopenHelper to get the default methods* */
	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
     public void deleteActualFromPlan(String categoryName , String startDate, String endDate){
	    	
		 
		 String deleteSql ="DELETE FROM   actualplan_category" +
		 		           "   WHERE  CAST(issue_date AS INTEGER) >=  "
						+ startDate + "  AND CAST(issue_date AS INTEGER) <= "
						+ endDate + "  AND  category_name = '"+ categoryName +"'";
		 
		 SQLiteStatement stmt = db.compileStatement(deleteSql );
		 try{
			 
			 
			 stmt.execute();
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 finally{
			 if(stmt!=null){
				 stmt.close();
			 }
		 }
}
}