package libra.spicy.vlicense.model;

public class Question {
	private String mQuestionNumber;
	private String mImageFileName;
	private String mQuestion;
	private String[] mAnswers;
	private String mRightAnswer;
	private String mExplaination;
	private int mChapter;
	private boolean isWrong;
	private Classification classification;

	public Question(String mQuestionNumber, String mImageFileName,
			String mQuestion, String[] mAnswers, String mRightAnswer,
			String mExplaination, int mChapter, boolean isWrong,
			Classification classification) {

		super();
		this.mQuestionNumber = mQuestionNumber;
		this.mImageFileName = mImageFileName;
		this.mQuestion = mQuestion;
		this.mAnswers = mAnswers;
		this.mRightAnswer = mRightAnswer;
		this.mExplaination = mExplaination;
		this.mChapter = mChapter;
		this.isWrong = isWrong;
		this.classification = classification;
	}

	public String getmQuestionNumber() {
		return mQuestionNumber;
	}

	public void setmQuestionNumber(String mQuestionNumber) {
		this.mQuestionNumber = mQuestionNumber;
	}

	public String getmImageFileName() {
		return mImageFileName;
	}

	public void setmImageFileName(String mImageFileName) {
		this.mImageFileName = mImageFileName;
	}

	public String getmQuestion() {
		return mQuestion;
	}

	public void setmQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public String[] getmAnswers() {
		return mAnswers;
	}

	public void setmAnswers(String[] mAnswers) {
		this.mAnswers = mAnswers;
	}

	public String getmRightAnswer() {
		return mRightAnswer;
	}

	public void setmRightAnswer(String mRightAnswer) {
		this.mRightAnswer = mRightAnswer;
	}

	public String getmExplaination() {
		return mExplaination;
	}

	public void setmExplaination(String mExplaination) {
		this.mExplaination = mExplaination;
	}

	public int getmChapter() {
		return mChapter;
	}

	public void setmChapter(int mChapter) {
		this.mChapter = mChapter;
	}

	public boolean isWrong() {
		return isWrong;
	}

	public void setWrong(boolean isWrong) {
		this.isWrong = isWrong;
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
