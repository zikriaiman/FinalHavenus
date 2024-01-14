package safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.finalhavenus.R;

public class domesticViolMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domestic_viol_menu);

        // Assuming you have buttons or clickables for each option
        CardView option1 = findViewById(R.id.cardDomViol);
        // Add more as needed...

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to open ViolenceStep for option 1
                Intent intent = new Intent(getApplicationContext(), violenceStep.class);
                startActivity(intent);
                finish();
            }
        });


        // Add more click listeners for other options...
    }
}
