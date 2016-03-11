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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.invis.GroupIndicatorExpandableListView.OnGroupIndicatorShowListener;

public class ExpandableListInvisActivity extends Activity {

	private final class GroupData {
		public String[] groupNames = { "好友", "同学", "同事", "吃货", "球友" };
		public List<GroupData> groupDataList;

		public String groupName;
		public List<ChildData> childNameList;

		public void initExpandableData(List<ChildData> childDatas) {
			groupDataList = new ArrayList<GroupData>();
			for (int i = 0; i < groupNames.length; i++) {
				GroupData groupData = new GroupData();
				List<ChildData> child = new ArrayList<ChildData>();

				groupData.groupName = groupNames[i];
				groupData.childNameList = child;
				for (ChildData childData : childDatas) {
					if (childData.groupID == i) {
						child.add(childData);
					}
				}
				groupDataList.add(groupData);
			}
		}
	}

	private final class ChildData {
		public String childName;
		public int groupID;

		public List<ChildData> childDataList;

		public void initChildData(String[] groupNames) {
			childDataList = new ArrayList<ChildData>();
			for (int i = 0; i < groupNames.length; i++) {
				int childSize = 20;
				if (i == 0)
					childSize = 30;
				if (i == 2)
					childSize = 40;
				for (int j = 0; j < childSize; j++) {
					ChildData childData = new ChildData();
					childData.groupID = i;
					childData.childName = groupNames[i] + "的数据" + j;
					childDataList.add(childData);
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandablelist);
		final GroupIndicatorExpandableListView expandableListView = (GroupIndicatorExpandableListView) findViewById(android.R.id.list);
		final TextView tvInvis = (TextView) findViewById(R.id.group);
		
		
		expandableListView.setOnGroupIndicatorShowListener(new OnGroupIndicatorShowListener() {

			@Override
			public void OnGroupIndicatorShow(boolean show, int groupId) {
				// TODO Auto-generated method stub
				Log.e("----------滑动事件是否执行","45561");
				MyExpandableListAdapter adapter = (MyExpandableListAdapter) expandableListView.getExpandableListAdapter();
				String groupName = (groupId > -1) ? ((GroupData) adapter.getGroup(groupId)).groupName : "";
				tvInvis.setVisibility(show ? View.VISIBLE : View.GONE);
				tvInvis.setText(groupName);
			}
		});
		
		
		
		View header = View.inflate(this, R.layout.acitivty_listview_header, null);
		expandableListView.addHeaderView(header);

		GroupData groupData = new GroupData();
		ChildData childData = new ChildData();

		childData.initChildData(groupData.groupNames);
		groupData.initExpandableData(childData.childDataList);

		List<GroupData> datas = groupData.groupDataList;
		expandableListView.setAdapter(new MyExpandableListAdapter(datas));
	}

	private final class MyExpandableListAdapter extends BaseExpandableListAdapter {
		private List<GroupData> datas;

		public MyExpandableListAdapter(List<GroupData> datas) {
			this.datas = datas;
		}

		@Override
		public int getGroupCount() {
			if (datas == null)
				return 0;
			return datas.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			if (datas == null)
				return 0;
			return datas.get(groupPosition).childNameList.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			if (datas == null)
				return null;
			return datas.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			if (datas == null)
				return null;
			return datas.get(groupPosition).childNameList.get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return 0;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			GroupHolder groupHolder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(ExpandableListInvisActivity.this).inflate(R.layout.activity_expandablelist_group, parent, false);
				groupHolder = new GroupHolder();
				groupHolder.tvGroup = (TextView) view.findViewById(R.id.group);
				view.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) view.getTag();
			}
			GroupData groupData = (GroupData) getGroup(groupPosition);
			groupHolder.tvGroup.setText(groupData.groupName);
	
			return view;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder childHolder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(ExpandableListInvisActivity.this).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
				childHolder = new ChildHolder();
				childHolder.tvChild = (TextView) view.findViewById(android.R.id.text1);
				view.setTag(childHolder);
			} else {
				childHolder = (ChildHolder) view.getTag();
			}
			ChildData childData = (ChildData) getChild(groupPosition, childPosition);
			childHolder.tvChild.setText(childData.childName);
			return view;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}

		@Override
		public int getGroupType(int groupPosition) {
			return super.getGroupType(groupPosition);
		}

		class GroupHolder {
			TextView tvGroup;
		}

		class ChildHolder {
			TextView tvChild;
		}
	}
}
