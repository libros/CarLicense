package libra.spicy.vlicense.utils;

import java.util.ArrayList;
import java.util.List;

import libra.spicy.vlicense.model.Question;
import libra.spicy.vlicense.model.Question.Classification;

public class QuestionFactory {
	private static List<Question> mQuestionList = new ArrayList<Question>();

	public static int sizeOfQuestion() {
		return mQuestionList.size();
	}

	static {
		for (int i = 0; i < 23; i++) {
			Question question = new Question(i + "", "", "Question" + i,
					new String[] { "A", "B", "C", "D" }, "D", "Exp", 1, false,
					Classification.C);
			mQuestionList.add(question);
		}
	}

	public static Question getByIndex(int index) {
		return mQuestionList.get(index);
	}

}
