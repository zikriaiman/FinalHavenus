package emergency;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhavenus.R;

import java.util.Arrays;

public class SOSButtonAfterPressed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_button_after_pressed);

        EditText EnterUpdateET = findViewById(R.id.EnterUpdateET);
        Button SendUpdateButton = findViewById(R.id.SendUpdateButton);
        Button ReasonsDialog = findViewById(R.id.ReasonsDialog);

        SendUpdateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String[] emergencyContacts = {"+601131566914", "+60169097647"};
                String update = EnterUpdateET.getText().toString();
                try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(Arrays.toString(emergencyContacts),null,update,null,null);
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Try again.",Toast.LENGTH_LONG).show();
                }
            }
        });

        ReasonsDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReasonsDialog();
            }
        });
    }

    private void showReasonsDialog() {
        // Get the list of reason options
        final String[] reasonOptions = {"Accident/Injury", "Natural Disaster", "Medical Emergency", "Safety Concerns"};

        // Create a dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Reason");

        // Set up the dropdown menu
        builder.setItems(reasonOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String selectedReason = null;

                if (reasonOptions[which].equals("Accident/Injury")) {
                    selectedReason = "Involved in an accident or injured, require immediate rescue or medical attention.";
                }
                else if (reasonOptions[which].equals("Natural Disaster")) {
                    selectedReason = "Caught in a natural disaster (e.g., earthquake, flood), need rescue and evacuation.";
                }
                else if (reasonOptions[which].equals("Medical Emergency")) {
                    selectedReason = "I'm experiencing a severe medical issue and need urgent assistance.";
                }
                else if (reasonOptions[which].equals("Safety Concerns")) {
                    selectedReason = "Feeling threatened or in an unsafe situation, need immediate help.";
                }

                sendReason(selectedReason);

                // Close the dialog
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void sendReason(String selectedReason) {
        String message = "Reason for SOS: " + selectedReason;

        String[] emergencyContacts = {"+601131566914", "+60169097647"};

        // Send SMS to emergency contacts
        for (String contact : emergencyContacts) {
            sendSMS(contact, message);
        }
    }

    public void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            // Success message
            Toast.makeText(this, "Alert sent successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (show an error message)
            Toast.makeText(this, "Failed to send alert. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
