package com.zjf.nicework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomView extends ViewGroup {
	private static final int OFFSET = 80;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// �ڷ�
		int left = 0;
		int right = 0;
		int top = 0;
		int bottom = 0;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			left = i*OFFSET;
			right = left + child.getMeasuredWidth();
			bottom = top + child.getMeasuredHeight();
			child.layout(left, top, right, bottom);
			
			top += child.getMeasuredHeight();
		}

	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
        int width = 0;
        int height = 0;
		//1.�����Լ��ĳߴ�
//		ViewGroup.onMeasure();
		//1.1 Ϊÿһ��child������������Ϣ(MeasureSpec)
//		ViewGroup.getChildMeasureSpec();
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			LayoutParams lp = child.getLayoutParams();
			int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
			int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
			//1.2 �����������Ľ��������ÿһ����View����view�����Լ��ĳߴ�
			child.measure(childWidthSpec, childHeightSpec);
		}
//		//1.3 ��View�����꣬ViewGroup�Ϳ����õ������View�Ĳ�����ĳߴ���
//		child.getChildMeasuredSize();//child.getMeasuredWidth()��child.getMeasuredHeight()
//		//1.4ViewGroup�Լ��Ϳ��Ը�����������(Padding�ȵ�),�������Լ��ĳߴ�(mode,value)
//		ViewGroup.calculateSelfSize();
		
		switch (widthMode) {
		case MeasureSpec.EXACTLY:
			width = widthSize;
			break;
		case MeasureSpec.AT_MOST:
		case MeasureSpec.UNSPECIFIED:
			for (int i = 0; i < childCount; i++) {
				View child = getChildAt(i);
				int widthAndOffset = i*OFFSET + child.getMeasuredWidth();
				width = Math.max(width, widthAndOffset);
			}
			break;
		default:
			break;
		}
		
		
		switch (heightMode) {
		case MeasureSpec.EXACTLY:
			height = heightSize;
			break;
		case MeasureSpec.AT_MOST:
		case MeasureSpec.UNSPECIFIED:
			for (int i = 0; i < childCount; i++) {
				View child = getChildAt(i);
				height = height + child.getMeasuredHeight();
			}
			break;
		default:
			break;
		}
		//2.�����Լ��ĳߴ�
//				ViewGroup.setMeasuredDimension(size);
		setMeasuredDimension(width, height);
	}

}
