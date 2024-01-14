package emergency;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhavenus.R;

public class ContactActivity extends AppCompatActivity {
    LinearLayout eline;
    ImageView pdrm, wao, bomba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

         eline = findViewById(R.id.eline);
         pdrm = findViewById(R.id.pdrm);
         wao = findViewById(R.id.woman);
         bomba = findViewById(R.id.bomba);



        eline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                openDialer("999");
            }
        });

        pdrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                openDialer("0322662222");
            }
        });

        wao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                openDialer("0330008858");
            }
        });

        bomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "1234567890" with the actual phone number
                openDialer("0392217222");
            }
        });
    }

    private void openDialer(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        Uri phoneNumberUri = Uri.parse("tel:" + phoneNumber);
        dialIntent.setData(phoneNumberUri);
        startActivity(dialIntent);
    }
}