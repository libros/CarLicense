package libra.spicy.vlicense.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import libra.spicy.vlicense.model.Answer;
import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseWork implements IQuestionDao{

	private Context context;
	private String class_;//����a��b��c
	DatabaseHelper databasehelper;
	private SQLiteDatabase database;
	
	public DatabaseWork(Context context, String class_) {
		super();
		this.context = context;
		this.class_ = class_;
		databasehelper = new DatabaseHelper(context);
	}

	public int findNumberOfQuestionsInAChapter(int pChapter) {//������� 0������û�鵽��
		// TODO Auto-generated method stub
		//�����ж�pQuestionId����id�Ƿ�Ϸ���
		if(pChapter<1||pChapter>DataBase_Fields.Chapter_allNum){
			return 0;
		}
		int NumberOfQuestionsInAChapter;
		database=databasehelper.openDatabase();
		String sql="select * from "+DataBase_Fields.mquestionTab+" where "+DataBase_Fields.mChapter+"=?";
		Cursor cursor=database.rawQuery(sql, new String[]{Integer.toString(pChapter)});
		NumberOfQuestionsInAChapter=cursor.getCount();
		database.close();
		return NumberOfQuestionsInAChapter;
	}

	
	public int findNumberOfQuestions() {
		// TODO Auto-generated method stub
		database=databasehelper.openDatabase();
		String sql="SELECT * FROM "+DataBase_Fields.mquestionTab;
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor!=null){
			int list_long = cursor.getCount();
			database.close();
			return list_long;
		}
		return 0;
	}

	public List<Question> findQuestionsInChapter(int pChapter) {
		// TODO Auto-generated method stub
		if(pChapter<1||pChapter>DataBase_Fields.Chapter_allNum){
			return null;
		}
		List<Question> list_question = null;
		database=databasehelper.openDatabase();
		String sql="select * from "+DataBase_Fields.mquestionTab+" where "+DataBase_Fields.mChapter+"=?";
		Cursor cursor=database.rawQuery(sql, new String[]{Integer.toString(pChapter)});
		if(cursor!=null){
			list_question=new ArrayList<Question>();
			Boolean is=cursor.moveToFirst();
			while(is){
				Question question=getQuestion(cursor);
				list_question.add(question);
				is=cursor.moveToNext();
			}
		  database.close();
		  return list_question;
		}	
		return null;
	}

	public List<Question> findStartQuestions(int pChapter, int pNextAmount) {
		// TODO Auto-generated method stub
		return null;
	}
    //�㶨
	public List<Question> findQuestions(int pQuestionId, int pNextAmount) {
		//�����ж�pQuestionId����id�Ƿ�Ϸ���
		if(pQuestionId<1||pQuestionId>DataBase_Fields.Question_allNum){
			return null;
		}
		List<Question> list_question = null;
		database=databasehelper.openDatabase();
		String sql="SELECT * FROM "+DataBase_Fields.mquestionTab;
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor!=null){
			int list_long = cursor.getCount();
			if(list_long>=(pQuestionId+pNextAmount)){
				list_question=new ArrayList<Question>(pNextAmount);
				Boolean is = cursor.moveToPosition(pQuestionId);
				if(is){
					for(int i=pQuestionId;i<pQuestionId+pNextAmount;i++){
						 Question question=getQuestion(cursor);
						 list_question.add(question);
						 cursor.moveToNext();
					}
				}
				database.close();
				return list_question;
			}else{
				//�����pQuestionIdΪ900ʱ��list_questionҲ�᷵��ֻ�Ǵ�СΪ0
				list_question=new ArrayList<Question>(list_long-pQuestionId);
				Boolean is = cursor.moveToPosition(pQuestionId);
				if(is){
					for(int i=pQuestionId;i<list_long;i++){
						 Question question=getQuestion(cursor);
						 list_question.add(question);
						 cursor.moveToNext();
					}
				}
				database.close();
				return list_question;
			}
		}else{
			//��ʾ�û����ݿ��ȡ����
		}				
		database.close();
		return null;
	}

	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection, boolean pWithinSameChapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean setLastVisitTime(int pQuestionId, Date pDate) {
		// TODO Auto-generated method stub
		return false;
	}

	//�㶨
	public int getNumberOfChapters() {
		// TODO Auto-generated method stub
		database=databasehelper.openDatabase();
		String sql="SELECT * FROM chapters";
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor!=null){
			int list_long = cursor.getCount();
			return list_long;
		}
		return 0;
	}
		
	//�㶨
	public List<Chapter> findAllChapters() {
		// TODO Auto-generated method stub
		List<Chapter> list_chapter = null;
		database=databasehelper.openDatabase();
		String sql="SELECT * FROM chapters";
		Cursor cursor=database.rawQuery(sql, null);
		if(cursor!=null){
			list_chapter=new ArrayList<Chapter>();
			Boolean is=cursor.moveToFirst();
			while (is) {
				String chapterName = cursor.getString(cursor.getColumnIndex("chapter"));
				int chapterNumber=cursor.getInt(cursor.getColumnIndex("_id"));
				Chapter chapter=new Chapter(chapterName, chapterNumber);
				list_chapter.add(chapter);
				is=cursor.moveToNext();
			}
			database.close();
			return list_chapter;
		}
		return null;
	}
	//�㶨
	public Chapter findChapterWithNumber(int number) {
		// TODO Auto-generated method stub
		Chapter chapter;
		database=databasehelper.openDatabase();
		String sql="SELECT * FROM chapters where _id=?";
		Cursor cursor=database.rawQuery(sql, new String[]{String.valueOf(number)});
		if(cursor!=null){
			cursor.moveToFirst();
			String chapterName = cursor.getString(cursor.getColumnIndex("chapter"));
			chapter=new Chapter(chapterName, number);
			return chapter;
		}
		return null;
	}

	public int getNumberOfMarkedQuestion(int pChapter) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfMarkedQuestion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Question> getMarkedQuestionsInChapter(int pChapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Question> getMarkedQuestionsInChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean markAsWrong(int pQuestionId, Date pNow) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * �����α�cursor�õ���Ӧ��һ��question
	 * @param cursor
	 * @return��question
	 */
	public Question getQuestion(Cursor cursor){
		 String mQuestionNumber = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestionNumber));
         String mQuestion = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mQuestion));
         String mImageFileName = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mImageFileName));
         String mAnswer_a = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_a));
         String mAnswer_b = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_b));
         String mAnswer_c = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_c));
         String mAnswer_d = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mAnswer_d));
         String mRightAnswer = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mRightAnswer));
         Answer[] mAnswers = null;
         
         if("A".equals(mRightAnswer)){
        	 mAnswers=new Answer[]{new Answer(mAnswer_a,true),new Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new Answer(mAnswer_d,false)};
         }else if("B".equals(mRightAnswer)){
        	 mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,true),new Answer(mAnswer_c,false),new Answer(mAnswer_d,false)};
         }else if("C".equals(mRightAnswer)){
        	 mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,false),new Answer(mAnswer_c,true),new Answer(mAnswer_d,false)};
         }else if("D".equals(mRightAnswer)){
        	 mAnswers=new Answer[]{new Answer(mAnswer_a,false),new Answer(mAnswer_b,false),new Answer(mAnswer_c,false),new Answer(mAnswer_d,true)};
			   
         }
         String mExplaination = cursor.getString(cursor.getColumnIndex(DataBase_Fields.mExplaination));
         int mChapter = cursor.getInt(cursor.getColumnIndex(DataBase_Fields.mChapter));
         Chapter chapter=findChapterWithNumber(mChapter);
         Question question = new Question(Integer.parseInt(mQuestionNumber), mImageFileName, mQuestion, mAnswers, mExplaination, chapter, 0,null);
		return question;
	}
	
	
	
	//class_c��1-7�£�class_b��1-8�£�class_a��1-7��9��

}
