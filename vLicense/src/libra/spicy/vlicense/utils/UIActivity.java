package libra.spicy.vlicense.utils;

public enum UIActivity {
	MainActivity, SignActivity, SettingActivity, MoreActivity, TechniqueActivit;

	public String toString() {
		String toString = null;
		switch (this) {
		case MainActivity:
			toString = "MainActivity";
			break;
		case SignActivity:
			toString = "SignActivity";
			break;
		case SettingActivity:
			toString = "SettingActivity";
			break;
		case MoreActivity:
			toString = "MoreActivity";
			break;
		case TechniqueActivit:
			toString = "TechniqueActivit";
		default:
			break;
		}
		return toString;
	};
}
