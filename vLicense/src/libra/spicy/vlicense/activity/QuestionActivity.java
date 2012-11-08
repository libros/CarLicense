package libra.spicy.vlicense.activity;

import java.util.ArrayList;

import libra.spicy.vlicense.R;
import libra.spicy.vlicense.utils.QuestionFactory;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	private GestureDetector mGestureDetector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		ViewPager viewPager = (ViewPager) findViewById(R.id.question_view_pager);
		LayoutInflater inflater = LayoutInflater.from(this);
		// View view1 = inflater.inflate(R.layout.layout1, null);

		// viewList.add(view1);

		final ArrayList<View> viewList = new ArrayList<View>();
		View view1 = inflater.inflate(R.layout.layout1, null);
		View view2 = inflater.inflate(R.layout.layout1, null);
		View view3 = inflater.inflate(R.layout.layout1, null);
		View view4 = inflater.inflate(R.layout.layout1, null);
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		viewList.add(view4);

		PagerAdapter pageAdapter = new PagerAdapter() {

			@Override
			public int getCount() {
				return QuestionFactory.sizeOfQuestion();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				Log.d("debug", "instantiateItem position:" + position);

				View view = viewList.get(position % viewList.size());
				TextView question_textview = (TextView) view
						.findViewById(R.id.question_textview);
				question_textview.setText(QuestionFactory.getByIndex(position)
						.getmQuestion());

				((ViewPager) container).addView(view);
				
				return view;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				Log.d("debug", "destroyItem position:" + position);
				((ViewPager) container).removeView( viewList.get(position % viewList.size()));
			}

		};

		viewPager.setAdapter(pageAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_question, menu);
		return true;
	}

}
