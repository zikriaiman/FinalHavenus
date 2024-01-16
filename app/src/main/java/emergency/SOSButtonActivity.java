package emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.finalhavenus.R;

public class SOSButtonActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private static final long LONG_PRESS_DURATION = 10000; // 5 seconds
    private Handler handler = new Handler();
    private boolean isLongPress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_button);

        Button sosButton = findViewById(R.id.sosButton);
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                isLongPress = true;
                handleLongPress();
            }
        });

        sosButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null);
                    if (!isLongPress) {
                        // If it's a short press, perform regular click action
                        performClickAction();
                    }
                    isLongPress = false;
                }
                return true;
            }
        });
    }

    private void performClickAction() {
        Toast.makeText(this, "Long press SOS button for 5 seconds to send alert.", Toast.LENGTH_SHORT).show();
    }

    private void handleLongPress() {
        // Implement your logic for long press (5 seconds) here
        if (checkLocationPermission()) {
            getLocation();
            navigateToSOSButtonAfterPressed();
        } else {
            requestLocationPermission();
        }
    }

    private boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
                navigateToSOSButtonAfterPressed();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private double latitude;
    private double longitude;

    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

            Location lastKnownLocationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location lastKnownLocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location lastKnownLocation = (lastKnownLocationGPS != null) ? lastKnownLocationGPS : lastKnownLocationNetwork;

            if (lastKnownLocation != null) {
                latitude = lastKnownLocation.getLatitude();
                longitude = lastKnownLocation.getLongitude();

                sendSOSMessage(latitude, longitude);
            } else {
                Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show();
            }
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

    private void sendSOSMessage(double latitude, double longitude) {
        // Implement your logic to send the SOS message with location here
        // You can use SMS, email, or any other communication method
        // Compose a message with the current location
        //String phoneNumber = "+60169097647";
        String[] emergencyContacts = {"+601131566914", "+60169097647"};
        String message = "SOS! This message is to notify you that I am in an emergency. My current location is: https://maps.google.com/?q=" + latitude + "," + longitude;
        // Replace with the actual phone numbers of your emergency contacts


        // Send SMS to emergency contacts
        for (String contact : emergencyContacts) {
            sendSMS(contact, message);
        }
    }

    private void navigateToSOSButtonAfterPressed() {
        Intent intent = new Intent(this, SOSButtonAfterPressed.class);
        startActivity(intent);
    }
}
