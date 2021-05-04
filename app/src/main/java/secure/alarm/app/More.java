package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class More extends AppCompatActivity {
    Button btn_add,btn_update,btn_delete,btn_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        btn_add = findViewById(R.id.Add_Data);
        btn_update = findViewById(R.id.Update_Data);
        btn_delete = findViewById(R.id.Delete_Data);
        btn_Search = findViewById(R.id.Search_Data);

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


    }
}