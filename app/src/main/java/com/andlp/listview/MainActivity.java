package com.andlp.listview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_up,btn_down,btn_stop;
	private ListView listview;
	private Adapter adapter;
    @Override  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findBy();
        init();
    }

	private void init()  {
		btn_up.setOnClickListener(this);
		btn_down.setOnClickListener(this);
	    btn_stop.setOnClickListener(this);
	    
	    adapter = new Adapter(this);
	    listview.setAdapter(adapter);
    }

	private void findBy() {
	    btn_up = findViewById(R.id.btn_scroll_up);
	    btn_down = findViewById(R.id.btn_scroll_down);
	    btn_stop = findViewById(R.id.btn_scroll_stop);
	    listview = findViewById(R.id.listview);
    }

	@Override public void onClick(View v)  {
	    switch(v.getId()) {
	    case R.id.btn_scroll_down: listScrollDown(); break;
	    case R.id.btn_scroll_up: listScrollUp(); break;
	    case R.id.btn_scroll_stop: listScrollOff(); break;
	    }
    }
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override public void handleMessage(Message msg) {
			handler.removeCallbacks(run_scroll_down);
			handler.removeCallbacks(run_scroll_up);
        }
	};

	public void listScrollUp() {
		listScrollOff();
		handler.postDelayed(run_scroll_up, 0);
	}

	public void listScrollDown() {
		listScrollOff();
		handler.postDelayed(run_scroll_down, 0);
	}

	public void listScrollOff() {
		handler.removeCallbacks(run_scroll_down);
		handler.removeCallbacks(run_scroll_up);
	}

	Runnable run_scroll_up = new Runnable()
	{
		@Override
		public void run()
		{
//			listview.smoothScrollBy(dip2px(MainActivity.this,40), 10);
//			handler.postDelayed(run_scroll_up,3000);

			listview.setSelectionAfterHeaderView();
			a--;
			listview.setSelection(a);
			handler.postDelayed(run_scroll_down, 3000);

		}
	};

	int a =0;
	Runnable run_scroll_down = new Runnable()
	{
		@Override
		public void run()
		{
			listview.setSelectionAfterHeaderView();
			a++;
			listview.setSelection(a);
			handler.postDelayed(run_scroll_down, 3000);
		}
	};



	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

    //判断listview已到底
	public boolean isListViewReachBottomEdge(final ListView listView) {
		boolean result=false;
		if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
			final View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
			result= (listView.getHeight()>=bottomChildView.getBottom());
		}
		return  result;
	}

	//listview到顶部
	public boolean isListViewReachTopEdge(final ListView listView) {
		boolean result=false;
		if(listView.getFirstVisiblePosition()==0){
			final View topChildView = listView.getChildAt(0);
			result=topChildView.getTop()==0;
		}
		return result ;
	}


}