package libra.spicy.vlicense.utils;

public interface LicenseConstants {
	public enum Chapter {
		Chapter1(1), Chapter2(2), Chapter3(3), Chapter4(4), Chapter5(5), Chapter6(
				6), Chapter7(7), Chapter8(8), ChapterAll(0);

		private int mChapterNumber;

		Chapter(int pChapterNumber) {
			this.mChapterNumber = pChapterNumber;
		}

		public int getChapterNumber() {
			return this.mChapterNumber;
		}

	}
}
