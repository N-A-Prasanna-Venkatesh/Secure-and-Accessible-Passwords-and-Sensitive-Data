package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class More extends AppCompatActivity {
    Button btn_add,btn_update,btn_delete,btn_Search,btn_chgTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_more);

        btn_add = findViewById(R.id.Add_Data);
        btn_update = findViewById(R.id.Update_Data);
        btn_delete = findViewById(R.id.Delete_Data);
        btn_Search = findViewById(R.id.Search_Data);
        btn_chgTime=findViewById(R.id.chng_time);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(More.this,Add_Pass.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(More.this,Update_Pass.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(More.this,Delete_Key_Value.class);
                startActivity(intent);
            }
        });

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(More.this,Search_Data.class);
                startActivity(intent);
            }
        });
        btn_chgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(More.this,ChangeTime.class);
                startActivity(intent);
            }
        });


    }
}