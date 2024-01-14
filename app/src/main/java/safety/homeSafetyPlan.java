package safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class homeSafetyPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_safety_plan);

        // Assuming you have buttons or clickables for each category
        CardView category1 = findViewById(R.id.cardDomViol);
        // Add more as needed...

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to open DomesticViolenceMenu for category 1
                Intent intent = new Intent(getApplicationContext(), domesticViolMenu.class);
                startActivity(intent);
            }
        });

        // Add more click listeners for other categories...
    }
}
