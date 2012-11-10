package libra.spicy.vlicense.dao;

import java.util.Date;
import java.util.List;

import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;

public interface IQuestionDao {
	public enum LookDirection {
		Forward, Backward
	}

	// 查找某一个章节题目的数量
	public int findNumberOfQuestionsInAChapter(int pChapter);

	// 查找所有章节题目的数量
	public int findNumberOfQuestions();

	// 找到某章节所有的题目，参数pChapter指定某一章节
	public List<Question> findQuestionsInChapter(int pChapter);
	
	
	//从pChapter章中从第一个开始，找到接下去的nextAmount个题目
	public List<Question> findStartQuestions(int pChapter,int pNextAmount);

	// 从某一个pQuestionId开始，*默认向后*查找pNextAmount个试题，并且返回（包含pQuestionId的那个Question）
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);

	// 从某一个pQuestionId开始，根据LookDirection的值选择是向前/向后查找pNextAmount个试题，
	// 并且返回这个List（包含pQuestionId的那个Question，默认在同一个章节中查找）
	// 如果同章节中的数量不够，有多少就返回多少
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection);

	// 同上，最后一个参数如果是true表示仅仅返回与pQuestionId在同一章节的所有试题，如果不够pNextAmount则有多少返回多少
	// 如果参数withinSameChapter 为false，则与上一个函数一样
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection, boolean pWithinSameChapter);

	// 设置Question最后访问时间
	public boolean setLastVisitTime(int pQuestionId, Date pDate);

	// 查找并且返回所有的章节对象
	public List<Chapter> findAllChapters();

	// 查找章节数量
	public int getNumberOfChapters();

	// 根据章节编号（例如第一章就是1）查找出该章节对象
	public Chapter findChapterWithNumber(int number);

	// 以下两个方法获取 某一章/全部 标记收藏的题目的数量
	public int getNumberOfMarkedQuestion(int pChapter);

	public int getNumberOfMarkedQuestion();

	// 获取某一章节所有收藏的题目
	public List<Question> getMarkedQuestionsInChapter(int pChapter);

	// 获取所有章节所有收藏的题目
	public List<Question> getMarkedQuestionsInChapter();

	// 给pQuestionId的试题错误数加一,同时保存最后访问时间
	public boolean markAsWrong(int pQuestionId, Date pNow);

}
