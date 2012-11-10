package libra.spicy.vlicense.dao;

import java.util.Date;
import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionDao {
	public enum LookDirection {
		Forward, Backward
	}

	// ����ĳһ���½���Ŀ������
	public int findNumberOfQuestionsInAChapter(int pChapter);

	// ���������½���Ŀ������
	public int findNumberOfQuestions();

	// �ҵ�ĳ�½����е���Ŀ������pChapterָ��ĳһ�½�
	public List<Question> findQuestionsInChapter(int pChapter);
	
	
	//��pChapter���дӵ�һ����ʼ���ҵ�����ȥ��nextAmount����Ŀ
	public List<Question> findStartQuestions(int pChapter,int pNextAmount);

	// ��ĳһ��pQuestionId��ʼ��*Ĭ�����*����pNextAmount�����⣬���ҷ��أ�����pQuestionId���Ǹ�Question��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);

	// ��ĳһ��pQuestionId��ʼ������LookDirection��ֵѡ������ǰ/������pNextAmount�����⣬
	// ���ҷ������List������pQuestionId���Ǹ�Question��Ĭ����ͬһ���½��в��ң�
	// ���ͬ�½��е������������ж��پͷ��ض���
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection);

	// ͬ�ϣ����һ�����������true��ʾ����������pQuestionId��ͬһ�½ڵ��������⣬�������pNextAmount���ж��ٷ��ض���
	// �������withinSameChapter Ϊfalse��������һ������һ��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection, boolean pWithinSameChapter);

	// ����Question������ʱ��
	public boolean setLastVisitTime(int pQuestionId, Date pDate);

	// ���Ҳ��ҷ������е��½ڶ���
	public List<Chapter> findAllChapters();

	// �����½�����
	public int getNumberOfChapters();

	// �����½ڱ�ţ������һ�¾���1�����ҳ����½ڶ���
	public Chapter findChapterWithNumber(int number);

	// ��������������ȡ ĳһ��/ȫ�� ����ղص���Ŀ������
	public int getNumberOfMarkedQuestion(int pChapter);

	public int getNumberOfMarkedQuestion();

	// ��ȡĳһ�½������ղص���Ŀ
	public List<Question> getMarkedQuestionsInChapter(int pChapter);

	// ��ȡ�����½������ղص���Ŀ
	public List<Question> getMarkedQuestionsInChapter();

	// ��pQuestionId�������������һ,ͬʱ����������ʱ��
	public boolean markAsWrong(int pQuestionId, Date pNow);

}
