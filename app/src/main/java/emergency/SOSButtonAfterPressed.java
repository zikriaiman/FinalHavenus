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
                //String phoneNumber = "+601131566914";
                String[] emergencyContacts = {"+601131566914"};
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
        // Get the list of status options (you can replace it with your own array)
        final String[] reasonOptions = {"Accident/Injury", "Natural Disaster", "Medical Emergency", "Safety Concerns"};

        // Create a dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Status");

        // Set up the dropdown menu
        builder.setItems(reasonOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String selectedStatus = null;

                if (reasonOptions[which].equals("Accident/Injury")) {
                    selectedStatus = "Involved in an accident or injured, require immediate rescue or medical attention.";
                }
                else if (reasonOptions[which].equals("Natural Disaster")) {
                    selectedStatus = "Caught in a natural disaster (e.g., earthquake, flood), need rescue and evacuation.";
                }
                else if (reasonOptions[which].equals("Medical Emergency")) {
                    selectedStatus = "I'm experiencing a severe medical issue and need urgent assistance.";
                }
                else if (reasonOptions[which].equals("Safety Concerns")) {
                    selectedStatus = "Feeling threatened or in an unsafe situation, need immediate help.";
                }

                // Handle the selected status, you might want to send an SMS or perform other actions
                sendStatusUpdate(selectedStatus);

                // Close the dialog (optional)
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void sendStatusUpdate(String selectedStatus) {
        // Implement logic to send a status update message
        // Use location services to get the current location if needed
        // Send SMS or use other communication methods to notify emergency contacts

        // Example: Format the message with the selected status
        String message = "Reason for SOS: " + selectedStatus;

        // Replace with the actual phone numbers of your emergency contacts
        String[] emergencyContacts = {"+601131566914"};

        // Send SMS to emergency contacts
        for (String contact : emergencyContacts) {
            sendSMS(contact, message);
        }
    }

    public void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            // Success message (optional)
            Toast.makeText(this, "Alert sent successfully", Toast.LENGTH_SHORT).show();

            // Additional actions (optional)
            // For example, you might want to log the successful sending of the alert
            // Log.d("SendSMS", "Alert sent to " + phoneNumber + ": " + message);

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
            Toast.makeText(this, "Failed to send alert. Please try again.", Toast.LENGTH_SHORT).show();

            // Additional error handling (optional)
            // For example, you might want to log the error for debugging purposes
            // Log.e("SendSMS", "Error sending alert to " + phoneNumber + ": " + e.getMessage());
        }
    }
}