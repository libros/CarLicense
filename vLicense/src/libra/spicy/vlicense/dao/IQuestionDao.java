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
	 * 查找某一个章节题目的数量
	 * 
	 * @param pChapter
	 *            章节编号
	 * @return 章节编号下的问题数量
	 */
	public int findNumberOfQuestionsInAChapter(int pChapter);

	/**
	 * 查找所有章节题目的数量
	 * 
	 * @return 返回数据库中所有题目的数量
	 */
	public int findNumberOfQuestions();

	/**
	 * 找到某章节所有的题目，参数pChapter指定某一章节
	 * 
	 * @param pChapter
	 *            数据库章节的编号
	 * @return 返回指定章节的问题的列表
	 */
	public List<Question> findQuestionsInChapter(int pChapter);

	/**
	 * 从pChapter章中从第一个开始，找到接下去的nextAmount个题目
	 * 
	 * @param pChapter
	 *            章节编号
	 * @param pNextAmount
	 *            预读取数量
	 * @return 返回pChapter章第一个开始的预读取数量的问题列表，如果不足pNextAmount,那么就返回剩下的
	 */
	public List<Question> findStartQuestions(int pChapter, int pNextAmount);

	/**
	 * 从某一个pQuestionId开始，*默认向后*查找pNextAmount个试题，并且返回（包含pQuestionId的那个Question）
	 * 
	 * @param pQuestionId
	 *            开始的Questionid
	 * @param pNextAmount
	 *            设定返回题目的数量
	 * @return 按要求返回的题目列表
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount);

	/**
	 * 从某一个pQuestionId开始，根据LookDirection的值选择是向前/向后查找pNextAmount个试题，
	 * 并且返回这个List（包含pQuestionId的那个Question，默认在同一个章节中查找） 如果同章节中的数量不够，有多少就返回多少
	 * 
	 * @param pQuestionId
	 * @param pNextAmount
	 * @param pLookDirection
	 *            查找的方向，可以向前和向后
	 * @return 按要求返回题目的列表
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection);

	/*
	 * 同上，最后一个参数如果是true表示仅仅返回与pQuestionId在同一章节的所有试题，如果不够pNextAmount则有多少返回多少
	 * 如果参数withinSameChapter 为false，则与上一个函数一样
	 */
	public List<Question> findQuestions(int pQuestionId, int pNextAmount,
			LookDirection pLookDirection, boolean pWithinSameChapter);

	/**
	 *  设置Question最后访问时间
	 * @param pQuestionId 设置的题目
	 * @param pDate 时间
	 * @return 设置成功返回true
	 */
	public boolean setLastVisitTime(int pQuestionId, Date pDate);

	/**
	 *  查找并且返回所有的章节对象
	 * @return 返回所有的章节对象
	 */
	public List<Chapter> findAllChapters();

	/**
	 *  查找章节数量
	 * @return 返回共有多少个章节
	 */
	public int getNumberOfChapters();

	/**
	 *  根据章节编号（例如第一章就是1）查找出该章节对象
	 * @param number 查找的章节的编号
	 * @return 返回给定编号的章节对象
	 */
	public Chapter findChapterWithNumber(int number);

	/**
	 *  获取 某一章 标记收藏的题目的数量
	 * @param pChapter 某一章节的编号
	 * @return 某一章标记收藏的题目的数量
	 */
	public int getNumberOfMarkedQuestion(int pChapter);

	/**
	 *  获取所有标记收藏的题目的数量
	 * @param pChapter 某一章节的编号
	 * @return 某一章标记收藏的题目的数量
	 */
	public int getNumberOfMarkedQuestion();

	/**
	 * 	获取某一章节所有收藏的题目
	 * @param pChapter 章节编号
	 * @return 返某一章节标记收藏的题目
	 */
	public List<Question> getMarkedQuestionsInChapter(int pChapter);

	/**
	 *  获取所有章节所有收藏的题目
	 * @return 所有章节所有收藏的题目
	 */
	public List<Question> getMarkedQuestionsInChapter();

	/**
	 *  给pQuestionId的试题错误数加一,同时保存最后访问时间
	 * @param pQuestionId 要指定为错误的题目编号
	 * @param pNow 最后访问时间
	 * @return 设置成功返回true，否则false
	 */
	public boolean markAsWrong(int pQuestionId, Date pNow);

}
