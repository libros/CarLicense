package libra.spicy.vlicense.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import libra.spicy.vlicense.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
	public static final String DB_NAME = "jz.db";// ���ݿ������
	public static final String PACKAGE_NAME = "kaojz";// ���ݿ���sd���ϵ��ļ�������
	private String DATABASE_PATH;// ���ݿ���sd���ϵ�·��
	private String databaseFilename;// ���jz.db�ļ��ľ���·��
	private boolean mDatabaseReady;
	private static DatabaseHelper self;

	private DatabaseHelper() {
		super();
		// �õ�sd��·��
		String SdPath = android.os.Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		// ���ݿ���sd���ϵ��ļ���·��
		DATABASE_PATH = SdPath + "/" + PACKAGE_NAME;
		// ���jz.db�ļ��ľ���·��
		databaseFilename = DATABASE_PATH + "/" + DB_NAME;
	}

	/**
	 * �õ�databaseHelper
	 * @return
	 */
	public static DatabaseHelper getDatabaseHelper() {		
		if (self == null) {
			self = new DatabaseHelper();
		}
		return self;
	}

	/**
	 * �򿪿ɶ����ݿ⣬������ݿⲻ���ڣ��ʹ�����
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
	 * �򿪿�д���ݿ⣬������ݿⲻ���ڣ��ʹ�����
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
	 * �����ݿⷽ��
	 * @param databaseFilename
	 *        :���ݿ���sd���ϵľ���·��
	 * @return��database����
	 */
	public void createDatabase(Context context) {
		System.out.println("create Database");
		// �����ж��Ƿ�װsd����
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			try {
				File dir = new File(DATABASE_PATH);
				// System.out.println(DATABASE_PATH);
				// ���/sdcard/kaojzĿ¼�����ڣ��򴴽����Ŀ¼
				if (!dir.exists())
					dir.mkdir();
				if (!dir.exists())
					System.out.println("Ŀ¼����û�н�����");
				// �����/sdcard/kaojzĿ¼�в�����jz.db�ļ������res\rawĿ¼�и�������ļ���SD����Ŀ¼��/sdcard/kaojz��
				if (!(new File(databaseFilename)).exists()) {
					System.out.println("�������ݿ��ļ�");
					// ��÷�װjz.db�ļ���InputStream����
					InputStream is = context.getResources().openRawResource(R.raw.jz);
					FileOutputStream fos = new FileOutputStream(databaseFilename);
					byte[] buffer = new byte[8192];
					int count = 0;
					// ��ʼ����jz.db�ļ�
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count);
					}
					fos.close();
					is.close();
				}
				mDatabaseReady = true;
				// ��/sdcard/kaojzĿ¼�е�jz.db�ļ�
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// ��ʾ�û���װsd����
			throw new IllegalStateException("�����ˣ��û�û�д洢��~~");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// /**
	// * �����α�cursor�õ���Ӧ��һ��question
	// * @param cursor
	// * @return��question
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
// * �õ�������Ŀlist<question>
// * @param paramInt
// * @return
// */
// public ArrayList getListQuestions(){
// //�����ݿ⣬���õ�database����
// database=openDatabase();
// String sql = "SELECT * FROM "+DataBase_Fields.mquestionTab;
// Cursor cursor = database.rawQuery(sql,null);
// ArrayList arrayList = null;
// if (cursor != null)
// {
// int list_long = cursor.getCount();
// // System.out.println(list_long+"cursor��count");
// arrayList = new ArrayList(list_long);
// Boolean is = cursor.moveToFirst();
// while(is){
// Question question=getQuestion(cursor);
// arrayList.add(question);
// is = cursor.moveToNext();
// System.out.println(is+"��ȡһ�Σ�");
// }
// }
// database.close();
// return arrayList;
// }
