package libra.spicy.vlicense.controller;

import java.util.Date;
import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionController {
	
	//����ĳһ���½���Ŀ������
	public int findNumberOfQuestionsInAChapter(int pChapter);
	//���������½���Ŀ������
	public int findNumberOfQuestions();
	//�ҵ�ĳ�½����е���Ŀ������pChapterָ��ĳһ�½�
	public List<Question> findQuestionsInChapter(int pChapter);
	//��ĳһ��pQuestionId��ʼ��������pNextAmount�����⣬���ҷ��أ�����pQuestionId���Ǹ�Question��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);
	//ͬ�ϣ����һ�����������true��ʾ����������pQuestionId��ͬһ�½ڵ��������⣬�������pNextAmount���ж��ٷ��ض���
	//�������withinSameChapter Ϊfalse��������һ������һ��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount, boolean withinSameChapter);
	//����Question������ʱ��
	public boolean setLastVisitTime(int pQuestionId, Date pDate);
	
	
	//���Ҳ��ҷ������е��½ڶ���
	public List<Chapter> findAllChapters();
	//�����½�����
	public int getNumberOfChapters();
	//�����½ڱ�ţ������һ�¾���1�����ҳ����½ڶ���
	public Chapter findChapterWithNumber(int number);

	
	//��������������ȡ ĳһ��/ȫ�� ����ղص���Ŀ������
	public int getNumberOfMarkedQuestion(int pChapter);
	public int getNumberOfMarkedQuestion();
	//��ȡĳһ�½������ղص���Ŀ
	public List<Question> getMarkedQuestionsInChapter(int pChapter);
	//��ȡ�����½������ղص���Ŀ
	public List<Question> getMarkedQuestionsInChapter();
	//��pQuestionId�������������һ,ͬʱ����������ʱ��	
	public boolean markAsWrong(int pQuestionId,Date pNow);
	
	
	
}
