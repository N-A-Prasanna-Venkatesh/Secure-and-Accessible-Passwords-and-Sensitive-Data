package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class ChangeTime extends AppCompatActivity {
    TimePicker tp;
    Button btn;
    int hour,minute;
    String h,m;
    public static final String MyPreferences = "Preferences";
    public static final String Hour = "hour";
    public static final String Minute = "minute";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_change_time);

        tp = findViewById(R.id.TP2);
        btn = findViewById(R.id.set_Time);

        sharedPreferences=getSharedPreferences(MyPreferences,MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour=tp.getCurrentHour()+10;
                minute=tp.getCurrentMinute()+25;
                h=Integer.toString(hour);
                m=Integer.toString(minute);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Hour,h);
                editor.putString(Minute,m);
                editor.commit();

                Toast.makeText(ChangeTime.this, "Changed Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ChangeTime.this,Data.class);
                startActivity(intent);

            }
        });


    }
}