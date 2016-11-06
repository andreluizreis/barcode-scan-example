package andreluizreis.barcodedetect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_camera);

        // Camera Button
        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            scanBarcode();
            }
        });
    }

    private void scanBarcode(){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * This method is called after CameraActivity returns the result.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    TextView resultText = (TextView) findViewById(R.id.resultText);
                    resultText.setText("Barcode = " + barcode.displayValue);
                } else {
                    TextView resultText = (TextView) findViewById(R.id.resultText);
                    resultText.setText("No barcode found.");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
