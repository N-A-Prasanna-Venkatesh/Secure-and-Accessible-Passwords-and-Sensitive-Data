package secure.alarm.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Delete_Key_Value extends AppCompatActivity {
    EditText et_delete;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__key__value);

        et_delete=findViewById(R.id.Delete_Key);
        btn_delete=findViewById(R.id.Delete_Data);


    }
}