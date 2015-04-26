package com.simple.kanjiquiz.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "QuizeDatabase.sqlite";
	private static final String DB_PATH_SUFFIX = "/databases/";
	static Context ctx;

	public SqlLiteDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		ctx = context;
	}

	public HashMap<String, String[]> getMathHighScore() {
		HashMap<String, String[]> hmHighScore = new HashMap<String, String[]>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM HighScore", null);

		Log.d("tag", "Count : " + cursor.getCount());
		cursor.moveToFirst();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					String rowArray[] = new String[2];
					rowArray[0] = cursor.getString(1);
					rowArray[1] = cursor.getString(2);
					hmHighScore.put(cursor.getString(0), rowArray);
					cursor.moveToNext();
				}
				return hmHighScore;
			}
		}
		return null;

	}

	public String[] getSingleTestHighScore(String test) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM HighScore where test='"
				+ test + "'", null);

		Log.d("tag", "test : " + test);
		Log.d("tag", "Count : " + cursor.getCount());
		cursor.moveToFirst();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				String rowArray[] = new String[2];
				rowArray[0] = cursor.getString(1);
				rowArray[1] = cursor.getString(2);
				return rowArray;
			}
		}
		return null;

	}

	public ArrayList<HashMap<String, String>> getQuestion(String tblName) {
		ArrayList<HashMap<String, String>> alListOfQuestionDataContainer = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + tblName, null);
		Log.d("tag", "Count : " + cursor.getCount());
		cursor.moveToFirst();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					HashMap<String, String> hmQuestionRowData = new HashMap<String, String>();

					try {
						if (cursor.getString(0) == null) {
							hmQuestionRowData.put("option_b_ans", "");
						} else {
							hmQuestionRowData.put("option_b_ans",
									cursor.getString(0));
						}

					} catch (Exception e) {
						hmQuestionRowData.put("option_b_ans", "");
						e.printStackTrace();
					}
					try {
						if (cursor.getString(1) == null) {
							hmQuestionRowData.put("option_a_ans", "");
						} else {
							hmQuestionRowData.put("option_a_ans",
									cursor.getString(1));
						}

					} catch (Exception e) {
						hmQuestionRowData.put("option_a_ans", "");
						e.printStackTrace();
					}

					hmQuestionRowData.put("index", cursor.getString(2));
					hmQuestionRowData.put("id", cursor.getString(3));
					hmQuestionRowData.put("question", cursor.getString(4));
					hmQuestionRowData.put("ans_1", cursor.getString(5));
					hmQuestionRowData.put("ans_2", cursor.getString(6));
					hmQuestionRowData.put("ans_3", cursor.getString(7));
					hmQuestionRowData.put("ans_4", cursor.getString(8));
					hmQuestionRowData.put("ans_5", cursor.getString(9));
					hmQuestionRowData.put("correct_ans", cursor.getString(10));
					Log.d("tag", "Titlt : " + hmQuestionRowData.get("index")
							+ "=>" + hmQuestionRowData.get("correct_ans"));
					cursor.moveToNext();
					alListOfQuestionDataContainer.add(hmQuestionRowData);
				}
				Log.d("tag", "Total Question : "
						+ alListOfQuestionDataContainer.size());
			}
		}
		return alListOfQuestionDataContainer;
	}

	public ArrayList<String> getTitleList() {
		ArrayList<String> alListOfTitle = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM Math", null);
		Log.d("tag", "Count : " + cursor.getCount());
		cursor.moveToFirst();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					alListOfTitle.add(cursor.getString(1));
					Log.d("tag", "Titlt : " + cursor.getString(1));
					cursor.moveToNext();
				}
			}
		}
		return alListOfTitle;
	}

	public ArrayList<String> getDescription(String title) {
		ArrayList<String> alListOfTitle = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"SELECT * FROM contentofcategories where name_categories='"
						+ title + "'", null);
		Log.d("tag", "Count : " + cursor.getCount());
		cursor.moveToFirst();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					alListOfTitle.add(cursor.getString(2));
					Log.d("tag", "Titlt : " + cursor.getString(2));
					cursor.moveToNext();
				}
			}
		}
		return alListOfTitle;
	}

	public void CopyDataBaseFromAsset() throws IOException {
		InputStream myInput = ctx.getAssets().open(DATABASE_NAME);
		String outFileName = getDatabasePath();
		File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
		if (!f.exists())
			f.mkdir();
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	// UPDATE Math SET option_a_ans = '00' where _id='1'
	public void insertAnswer(String table, String col, String colValue,
			String id) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] args = new String[] { id };
			ContentValues values = new ContentValues();
			values.put(col, colValue);
			int row = db.update(table, values, "_id=?", args);
			Log.d("tag", "No Of Row Update : " + row);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertAnswer1(String table, String col, String colValue,
			String id) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] args = new String[] { id };
			ContentValues values = new ContentValues();
			values.put(col, colValue);
			int row = db.update(table, values, "test=?", args);
			Log.d("tag", "No Of Row Update : " + row);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getDatabasePath() {
		return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
				+ DATABASE_NAME;
	}

	public SQLiteDatabase openDataBase() throws SQLException {
		File dbFile = ctx.getDatabasePath(DATABASE_NAME);

		if (!dbFile.exists()) {
			try {
				CopyDataBaseFromAsset();
				System.out.println("Copying sucess from Assets folder");
			} catch (IOException e) {
				throw new RuntimeException("Error creating source database", e);
			}
		}

		return SQLiteDatabase.openDatabase(dbFile.getPath(), null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS
						| SQLiteDatabase.CREATE_IF_NECESSARY);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
