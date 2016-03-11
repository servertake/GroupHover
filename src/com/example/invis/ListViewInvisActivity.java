/*
 * Copyright (c) 2014. hupei (hupei132@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.invis;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class ListViewInvisActivity extends ListActivity {
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		ListView listView = getListView();
		
		
		
		final TextView tvInvis = (TextView) findViewById(R.id.main_listview_invis);
		
		
		
/*		View header = View.inflate(this, R.layout.acitivty_listview_header, null);
		View invis = View.inflate(this, R.layout.acitivty_listview_invis, null);
		
		
		
		listView.addHeaderView(header);
		listView.addHeaderView(invis);*/
		List<String> datas = new ArrayList<String>();
		
		
		
		
		for (int i = 0; i < 22; i++) {
			datas.add("数据条目--" + i + "-啊");
		}
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas));

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				System.out.println("firstVisibleItem=[" + firstVisibleItem + "] visibleItemCount=[" + visibleItemCount + "] totalItemCount=[" + totalItemCount + "]");
				if (firstVisibleItem >= 1)
					tvInvis.setVisibility(0);
				else
					tvInvis.setVisibility(8);
			}
		});
	};
}