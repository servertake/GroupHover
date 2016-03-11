package com.example.invis;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[] { "ListView", "ExpandableList" }));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			startActivity(new Intent(MainActivity.this, ListViewInvisActivity.class));
			break;
		case 1:
			startActivity(new Intent(MainActivity.this, ExpandableListInvisActivity.class));
			break;		
		default:
			break;
		}
	}
}