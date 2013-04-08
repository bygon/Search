package kr.example.a;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class A_Activity extends Activity implements OnClickListener{
	static final int[] BUTTONS = 
		{
			R.id.button1,
			R.id.button2,
			R.id.button3
		};
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    		
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	Toast.makeText(A_Activity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
		    	Log.i("Button Event","is Clicked");
		    }
		});
		button.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(A_Activity.this, "Button Touched", Toast.LENGTH_SHORT).show();
				Log.i("Button Event","is Touched");
				return false;
			}
		});
		for(int btnId:BUTTONS){
			Button tmpButton = (Button) findViewById(btnId);
			tmpButton.setOnClickListener(this);
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button1:
			Toast.makeText(A_Activity.this, "가위를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button2:
			Toast.makeText(A_Activity.this, "바위를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button3:
			Toast.makeText(A_Activity.this, "보를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}

