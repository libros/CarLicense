package libra.spicy.vlicense.controller;

import libra.spicy.vlicense.R;
import libra.spicy.vlicense.model.Answer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

public class QuestionAnswerListviewAdapter extends BaseAdapter {

	private Answer[] mContent;
	private Context mContext;

	public QuestionAnswerListviewAdapter(Context context, Answer[] pContent) {

		this.mContext = context;
		this.mContent = pContent;
	}

	public void setmCntentList(Answer[] pCntList) {
		this.mContent = pCntList;
	}

	public int getCount() {
		return mContent.length;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		// System.out.println("in the list adapter getView.");
		if (view == null) {
			// System.out.println("in the list adapter getView, view == null");
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			view = layoutInflater
					.inflate(R.layout.question_listview_item, null);
		}

		Answer answer = mContent[position];

		if (answer != null) {
			CheckBox answerCheckBox = (CheckBox) view
					.findViewById(R.id.answer_checkbox);
			answerCheckBox.setText(answer.getDescription());
//			answerCheckBox.setOnClickListener(new OnClickListener() {
				
//				public void onClick(View v) {
//					System.out.println(v.getParent());
//					System.out.println(v.getParent().getParent());
//					ListView listview = (ListView) v.getParent();
//					
//					Object object = listview.getSelectedItem();
//					System.out.println(object);
					
			// }
			// });
		}

		return view;
	}

	public Object getItem(int position) {
		return mContent[position];
	}

	public long getItemId(int position) {
		return position;
	}

}
