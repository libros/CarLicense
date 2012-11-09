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

	public String getQuestionNumber() {
		return mQuestionNumber;
	}

	public void setQuestionNumber(String mQuestionNumber) {
		this.mQuestionNumber = mQuestionNumber;
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

	public String[] getAnswers() {
		return mAnswers;
	}

	public void setAnswers(String[] mAnswers) {
		this.mAnswers = mAnswers;
	}

	public String getRightAnswer() {
		return mRightAnswer;
	}

	public void setRightAnswer(String mRightAnswer) {
		this.mRightAnswer = mRightAnswer;
	}

	public String getExplaination() {
		return mExplaination;
	}

	public void setExplaination(String mExplaination) {
		this.mExplaination = mExplaination;
	}

	public int getChapter() {
		return mChapter;
	}

	public void setChapter(int mChapter) {
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
