package libra.spicy.vlicense.model;

public class Chapter {
	// �½ڵ�����
	private String chapterName;

	// �½ڱ�ţ������һ�¾���1���ڶ��¾���2
	private int chapterNumber;

	public Chapter(String chapterName, int chapterNumber) {
		super();
		this.chapterName = chapterName;
		this.chapterNumber = chapterNumber;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

}
