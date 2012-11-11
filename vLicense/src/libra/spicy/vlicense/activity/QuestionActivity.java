package libra.spicy.vlicense.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import libra.spicy.vlicense.R;
import libra.spicy.vlicense.controller.QuestionActivityController;
import libra.spicy.vlicense.controller.QuestionAnswerListviewAdapter;
import libra.spicy.vlicense.model.Answer;
import libra.spicy.vlicense.model.Question;
import libra.spicy.vlicense.utils.LicenseConstants.Chapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionActivity extends Activity implements OnItemClickListener {
	private QuestionActivityController mQuestionActivityController;
	private QuestionAnswerListviewAdapter mAnswerAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		mQuestionActivityController = new QuestionActivityController(this,
				Chapter.Chapter2);

		// mAnswerAdapter = new QuestionAnswerListviewAdapter(this,
		// R.layout.question_listview_item);

		ViewPager viewPager = (ViewPager) findViewById(R.id.question_view_pager);
		LayoutInflater inflater = LayoutInflater.from(this);

		// ��ʼ���ĸ�View����pageViewer������
		final ArrayList<View> viewList = new ArrayList<View>();
		for (int i = 0; i < 4; i++) {
			// pageView������ʾ�Ľ��棬��ʼ��4�������л���ʹ��
			View view1 = inflater.inflate(R.layout.layout1, null);
			viewList.add(view1);
		}

		PagerAdapter pageAdapter = new PagerAdapter() {

			@Override
			public int getCount() {
				// System.out.println("getCount");
				return mQuestionActivityController.numberOfQueston();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				Log.d("debug", "instantiateItem position:" + position);
				// �����ݿ�����ȡ����λ�õ�����
				Question question = mQuestionActivityController
						.getQuestionAtPosition(position);

				// ҳ�滬���ܹ���Ҫ�ĸ�View�����þͺ���
				View view = viewList.get(position % viewList.size());

				// ȡ��viewPager��ҳ�沼���е����ⲿ�ֵ���������
				TextView question_textview = (TextView) view
						.findViewById(R.id.question_textview);
				question_textview.setText(question.getQuestion());

				// ȥ��viewPager��ͼƬ�����ã������Ŀ��ͼƬ�Ļ���
				if (question.getImageFileName() != null
						&& question.getImageFileName().trim().length() > 0) {

					// System.out.println(String.format("id=%d has a imagefile",
					// question.getQuestionNumber()));

					Bitmap image = getImageFromAssetsFile(question
							.getImageFileName());

					// System.out.println("image is " + image);

					ImageView imageView = (ImageView) view
							.findViewById(R.id.question_image);
					imageView.setVisibility(View.VISIBLE);
					imageView.setImageBitmap(image);
				}

				// ����Listview����ʾCheckBox
				ListView answerListview = (ListView) view
						.findViewById(R.id.question_list_view);
				answerListview.setOnItemClickListener(QuestionActivity.this);

				answerListview.setAdapter(new QuestionAnswerListviewAdapter(
						QuestionActivity.this, question.getAnswers()));

				((ViewPager) container).addView(view);

				return view;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				Log.d("debug", "destroyItem position:" + position);
				((ViewPager) container).removeView(viewList.get(position
						% viewList.size()));
			}

		};

		viewPager.setAdapter(pageAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_question, menu);
		return true;
	}

	private Bitmap getImageFromAssetsFile(String fileName) {
		Bitmap image = null;
		try {
			InputStream is = getResources().getAssets().open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return image;
	}

	public void onItemClick(AdapterView<?> listView, View view, int position,
			long id) {
		Answer answer = (Answer) listView.getItemAtPosition(position);
		System.out.println(answer.getDescription());

		CheckBox checkBox = (CheckBox) view.findViewById(R.id.answer_checkbox);
		System.out.println("before is checked" + checkBox.isChecked());
		 checkBox.setChecked(!checkBox.isChecked());
		 System.out.println("after is checked" + checkBox.isChecked());

		if (answer.isCorrect()) {

		}

	}

}
