package libra.spicy.vlicense.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import libra.spicy.vlicense.model.Answer;
import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseWork implements IQuestionDao {
	private static DatabaseWork self;
	private Context context;
	private String class_;// 车型a、b、c
	DatabaseHelper databasehelper;

	private DatabaseWork(Context context, String class_) {
		super();
		this.context = context;
		this.class_ = class_;
		databasehelper = DatabaseHelper.getDatabaseHelper();
	}

	public static DatabaseWork getInstance(Context pContext, String class_) {
		if (self == null) {
			self = new DatabaseWork(pContext, class_);
		}
		return self;
	}

	public int findNumberOfQuestionsInAChapter(int pChapter) {// 如果返回 0，就是没查到！
		// // TODO Auto-generated method stub
		// // 首先判断pQuestionId试题id是否合法！
		// if (pChapter < 1 || pChapter > DataBase_Fields.Chapter_allNum) {
		// return 0;
		// }
		int NumberOfQuestionsInAChapter;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "select * from " + DataBase_Fields.mquestionTab
				+ " where " + DataBase_Fields.mChapter + "=?";
		Cursor cursor = database.rawQuery(sql,
				new String[] { Integer.toString(pChapter) });
		NumberOfQuestionsInAChapter = cursor.getCount();
		database.close();
		return NumberOfQuestionsInAChapter;
	}

	public int findNumberOfQuestions() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "SELECT * FROM " + DataBase_Fields.mquestionTab;
		Cursor cursor = database.rawQuery(sql, null);
		int list_long = 0;
		if (cursor != null) {
			list_long = cursor.getCount();
		}
		database.close();
		return list_long;
	}

	public List<Question> findQuestionsInChapter(int pChapter) {
		// TODO Auto-generated method stub
		if (pChapter < 1 || pChapter > DataBase_Fields.Chapter_allNum) {
			return null;
		}
		List<Question> list_question = null;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "select * from " + DataBase_Fields.mquestionTab
				+ " where " + DataBase_Fields.mChapter + "=?";
		Cursor cursor = database.rawQuery(sql,
				new String[] { Integer.toString(pChapter) });
		if (cursor != null) {
			list_question = new ArrayList<Question>();
			Boolean is = cursor.moveToFirst();
			while (is) {
				Question question = getQuestion(cursor);
				list_question.add(question);
				is = cursor.moveToNext();
			}
		}
		database.close();
		return list_question;
	}

	public List<Question> findStartQuestions(int pChapter, int pNextAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	// 搞定
	public List<Question> findQuestions(int pQuestionId, int pNextAmount) {
		// 首先判断pQuestionId试题id是否合法！
		if (pQuestionId < 1 || pQuestionId > DataBase_Fields.Question_allNum) {
			return null;
		}
		List<Question> list_question = null;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "SELECT * FROM " + DataBase_Fields.mquestionTab;
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {
			int list_long = cursor.getCount();
			if (list_long >= (pQuestionId + pNextAmount)) {
				list_question = new ArrayList<Question>(pNextAmount);
				Boolean is = cursor.moveToPosition(pQuestionId);
				if (is) {
					for (int i = pQuestionId; i < pQuestionId + pNextAmount; i++) {
						Question question = getQuestion(cursor);
						list_question.add(question);
						cursor.moveToNext();
					}
				}
				database.close();
				return list_question;
			} else {
				// 如果当pQuestionId为900时，list_question也会返回只是大小为0
				list_question = new ArrayList<Question>(list_long - pQuestionId);
				Boolean is = cursor.moveToPosition(pQuestionId);
				if (is) {
					for (int i = pQuestionId; i < list_long; i++) {
						Question question = getQuestion(cursor);
						list_question.add(question);
						cursor.moveToNext();
					}
				}
				database.close();
				return list_question;
			}
		} else {
			// 提示用户数据库读取错误！
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

	// 搞定
	public int getNumberOfChapters() {
		int list_long = 0;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "SELECT * FROM chapters";
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {
			list_long = cursor.getCount();
		}
		database.close();
		return list_long;
	}

	// 搞定
	public List<Chapter> findAllChapters() {
		// TODO Auto-generated method stub
		List<Chapter> list_chapter = null;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "SELECT * FROM chapters";
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {
			list_chapter = new ArrayList<Chapter>();
			Boolean is = cursor.moveToFirst();
			while (is) {
				String chapterName = cursor.getString(cursor
						.getColumnIndex("chapter"));
				int chapterNumber = cursor.getInt(cursor.getColumnIndex("_id"));
				Chapter chapter = new Chapter(chapterName, chapterNumber);
				list_chapter.add(chapter);
				is = cursor.moveToNext();
			}
		}
		database.close();
		return list_chapter;
	}

	// 搞定
	public Chapter findChapterWithNumber(int number) {
		// TODO Auto-generated method stub
		Chapter chapter = null;
		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = "SELECT * FROM chapters where _id=?";
		Cursor cursor = database.rawQuery(sql,
				new String[] { String.valueOf(number) });
		if (cursor != null) {
			cursor.moveToFirst();
			String chapterName = cursor.getString(cursor
					.getColumnIndex("chapter"));
			chapter = new Chapter(chapterName, number);
		}
		database.close();
		return chapter;
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
	 * 根据游标cursor得到相应的一个question
	 * 
	 * @param cursor
	 * @return：question
	 */
	public Question getQuestion(Cursor cursor) {

		String mQuestionNumber = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mQuestionNumber));
		String mQuestion = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mQuestion));
		String mImageFileName = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mImageFileName));
		String mAnswer_a = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mAnswer_a));
		String mAnswer_b = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mAnswer_b));
		String mAnswer_c = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mAnswer_c));
		String mAnswer_d = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mAnswer_d));
		String mRightAnswer = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mRightAnswer));

		String answerString[] = new String[] { mAnswer_a, mAnswer_b, mAnswer_c,
				mAnswer_d };

		ArrayList<Answer> answers = new ArrayList<Answer>();

		for (int i = 0; i < answerString.length; i++) {
			String answer = answerString[i];

			if (answer != null && answer.trim().length() > 0) {
				Answer theAnswer = new Answer(answerString[i], false);

				if ("A".equalsIgnoreCase(mRightAnswer) && i == 0) {
					theAnswer.setCorrect(true);
				} else if ("B".equalsIgnoreCase(mRightAnswer) && i == 1) {
					theAnswer.setCorrect(true);
				} else if ("C".equalsIgnoreCase(mRightAnswer) && i == 2) {
					theAnswer.setCorrect(true);
				} else if ("D".equalsIgnoreCase(mRightAnswer) && i == 3) {
					theAnswer.setCorrect(true);
				}
				answers.add(theAnswer);
			}

		}

//		Answer[] mAnswers = null;
		/*
		 * 
		 * if ("A".equals(mRightAnswer)) { mAnswers = new Answer[] { new
		 * Answer(mAnswer_a, true), new Answer(mAnswer_b, false), new
		 * Answer(mAnswer_c, false), new Answer(mAnswer_d, false) };
		 * 
		 * } else if ("B".equals(mRightAnswer)) { mAnswers = new Answer[] { new
		 * Answer(mAnswer_a, false), new Answer(mAnswer_b, true), new
		 * Answer(mAnswer_c, false), new Answer(mAnswer_d, false) }; } else if
		 * ("C".equals(mRightAnswer)) { mAnswers = new Answer[] { new
		 * Answer(mAnswer_a, false), new Answer(mAnswer_b, false), new
		 * Answer(mAnswer_c, true), new Answer(mAnswer_d, false) }; } else if
		 * ("D".equals(mRightAnswer)) { mAnswers = new Answer[] { new
		 * Answer(mAnswer_a, false), new Answer(mAnswer_b, false), new
		 * Answer(mAnswer_c, false), new Answer(mAnswer_d, true) };
		 * 
		 * }
		 */String mExplaination = cursor.getString(cursor
				.getColumnIndex(DataBase_Fields.mExplaination));
		int mChapter = cursor.getInt(cursor
				.getColumnIndex(DataBase_Fields.mChapter));
		Chapter chapter = findChapterWithNumber(mChapter);
		Question question = new Question(Integer.parseInt(mQuestionNumber),
				mImageFileName, mQuestion, answers.toArray(new Answer[] {}),
				mExplaination, chapter, 0, null);
		return question;
	}

	public List<Question> findQuestionListInChapterWithPageNumberAndPagesize(
			int pChapter, int pPageNumber, int pPageSize) {
		System.out
				.println("findQuestionListInChapterWithPageNumberAndPagesize");

		SQLiteDatabase database = databasehelper
				.openReadableDatabase(this.context);
		String sql = null;
		Cursor cursor = null;

		if (pChapter == 0) {
			sql = "select * from questions where limit ?,?";
			cursor = database.rawQuery(sql,
					new String[] { Integer.toString(pPageNumber * pPageSize),
							Integer.toString(pPageSize) });

		} else {
			sql = "select * from questions where chapter_id = ? limit ?,?";
			cursor = database.rawQuery(
					sql,
					new String[] { Integer.toString(pChapter),
							Integer.toString(pPageNumber * pPageSize),
							Integer.toString(pPageSize) });

		}

		ArrayList<Question> questionList = null;
		if (cursor != null) {
			questionList = new ArrayList<Question>();
			while (cursor.moveToNext()) {
				Question question = getQuestion(cursor);
				questionList.add(question);
			}
		}

		database.close();
		database = null;
		return questionList;
	}
	// class_c是1-7章；class_b是1-8章；class_a是1-7、9章

}
