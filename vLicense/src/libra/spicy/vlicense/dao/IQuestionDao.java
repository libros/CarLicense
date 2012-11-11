package libra.spicy.vlicense.dao;

import java.util.Date;
import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionDao {
	public enum LookDirection {
		Forward, Backward
	}

	/**
	 * ����ĳһ���½���Ŀ������
	 * 
	 * @param pChapter
	 *            �½ڱ��
	 * @return �½ڱ���µ���������
	 */
	public int findNumberOfQuestionsInAChapter(int pChapter);

	/**
	 * ���������½���Ŀ������
	 * 
	 * @return �������ݿ���������Ŀ������
	 */
	public int findNumberOfQuestions();

	/**
	 * �ҵ�ĳ�½����е���Ŀ������pChapterָ��ĳһ�½�
	 * 
	 * @param pChapter
	 *            ���ݿ��½ڵı��
	 * @return ����ָ���½ڵ�������б�
	 */
	public List<Question> findQuestionsInChapter(int pChapter);

	/**
	 * ��pChapter���дӵ�һ����ʼ���ҵ�����ȥ��nextAmount����Ŀ
	 * 
	 * @param pChapter
	 *            �½ڱ��
	 * @param pNextAmount
	 *            Ԥ��ȡ����
	 * @return ����pChapter�µ�һ����ʼ��Ԥ��ȡ�����������б��������pNextAmount,��ô�ͷ���ʣ�µ�
	 */
	public List<Question> findStartQuestions(int pChapter, int pNextAmount);

	/**
	 * ��ĳһ��pQuestionId��ʼ��*Ĭ�����*����pNextAmount�����⣬���ҷ��أ�����pQuestionId���Ǹ�Question��
	 * 
	 * @param pQuestionId
	 *            ��ʼ��Questionid
	 * @param pNextAmount
	 *            �趨������Ŀ������
	 * @return ��Ҫ�󷵻ص���Ŀ�б�
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);

	/**
	 * ��ĳһ��pQuestionId��ʼ������LookDirection��ֵѡ������ǰ/������pNextAmount�����⣬
	 * ���ҷ������List������pQuestionId���Ǹ�Question��Ĭ����ͬһ���½��в��ң� ���ͬ�½��е������������ж��پͷ��ض���
	 * 
	 * @param pQuestionId
	 * @param pNextAmount
	 * @param pLookDirection
	 *            ���ҵķ��򣬿�����ǰ�����
	 * @return ��Ҫ�󷵻���Ŀ���б�
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection);

	/*
	 * ͬ�ϣ����һ�����������true��ʾ����������pQuestionId��ͬһ�½ڵ��������⣬�������pNextAmount���ж��ٷ��ض���
	 * �������withinSameChapter Ϊfalse��������һ������һ��
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection, boolean pWithinSameChapter);

	/**
	 *  ����Question������ʱ��
	 * @param pQuestionId ���õ���Ŀ
	 * @param pDate ʱ��
	 * @return ���óɹ�����true
	 */
	public boolean setLastVisitTime(int pQuestionId, Date pDate);

	/**
	 *  ���Ҳ��ҷ������е��½ڶ���
	 * @return �������е��½ڶ���
	 */
	public List<Chapter> findAllChapters();

	/**
	 *  �����½�����
	 * @return ���ع��ж��ٸ��½�
	 */
	public int getNumberOfChapters();

	/**
	 *  �����½ڱ�ţ������һ�¾���1�����ҳ����½ڶ���
	 * @param number ���ҵ��½ڵı��
	 * @return ���ظ�����ŵ��½ڶ���
	 */
	public Chapter findChapterWithNumber(int number);

	/**
	 *  ��ȡ ĳһ�� ����ղص���Ŀ������
	 * @param pChapter ĳһ�½ڵı��
	 * @return ĳһ�±���ղص���Ŀ������
	 */
	public int getNumberOfMarkedQuestion(int pChapter);

	/**
	 *  ��ȡ���б���ղص���Ŀ������
	 * @param pChapter ĳһ�½ڵı��
	 * @return ĳһ�±���ղص���Ŀ������
	 */
	public int getNumberOfMarkedQuestion();

	/**
	 * 	��ȡĳһ�½������ղص���Ŀ
	 * @param pChapter �½ڱ��
	 * @return ��ĳһ�½ڱ���ղص���Ŀ
	 */
	public List<Question> getMarkedQuestionsInChapter(int pChapter);

	/**
	 *  ��ȡ�����½������ղص���Ŀ
	 * @return �����½������ղص���Ŀ
	 */
	public List<Question> getMarkedQuestionsInChapter();

	/**
	 *  ��pQuestionId�������������һ,ͬʱ����������ʱ��
	 * @param pQuestionId Ҫָ��Ϊ�������Ŀ���
	 * @param pNow ������ʱ��
	 * @return ���óɹ�����true������false
	 */
	public boolean markAsWrong(int pQuestionId, Date pNow);

}
