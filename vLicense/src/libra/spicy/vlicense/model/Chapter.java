package libra.spicy.vlicense.model;

public class Chapter {
	// 章节的名字
	private String chapterName;

	// 章节编号，例如第一章就是1，第二章就是2
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
