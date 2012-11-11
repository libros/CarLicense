package libra.spicy.vlicense.controller;

import java.util.List;

import libra.spicy.vlicense.dao.DatabaseWork;
import libra.spicy.vlicense.model.Question;
import libra.spicy.vlicense.utils.LicenseConstants.Chapter;
import android.content.Context;

public class QuestionActivityController {
	private static final int UNINITIATED_POSITION = -1;
	private static final int UNINITIATED_PAGE_NUMBER = -1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	private DatabaseWork mDatabase;
	private Context mContext;
	private List<Question> mQuestionList;
	private int mStartPosition;
	private int mEndPosition;
	private int mPageSize;
	private int mPageNumber;
	private Chapter mChapter;

	/**
	 * ����QuestionActivity�Ŀ�����
	 * 
	 * @param pContext
	 *            Activity��Context����
	 * @param pCarType
	 *            �û�ѡ��ĳ���������
	 */
	public QuestionActivityController(Context pContext, Chapter pChapter,
			String pCarType) {
		this.mContext = pContext;
		this.mDatabase = DatabaseWork.getInstance(mContext, pCarType);
		this.mStartPosition = UNINITIATED_POSITION;
		this.mEndPosition = UNINITIATED_POSITION;
		this.mChapter = pChapter;
		this.mPageSize = DEFAULT_PAGE_SIZE;
		this.mPageNumber = UNINITIATED_PAGE_NUMBER;
	}

	public QuestionActivityController(Context pContext, Chapter pChapter) {
		this(pContext, pChapter, "C");
	}

	public int numberOfQueston() {
		return this.mDatabase.findNumberOfQuestionsInAChapter(mChapter
				.getChapterNumber());
	}

	public Question getQuestionAtPosition(int pPosition) {
		Question resultQuestion = null;

		if (mStartPosition == UNINITIATED_POSITION) {
			// ��ȡ��һҳ
			preloadBuffer(0);
		}

		if (pPosition < this.mEndPosition && pPosition >= this.mStartPosition) {
			// ���Ѱ�ҵ�λ�����Ѿ���ȡ�У���ֱ�ӷ���
			return this.mQuestionList.get(pPosition % mPageSize);
		} else {
			// ���Ѱ�ҵ�λ�ò��ڻ����б��У���ôȥ���ݿ����

			if (pPosition >= mEndPosition) {
				// �������ҳ
				int page = (pPosition - mEndPosition) / mPageSize + 1;
				preloadBuffer(mPageNumber + page);
				resultQuestion = mQuestionList.get(pPosition - mStartPosition);
			} else {
				//
				int page = (mStartPosition - pPosition) / mPageSize + 1;
				preloadBuffer(mPageNumber - page);
				resultQuestion = mQuestionList.get(pPosition - mStartPosition);
			}
//			return getQuestionAtPosition(pPosition % mPageSize);
		}
		return resultQuestion;

	}

	private void preloadBuffer(int pPageNumber) {
		this.mQuestionList = mDatabase
				.findQuestionListInChapterWithPageNumberAndPagesize(
						mChapter.getChapterNumber(), pPageNumber, mPageSize);

		this.mStartPosition = pPageNumber * mPageSize;
		this.mEndPosition = mStartPosition + mQuestionList.size();
		this.mPageNumber = pPageNumber;
	}

}
