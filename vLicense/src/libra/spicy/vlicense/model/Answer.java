package libra.spicy.vlicense.model;

public class Answer {
	private boolean correct;
	private String description;

	public Answer(String description, boolean correct) {
		this.correct = correct;
		this.description = description;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
