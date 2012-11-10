package libra.spicy.vlicense.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import libra.spicy.vlicense.R;
import libra.spicy.vlicense.model.Answer;
import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
	private Context context;
	private SQLiteDatabase database;
    public static final String DB_NAME = "jz.db";//数据库的名字
    public static final String PACKAGE_NAME = "kaojz";//数据库在sd卡上的文件夹名字
    private String DATABASE_PATH;//数据库在sd卡上的路径
    private String databaseFilename;//获得jz.db文件的绝对路径
    
	public DatabaseHelper(Context context) {
		super();
		this.context = context;
		//得到sd卡路径
		String SdPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();	
		//数据库在sd卡上的文件夹路径
		DATABASE_PATH=SdPath+"/"+PACKAGE_NAME;
		//获得jz.db文件的绝对路径
		databaseFilename= DATABASE_PATH + "/" + DB_NAME;
	}
	
	/**
	 * 打开数据库方法
	 * @param databaseFilename:数据库在sd卡上的绝对路径
	 * @return：database对象
	 */
	public SQLiteDatabase openDatabase(){
		//首先判断是否安装sd卡！
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			try
			  {
				File dir = new File(DATABASE_PATH);
				System.out.println(DATABASE_PATH);
				// 如果/sdcard/kaojz目录不存在，则创建这个目录
				if (!dir.exists())dir.mkdir();
				if(!dir.exists())System.out.println("目录还是没有建立！");
				// 如果在/sdcard/kaojz目录中不存在jz.db文件，则从res\raw目录中复制这个文件到SD卡的目录（/sdcard/kaojz）
				if (!(new File(databaseFilename)).exists())
				{
					// 获得封装jz.db文件的InputStream对象
					InputStream is = context.getResources().openRawResource(R.raw.jz);
					FileOutputStream fos = new FileOutputStream(databaseFilename);
					byte[] buffer = new byte[8192];
					int count = 0;
					//开始复制jz.db文件
					while ((count = is.read(buffer)) > 0)
					{
						fos.write(buffer, 0, count);
					}
					fos.close();
					is.close();
				}
				// 打开/sdcard/kaojz目录中的jz.db文件
				SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
				return database;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}else{
			//提示用户安装sd卡！
		}
	  return null;
	}


//	/**
//	 * 根据游标cursor得到相应的一个question
//	 * @param cursor
//	 * @return：question
//	 */
//	public Question getQuestion(Cursor cursor){
//		 String mQuestionNumber = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestionNumber));
//         String mQuestion = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestion));
//         String mImageFileName = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mImageFileName));
//         String mAnswer_a = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_a));
//         String mAnswer_b = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_b));
//         String mAnswer_c = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_c));
//         String mAnswer_d = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_d));
//         String mRightAnswer = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mRightAnswer));
//         Answer[] mAnswers = null;
//         switch (mRightAnswer) {
//		   case "A":
//			    mAnswers=new Answer[]{new Answer(mAnswer_a,true),new Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new Answer(mAnswer_d,false)};
//			    break;
//		   case "B":
//			    mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,true),new Answer(mAnswer_c,false),new Answer(mAnswer_d,false)};
//			    break;
//		   case "C":
//			    mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,false),new Answer(mAnswer_c,true),new Answer(mAnswer_d,false)};
//			    break;
//		   case "D":
//			    mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new Answer(mAnswer_d,true)};
//			    break;
//		}
//         String mExplaination = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mExplaination));
//         int mChapter = cursor.getInt(cursor.getColumnIndex(DataBase_Fields.mChapter));
//         
//         Chapter chapter=new Chapter(null,mChapter);
//         Question question = new Question(Integer.parseInt(mQuestionNumber), mImageFileName, mQuestion, mAnswers, mExplaination, chapter, 0,null);
//		return question;
//	}
//	


}

///**
//* 得到所有题目list<question>
//* @param paramInt
//* @return
//*/
//public  ArrayList getListQuestions(){
//	//打开数据库，并得到database对象
//	database=openDatabase();
//   String sql = "SELECT * FROM "+DataBase_Fields.mquestionTab;
//   Cursor cursor = database.rawQuery(sql,null);
//   ArrayList arrayList = null;
//   if (cursor != null)
//   {
//     int list_long = cursor.getCount();
////     System.out.println(list_long+"cursor的count");
//     arrayList = new ArrayList(list_long);
//     Boolean is = cursor.moveToFirst();
//     while(is){
//   	  Question question=getQuestion(cursor);
//   	  arrayList.add(question);
//         is = cursor.moveToNext();
//         System.out.println(is+"读取一次！");
//     }
//   }
//     database.close();
//     return arrayList;
// }
