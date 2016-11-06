package andreluizreis.barcodedetect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        // Process Button
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcodeDetection();
            }
        });
    }

    /**
     * Barcode Detection
     */
    private void barcodeDetection(){

        // Get text view from activity_main.xml
        TextView txtView = (TextView) findViewById(R.id.txtContent);

        // Image View
        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.upca_barcode);
        myImageView.setImageBitmap(myBitmap);

        // Barcode Detection
        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.UPC_A | Barcode.UPC_E)
                        .build();
        if(!detector.isOperational()){
            txtView.setText("Could not set up the detector!");
            return;
        }

        // Detect the barcode
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        // Decode the barcode
        if(barcodes.size() == 0) {
            txtView.setText("No barcode detected.");
            return;
        }
        Barcode thisCode = barcodes.valueAt(0);
        txtView.setText(thisCode.rawValue);
    }
}
