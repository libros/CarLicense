package libra.spicy.vlicense;

import libra.spicy.vlicense.utils.UIActivity;
import libra.spicy.vlicense.utils.UIFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity  {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textview_header = (TextView) findViewById(R.id.tv_head);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		UIFactory uiFactory = UIFactory.getInstance(this);

		// First time the application was lunched
		if (extras == null) {
			Log.d("my", "extra is null");
			uiFactory.generateScreen(UIActivity.MainActivity, this);
			textview_header.setText(UIActivity.MainActivity.toString());
		} else {
			UIActivity toActivity = (UIActivity) extras.get("ToActivity");
			textview_header.setText(toActivity.toString());
			uiFactory.generateScreen(toActivity, this);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
