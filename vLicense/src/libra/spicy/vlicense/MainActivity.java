package libra.spicy.vlicense;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class MainActivity extends Activity implements OnItemClickListener {
	private int mCurrentSelectedTabId = R.id.main_tab_practise;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridView = (GridView) findViewById(R.id.main_grid_view);

		ArrayList<HashMap<String, Object>> gridviewData = new ArrayList<HashMap<String, Object>>();

		for (int i = 1; i < 10; i++) {
			HashMap<String, Object> grid = new HashMap<String, Object>();
			grid.put("ItemImage", R.drawable.ic_launcher);
			grid.put("ItemText", "Good" + i);
			gridviewData.add(grid);
		}

		SimpleAdapter gridViewAdapter = new SimpleAdapter(this, gridviewData,
				R.layout.grid_cell, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.item_image, R.id.item_textview });

		gridView.setAdapter(gridViewAdapter);
		gridView.setOnItemClickListener(this);

		final TabHost tabHost = (TabHost) findViewById(R.id.main_tab_host);
		tabHost.setup();
		//
		//
		// TabSpec spec;
		// spec = tabHost.newTabSpec(getString(R.string.bottom_tab_practise))
		// .setIndicator(getString(R.string.bottom_tab_practise))
		// .setContent(new Intent().setClass(this, SignActivity.class));
		// Log.i("my", getString(R.string.bottom_tab_practise));
		// tabHost.addTab(spec);
		//
		//
		// spec = tabHost.newTabSpec(getString(R.string.bottom_tab_sign))
		// .setIndicator(getString(R.string.bottom_tab_sign))
		// .setContent(new Intent().setClass(this, SignActivity.class));
		// tabHost.addTab(spec);
		//
		// spec = tabHost.newTabSpec(getString(R.string.bottom_tab_technique))
		// .setIndicator(getString(R.string.bottom_tab_technique))
		// .setContent(new Intent().setClass(this, SignActivity.class));
		// tabHost.addTab(spec);
		//
		// spec = tabHost.newTabSpec(getString(R.string.bottom_tab_setting))
		// .setIndicator(getString(R.string.bottom_tab_setting))
		// .setContent(new Intent().setClass(this, SignActivity.class));
		// tabHost.addTab(spec);
		//
		// spec = tabHost.newTabSpec(getString(R.string.bottom_tab_more))
		// .setIndicator(getString(R.string.bottom_tab_more))
		// .setContent(new Intent().setClass(this, SignActivity.class));
		// tabHost.addTab(spec);

		// tabHost.addTab(spec);
		// tabHost.setCurrentTab(1);

		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_tab_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mCurrentSelectedTabId) {
					return;
				}

				Intent intent = new Intent();
				switch (checkedId) {
				case R.id.main_tab_practise:
					tabHost.setCurrentTabByTag(getString(R.string.bottom_tab_practise));
					intent.setClass(MainActivity.this, MainActivity.class);
					break;
				case R.id.main_tab_sign:
					tabHost.setCurrentTabByTag(getString(R.string.bottom_tab_practise));
					intent.setClass(MainActivity.this, MainActivity.class);
					break;
				case R.id.main_tab_technique:
					tabHost.setCurrentTabByTag(getString(R.string.bottom_tab_practise));
					intent.setClass(MainActivity.this, MainActivity.class);
					break;
				case R.id.main_tab_setting:
					tabHost.setCurrentTabByTag(getString(R.string.bottom_tab_practise));
					intent.setClass(MainActivity.this, MainActivity.class);
					break;
				case R.id.main_tab_more:
					tabHost.setCurrentTabByTag(getString(R.string.bottom_tab_practise));
					intent.setClass(MainActivity.this, MainActivity.class);
					break;

				default:
					break;
				}
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i("my", "arg2:" + arg2);

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

}
