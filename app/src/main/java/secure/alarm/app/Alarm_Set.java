package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class Alarm_Set extends AppCompatActivity {
    TimePicker tp1;
    Button btn1;
    int hour,minute;
    String h,m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_alarm__set);

        tp1=findViewById(R.id.TP1);
        btn1=findViewById(R.id.Set_Alarm);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour=tp1.getCurrentHour();
                minute=tp1.getCurrentMinute();
                if(hour==5 && minute==12)
                {
                    Intent intent = new Intent(Alarm_Set.this,Data.class);
                    startActivity(intent);
                }else
                    {
                        h=Integer.toString(hour);
                        m=Integer.toString(minute);
                        Toast.makeText(Alarm_Set.this, "Alarm set successfully at "+h+":"+m, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Alarm_Set.this,MainActivity.class);
                        startActivity(intent);
                    }

            }
        });




    }
}