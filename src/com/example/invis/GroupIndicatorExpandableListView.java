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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;

public class GroupIndicatorExpandableListView extends ExpandableListView implements OnScrollListener {
	public GroupIndicatorExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public interface OnGroupIndicatorShowListener {
		void OnGroupIndicatorShow(boolean show, int groupId);
	}

	private OnGroupIndicatorShowListener onGroupIndicatorShowListener;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		boolean show;
		long listPosition = getExpandableListPosition(firstVisibleItem);
		// 当前第一行归属的组ID getPackedPositionGroup 返回所选择的组
		int groupId = getPackedPositionGroup(listPosition);
		// 当前第一行的子视图类型
		int viewType = getPackedPositionType(listPosition);
		// 当前第二行的子视图类型
		int nextViewType = getPackedPositionType(getExpandableListPosition(firstVisibleItem + 1));
		// 第一行和第二行都是header，不需要显示
		if ((viewType == PACKED_POSITION_TYPE_NULL && nextViewType == PACKED_POSITION_TYPE_NULL)
		// 第一行是header 第二行是group 不需要显示
				|| (viewType == PACKED_POSITION_TYPE_NULL && nextViewType == PACKED_POSITION_TYPE_GROUP)) {
			show = false;
		} else if
		// 第一行是child 第二行是group 不需要显示
		(viewType == PACKED_POSITION_TYPE_CHILD && nextViewType == PACKED_POSITION_TYPE_GROUP) {
			show = false;
		} else {
			show = true;
		}
		if (onGroupIndicatorShowListener != null) {
			onGroupIndicatorShowListener.OnGroupIndicatorShow(show, groupId);
		}
	}
	public void setOnGroupIndicatorShowListener(OnGroupIndicatorShowListener onGroupIndicatorShowListener) {
		setOnScrollListener(this);
		this.onGroupIndicatorShowListener = onGroupIndicatorShowListener;
	}
}
