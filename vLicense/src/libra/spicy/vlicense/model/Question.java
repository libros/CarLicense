package libra.spicy.vlicense.model;

public class Question {
	// 题目标号，应该是题目的id，唯一的主键
	private int mQuestionId;

	// 题目的图片名，如果没有就为null
	private String mImageFileName;

	// 题目的文本正文
	private String mQuestion;

	// 题目对应的选项
	private Answer[] mAnswers;

	// 题目详解，这个题目的原理
	private String mExplaination;

	// 问题所在的章节
	private Chapter mChapter;

	// 该题目曾经做错过的次数
	private int mWrongTimes;

	// 分类，大型、小型、中型车对应的车本
	private Classification classification;

	public Question(int mQuestionNumber, String mImageFileName,
			String mQuestion, Answer[] mAnswers, String mExplaination,
			Chapter mChapter, int mWrongTimes, Classification classification) {
		super();
		this.mQuestionId = mQuestionNumber;
		this.mImageFileName = mImageFileName;
		this.mQuestion = mQuestion;
		this.mAnswers = mAnswers;
		this.mExplaination = mExplaination;
		this.mChapter = mChapter;
		this.mWrongTimes = mWrongTimes;
		this.classification = classification;
	}

	public Answer[] getAnswers() {
		return mAnswers;
	}

	public void setAnswers(Answer[] mAnswers) {
		this.mAnswers = mAnswers;
	}

	public int getWrongTimes() {
		return mWrongTimes;
	}

	public void setWrongTimes(int wrongTimes) {
		this.mWrongTimes = wrongTimes;
	}

	public int getQuestionNumber() {
		return mQuestionId;
	}

	public void setQuestionNumber(int mQuestionNumber) {
		this.mQuestionId = mQuestionNumber;
	}

	public String getImageFileName() {
		return mImageFileName;
	}

	public void setImageFileName(String mImageFileName) {
		this.mImageFileName = mImageFileName;
	}

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public String getExplaination() {
		return mExplaination;
	}

	public void setExplaination(String mExplaination) {
		this.mExplaination = mExplaination;
	}

	public Chapter getChapter() {
		return mChapter;
	}

	public void setChapter(Chapter mChapter) {
		this.mChapter = mChapter;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public static enum Classification {
		A, B, C
	}

}
