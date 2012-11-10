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
								new Answer("第一个选项", false),
								new Answer("第二个选项", false),
								new Answer("第三个选项", true),
								new Answer("第四个选项", false) }, "这里是解释",
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
