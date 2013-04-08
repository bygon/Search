package kr.example.b_project;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	}
	
	public void myClickHandler(View v) {
		switch(v.getId()){
		case R.id.button1:
			Toast.makeText(MainActivity.this, "가위를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button2:
			Toast.makeText(MainActivity.this, "바위를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button3:
			Toast.makeText(MainActivity.this, "보를 내셨습니다", Toast.LENGTH_SHORT).show();
			break;
		}
    }


}
