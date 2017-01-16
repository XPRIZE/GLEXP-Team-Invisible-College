package college.invisible.glx;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import college.invisible.soundgram.SoundGramController;
import college.invisible.soundgram.SoundGramControls;
import college.invisible.transporter.BluetoothController;

public class MainActivity extends AppCompatActivity
        implements SoundGramController.NotificationListener {

    private RecyclerView mRecyclerView;
    private final static int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 22;
    private BluetoothController mBluetoothController;
    private SoundGramController mSoundgramController;

    public MainActivity() {
        mSoundgramController = SoundGramController.getInstance();
        mSoundgramController.setNotifier(this);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mBluetoothController = BluetoothController.getInstance(this);

        // Programmatically set the layout
        LinearLayout ll = new LinearLayout(this);

        LayoutInflater inflater =
                (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //RecyclerView mRecyclerView = new RecyclerView(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SoundRecyclerAdapter mAdapter = new SoundRecyclerAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

/*
        ll.addView(mRecyclerView,
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
*/
        // specify an adapter (see also next example)

        //mAdapter.addItem(0, "sheep", "ba ba black sheep");
        //mAdapter.addItem(1, "threeBags", "three bags full");
        //inflater.inflate(R.layout.content_sound, mRecyclerView, true);
        /*
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.layout.app_bar_layout);
        appBarLayout.addView(ll,
                new AppBarLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                */
        //setContentView(ll);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.


            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        notifyUser("I'm alive!");
        //Toast.makeText(this, "I SAID I'm alive!", Toast.LENGTH_SHORT).show();

        FloatingActionButton soundgramFab = (FloatingActionButton) findViewById(R.id.soundgram_fab);
        soundgramFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSoundgramController.addNewSoundgram();
            }
        });

        FloatingActionButton transporterFab = (FloatingActionButton) findViewById(R.id.transporter_fab);
        transporterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothController.scanForDevices();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void notifyUser(String message) {
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_LONG);
    }
}
