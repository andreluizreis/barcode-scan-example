package andreluizreis.barcodedetect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListView();

    }// onCreate


    void createListView() {
        // List View
        listView = (ListView) findViewById(R.id.list_view);

        // List View Content
        String[] listViewContent = {"Barcode Example",
                "Face Tracker Example",
                "Barcode Camera Example"};

        // List View Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listViewContent);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                //String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/

                // Change Activity
                Intent intent;
                switch (itemPosition) {
                    case 0:
                        intent = new Intent(MainActivity.this, BarcodeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, FaceTrackerActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, BarcodeCameraActivity.class);
                        startActivity(intent);
                        break;
                    default:
                }

            }
        });

    }

}
