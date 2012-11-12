package libra.spicy.vlicense.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import libra.spicy.vlicense.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
	public static final String DB_NAME = "jz.db";// 数据库的名字
	public static final String PACKAGE_NAME = "kaojz";// 数据库在sd卡上的文件夹名字
	private String DATABASE_PATH;// 数据库在sd卡上的路径
	private String databaseFilename;// 获得jz.db文件的绝对路径
	private boolean mDatabaseReady;
	private static DatabaseHelper self;

	private DatabaseHelper() {
		super();
		// 得到sd卡路径
		String SdPath = android.os.Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		// 数据库在sd卡上的文件夹路径
		DATABASE_PATH = SdPath + "/" + PACKAGE_NAME;
		// 获得jz.db文件的绝对路径
		databaseFilename = DATABASE_PATH + "/" + DB_NAME;
	}

	/**
	 * 得到databaseHelper
	 * @return
	 */
	public static DatabaseHelper getDatabaseHelper() {		
		if (self == null) {
			self = new DatabaseHelper();
		}
		return self;
	}

	/**
	 * 打开可读数据库，如果数据库不存在，就创建它
	 * 
	 * @param context
	 *            Activity Context
	 * @return database
	 */
	public SQLiteDatabase openReadableDatabase(Context context) {
//		System.out.println("openReadableDatabase");
		if (!mDatabaseReady) {
			createDatabase(context);
		}
		return SQLiteDatabase.openDatabase(databaseFilename, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	/**
	 * 打开可写数据库，如果数据库不存在，就创建它
	 * @param context
	 *        Activity Context
	 * @return database
	 */
	public SQLiteDatabase openReadWriteDatabase(Context context) {
		if (!mDatabaseReady) {
			createDatabase(context);
		}
		return SQLiteDatabase.openDatabase(databaseFilename, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	/**
	 * 打开数据库方法
	 * @param databaseFilename
	 *        :数据库在sd卡上的绝对路径
	 * @return：database对象
	 */
	public void createDatabase(Context context) {
		System.out.println("create Database");
		// 首先判断是否安装sd卡！
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			try {
				File dir = new File(DATABASE_PATH);
				// System.out.println(DATABASE_PATH);
				// 如果/sdcard/kaojz目录不存在，则创建这个目录
				if (!dir.exists())
					dir.mkdir();
				if (!dir.exists())
					System.out.println("目录还是没有建立！");
				// 如果在/sdcard/kaojz目录中不存在jz.db文件，则从res\raw目录中复制这个文件到SD卡的目录（/sdcard/kaojz）
				if (!(new File(databaseFilename)).exists()) {
					System.out.println("拷贝数据库文件");
					// 获得封装jz.db文件的InputStream对象
					InputStream is = context.getResources().openRawResource(R.raw.jz);
					FileOutputStream fos = new FileOutputStream(databaseFilename);
					byte[] buffer = new byte[8192];
					int count = 0;
					// 开始复制jz.db文件
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count);
					}
					fos.close();
					is.close();
				}
				mDatabaseReady = true;
				// 打开/sdcard/kaojz目录中的jz.db文件
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 提示用户安装sd卡！
			throw new IllegalStateException("悲剧了，用户没有存储卡~~");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// /**
	// * 根据游标cursor得到相应的一个question
	// * @param cursor
	// * @return：question
	// */
	// public Question getQuestion(Cursor cursor){
	// String mQuestionNumber =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestionNumber));
	// String mQuestion =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestion));
	// String mImageFileName =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mImageFileName));
	// String mAnswer_a =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_a));
	// String mAnswer_b =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_b));
	// String mAnswer_c =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_c));
	// String mAnswer_d =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_d));
	// String mRightAnswer =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mRightAnswer));
	// Answer[] mAnswers = null;
	// switch (mRightAnswer) {
	// case "A":
	// mAnswers=new Answer[]{new Answer(mAnswer_a,true),new
	// Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new
	// Answer(mAnswer_d,false)};
	// break;
	// case "B":
	// mAnswers=new Answer[]{new Answer(mAnswer_a,false),new
	// Answer(mAnswer_b,true),new Answer(mAnswer_c,false),new
	// Answer(mAnswer_d,false)};
	// break;
	// case "C":
	// mAnswers=new Answer[]{new Answer(mAnswer_a,false),new
	// Answer(mAnswer_b,false),new Answer(mAnswer_c,true),new
	// Answer(mAnswer_d,false)};
	// break;
	// case "D":
	// mAnswers=new Answer[]{new Answer(mAnswer_a,false),new
	// Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new
	// Answer(mAnswer_d,true)};
	// break;
	// }
	// String mExplaination =
	// cursor.getString(cursor.getColumnIndex(DataBase_Fields.mExplaination));
	// int mChapter =
	// cursor.getInt(cursor.getColumnIndex(DataBase_Fields.mChapter));
	//
	// Chapter chapter=new Chapter(null,mChapter);
	// Question question = new Question(Integer.parseInt(mQuestionNumber),
	// mImageFileName, mQuestion, mAnswers, mExplaination, chapter, 0,null);
	// return question;
	// }
	//

}

// /**
// * 得到所有题目list<question>
// * @param paramInt
// * @return
// */
// public ArrayList getListQuestions(){
// //打开数据库，并得到database对象
// database=openDatabase();
// String sql = "SELECT * FROM "+DataBase_Fields.mquestionTab;
// Cursor cursor = database.rawQuery(sql,null);
// ArrayList arrayList = null;
// if (cursor != null)
// {
// int list_long = cursor.getCount();
// // System.out.println(list_long+"cursor的count");
// arrayList = new ArrayList(list_long);
// Boolean is = cursor.moveToFirst();
// while(is){
// Question question=getQuestion(cursor);
// arrayList.add(question);
// is = cursor.moveToNext();
// System.out.println(is+"读取一次！");
// }
// }
// database.close();
// return arrayList;
// }
