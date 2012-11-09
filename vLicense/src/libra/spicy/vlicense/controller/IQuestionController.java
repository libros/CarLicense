package libra.spicy.vlicense.controller;

import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionController {
	//找到某章节所有的题目，参数pChapter指定某一章节
	public List<Question> findQuestionsInChapter(int pChapter);
	//从某一个pQuestionId开始，向后查找pNextAmount个试题，并且返回（包含pQuestionId的那个Question）
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);
	//同上，最后一个参数如果是true表示仅仅返回与pQuestionId在同一章节的所有试题，如果不够pNextAmount则有多少返回多少
	//如果参数withinSameChapter 为false，则与上一个函数一样
	public List<Question> findQuestions(int pQuestionId, int pNextAmount, boolean withinSameChapter);
	
	//查找并且返回所有的章节对象
	public List<Chapter> findAllChapters();
	//根据章节编号（例如第一章就是1）查找出该章节对象
	public Chapter findChapterWithNumber(int number);
	
	//给pQuestionId的试题错误数加一	
	public boolean markAsWrong(int pQuestionId);
	
	
	
}
