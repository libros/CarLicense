package libra.spicy.vlicense.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import libra.spicy.vlicense.model.Answer;
import libra.spicy.vlicense.model.Chapter;
import libra.spicy.vlicense.model.Question;
import libra.spicy.vlicense.model.Question.Classification;

public class MockQuestionSource {
	public static ArrayList<Question> questions;

	static {
		questions = new ArrayList<Question>();

			for (int i = 0; i < 100; i++) {
				Question question = new Question(i, null,
						"Chapter " +( i / 10 ) + "Question is No." + i, new Answer[] {
								new Answer("��һ��ѡ��", false),
								new Answer("�ڶ���ѡ��", false),
								new Answer("������ѡ��", true),
								new Answer("���ĸ�ѡ��", false) }, "�����ǽ���",
						new Chapter("AA", (i / 10)), 0, Classification.A);

				questions.add(question);
			}
	}

	public static int sizeOfQuestion() {
		return 100;
	}

	public static Question getByIndex(int position) {
		// TODO Auto-generated method stub
		return questions.get(position);
	}

}
