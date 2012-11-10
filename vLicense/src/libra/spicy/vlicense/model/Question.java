package libra.spicy.vlicense.model;

public class Question {
	// ��Ŀ��ţ�Ӧ������Ŀ��id��Ψһ������
	private int mQuestionId;

	// ��Ŀ��ͼƬ�������û�о�Ϊnull
	private String mImageFileName;

	// ��Ŀ���ı�����
	private String mQuestion;

	// ��Ŀ��Ӧ��ѡ��
	private Answer[] mAnswers;

	// ��Ŀ��⣬�����Ŀ��ԭ��
	private String mExplaination;

	// �������ڵ��½�
	private Chapter mChapter;

	// ����Ŀ����������Ĵ���
	private int mWrongTimes;

	// ���࣬���͡�С�͡����ͳ���Ӧ�ĳ���
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
