package libra.spicy.vlicense;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
