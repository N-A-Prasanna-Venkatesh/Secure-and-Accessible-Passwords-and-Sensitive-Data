package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_Key_Value extends AppCompatActivity {
    EditText et_delete;
    Button btn_delete;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__key__value);

        et_delete=findViewById(R.id.Delete_Key);
        btn_delete=findViewById(R.id.Delete_Data);

        DB=new DBHelper(this);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =et_delete.getText().toString();
                Boolean chk = DB.deletedata(a);
                if (chk)
                {
                    Toast.makeText(Delete_Key_Value.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }else
                    {
                        Toast.makeText(Delete_Key_Value.this, "Data not deleted.", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }


}