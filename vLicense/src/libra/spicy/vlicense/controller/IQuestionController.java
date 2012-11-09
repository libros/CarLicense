package libra.spicy.vlicense.controller;

import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionController {
	//�ҵ�ĳ�½����е���Ŀ������pChapterָ��ĳһ�½�
	public List<Question> findQuestionsInChapter(int pChapter);
	//��ĳһ��pQuestionId��ʼ��������pNextAmount�����⣬���ҷ��أ�����pQuestionId���Ǹ�Question��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);
	//ͬ�ϣ����һ�����������true��ʾ����������pQuestionId��ͬһ�½ڵ��������⣬�������pNextAmount���ж��ٷ��ض���
	//�������withinSameChapter Ϊfalse��������һ������һ��
	public List<Question> findQuestions(int pQuestionId, int pNextAmount, boolean withinSameChapter);
	
	//���Ҳ��ҷ������е��½ڶ���
	public List<Chapter> findAllChapters();
	//�����½ڱ�ţ������һ�¾���1�����ҳ����½ڶ���
	public Chapter findChapterWithNumber(int number);
	
	//��pQuestionId�������������һ	
	public boolean markAsWrong(int pQuestionId);
	
	
	
}
