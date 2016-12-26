package com.bjsz.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义Gridview 重写其高度
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseLyGridView extends GridView {
	public BaseLyGridView(Context context) {
		super(context);

	}

	public BaseLyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
