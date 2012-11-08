package libra.spicy.vlicense.utils;

import java.util.ArrayList;
import java.util.HashMap;

import libra.spicy.vlicense.R;
import libra.spicy.vlicense.activity.MainActivity;
import libra.spicy.vlicense.activity.QuestionActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class UIFactory {
	private static UIFactory factory;
	private MainMenuOnClickListener mMainMenuOnClickListener;
	private MainTabBarOnCheckedListener mMainTabbarOnCheckedListener;
	private UIActivity mCurrentActivity;

	private UIFactory() {
	}

	public static UIFactory getInstance(Activity context) {

		if (null == factory) {
			factory = new UIFactory();
		}

		return factory;
	}

	public void generateScreen(final UIActivity pUIActivity,
			final Activity pContext) {
		this.mCurrentActivity = pUIActivity;
		GridView mainMenu = generateMainMenu(pUIActivity, pContext);
		TabHost tabHost = generateMainTabHost(pUIActivity, pContext);

	}

	private GridView generateMainMenu(final UIActivity pUIActivity,
			final Activity pContext) {

		GridView gridView = (GridView) pContext
				.findViewById(R.id.main_grid_view);

		setGridViewAndDataAdapter(pUIActivity, gridView, pContext);

		return gridView;
	}

	private TabHost generateMainTabHost(final UIActivity pUIActivity,
			final Activity pContext) {

		final TabHost tabHost = (TabHost) pContext
				.findViewById(R.id.main_tab_host);
		tabHost.setup();
		RadioGroup radioGroup = (RadioGroup) pContext
				.findViewById(R.id.main_tab_group);

		// set current activity radio button checked.
		setRadioButtonChecked(mCurrentActivity, pContext);

		// set
		if (this.mMainTabbarOnCheckedListener == null) {
			this.mMainTabbarOnCheckedListener = new MainTabBarOnCheckedListener(
					tabHost, pContext);
		}
		mMainTabbarOnCheckedListener.setmContext(pContext);
		mMainTabbarOnCheckedListener.setmTabHost(tabHost);

		radioGroup
				.setOnCheckedChangeListener(this.mMainTabbarOnCheckedListener);

		return tabHost;
	}

	private void setRadioButtonChecked(final UIActivity pCurrentActivity,
			Activity pContext) {
		int checkRadioButtonId = 0;

		switch (pCurrentActivity) {
		case MainActivity:
			checkRadioButtonId = R.id.main_tab_practise;
			break;
		case SignActivity:
			checkRadioButtonId = R.id.main_tab_sign;
			break;
		case TechniqueActivit:
			checkRadioButtonId = R.id.main_tab_technique;
			break;
		case SettingActivity:
			checkRadioButtonId = R.id.main_tab_setting;
			break;
		case MoreActivity:
			checkRadioButtonId = R.id.main_tab_more;
			break;
		default:
			break;
		}

		RadioButton radioButton = (RadioButton) pContext
				.findViewById(checkRadioButtonId);
		radioButton.setChecked(true);

	}

	private GridView setGridViewAndDataAdapter(final UIActivity pActivity,
			final GridView pGridview, final Activity pContext) {

		int numberOfItemsInTheMainMenu = 0;

		switch (pActivity) {
		case MainActivity:
			numberOfItemsInTheMainMenu = 9;
			break;
		case SignActivity:
			numberOfItemsInTheMainMenu = 3;
			break;
		case TechniqueActivit:
			numberOfItemsInTheMainMenu = 5;
			break;
		case SettingActivity:
			numberOfItemsInTheMainMenu = 8;
			break;
		case MoreActivity:
			numberOfItemsInTheMainMenu = 2;
			break;
		default:
			break;
		}

		ArrayList<HashMap<String, Object>> gridviewData = new ArrayList<HashMap<String, Object>>();

		for (int i = 1; i < numberOfItemsInTheMainMenu; i++) {
			HashMap<String, Object> grid = new HashMap<String, Object>();
			grid.put("ItemImage", R.drawable.ic_launcher);
			grid.put("ItemText", "Good" + i);
			gridviewData.add(grid);
		}

		SimpleAdapter gridViewAdapter = new SimpleAdapter(pContext,
				gridviewData, R.layout.grid_cell, new String[] { "ItemImage",
						"ItemText" }, new int[] { R.id.item_image,
						R.id.item_textview });

		pGridview.setAdapter(gridViewAdapter);
		
		if (this.mMainMenuOnClickListener == null) {
			this.mMainMenuOnClickListener = new MainMenuOnClickListener();
		}
		mMainMenuOnClickListener.setmContext(pContext);
		pGridview.setOnItemClickListener(this.mMainMenuOnClickListener);

		return pGridview;
	}
}

class MainTabBarOnCheckedListener implements OnCheckedChangeListener {
	private TabHost mTabHost;
	private Activity mContext;

	public MainTabBarOnCheckedListener(TabHost pTabHost, Activity pContext) {
		this.mTabHost = pTabHost;
		this.mContext = pContext;
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {

		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(mContext, MainActivity.class);

		switch (checkedId) {
		case R.id.main_tab_practise:
			mTabHost.setCurrentTabByTag(mContext
					.getString(R.string.bottom_tab_practise));
			bundle.putSerializable("ToActivity", UIActivity.MainActivity);

			break;
		case R.id.main_tab_sign:
			mTabHost.setCurrentTabByTag(mContext
					.getString(R.string.bottom_tab_practise));
			bundle.putSerializable("ToActivity", UIActivity.SignActivity);
			break;
		case R.id.main_tab_technique:
			mTabHost.setCurrentTabByTag(mContext
					.getString(R.string.bottom_tab_practise));
			bundle.putSerializable("ToActivity", UIActivity.TechniqueActivit);
			break;
		case R.id.main_tab_setting:
			mTabHost.setCurrentTabByTag(mContext
					.getString(R.string.bottom_tab_practise));
			bundle.putSerializable("ToActivity", UIActivity.SettingActivity);
			break;
		case R.id.main_tab_more:
			mTabHost.setCurrentTabByTag(mContext
					.getString(R.string.bottom_tab_practise));
			bundle.putSerializable("ToActivity", UIActivity.MoreActivity);
			break;

		default:
			break;
		}

		intent.putExtras(bundle);
		mContext.startActivity(intent);
		mContext.overridePendingTransition(R.anim.in_from_right,
				R.anim.out_to_left);
	}

	public TabHost getmTabHost() {
		return mTabHost;
	}

	public void setmTabHost(TabHost mTabHost) {
		this.mTabHost = mTabHost;
	}

	public Activity getmContext() {
		return mContext;
	}

	public void setmContext(Activity mContext) {
		this.mContext = mContext;
	}

}

class MainMenuOnClickListener implements OnItemClickListener {
	private Activity mContext;

	public MainMenuOnClickListener() {
	}

	public MainMenuOnClickListener(Activity mContext) {
		this.mContext = mContext;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i("my", "arg2:" + arg2);
		
		mContext.startActivity(new Intent(mContext,QuestionActivity.class));
		
		switch (arg2) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}

	public void setmContext(Activity mContext) {
		this.mContext = mContext;
	}

}
