package college.invisible.glx;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import college.invisible.soundgram.SoundGramController;
import college.invisible.transporter.BluetoothController;

public class MainActivity extends AppCompatActivity
        implements SoundGramController.NotificationListener,
            SoundGramInputFragment.SoundGramInputListener,
            SpeakerNameInputFragment.SpeakerNameInputListener {

    public final static String LOG_TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private SoundRecyclerAdapter mAdapter;
    private final static int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 22;
    private BluetoothController mBluetoothController;
    private SoundGramController mSoundgramController;
    private FragmentManager mFragMan;
    private SoundGramInputFragment mSoundGramInputFrag;
    private SpeakerNameInputFragment mSpeakerNameInputFrag;

    public MainActivity() {
        mSoundgramController = SoundGramController.getInstance(Environment.getExternalStorageDirectory());
        mSoundgramController.setNotifier(this);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mFragMan = getSupportFragmentManager();

        mBluetoothController = BluetoothController.getInstance(this);

        // Programmatically set the layout
        LinearLayout ll = new LinearLayout(this);

        LayoutInflater inflater =
                (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //RecyclerView mRecyclerView = new RecyclerView(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        //RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SoundRecyclerAdapter(mRecyclerView);
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
                //mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                //SoundGramInputFragment frag = (SoundGramInputFragment) fragMan.findFragmentById(R.id.soundgram_input_frag);
                //assert(frag != null);
                mSoundGramInputFrag = SoundGramInputFragment.newInstance("I'm a param!", "I'm another param!");
                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = mFragMan.beginTransaction();
                ft.replace(R.id.layout_frag_foreground, mSoundGramInputFrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                View fragLayout = (View) findViewById(R.id.layout_frag_foreground);
                fragLayout.setLayoutParams(new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.WRAP_CONTENT,
                        AppBarLayout.LayoutParams.WRAP_CONTENT));
                ft.commit();
            }
        });

        FloatingActionButton transporterFab = (FloatingActionButton) findViewById(R.id.transporter_fab);
        transporterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothController.scanForDevices();
            }
        });

        FloatingActionButton speakerFab = (FloatingActionButton) findViewById(R.id.speaker_fab);
        speakerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpeakerNameInputFrag = SpeakerNameInputFragment.newInstance();
                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = mFragMan.beginTransaction();
                ft.replace(R.id.layout_frag_foreground, mSpeakerNameInputFrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                View fragLayout = (View) findViewById(R.id.layout_frag_foreground);
                fragLayout.setLayoutParams(new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.WRAP_CONTENT,
                        AppBarLayout.LayoutParams.WRAP_CONTENT));
                ft.commit();

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

    @Override
    public void onSoundGramInput(String displayName) {
        FragmentTransaction ft = mFragMan.beginTransaction();

        ft.detach(mSoundGramInputFrag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();

        mAdapter.addItem(0, displayName);
    }

    @Override
    public void onSpeakerNameInput(String speakerName) {
        FragmentTransaction ft = mFragMan.beginTransaction();

        ft.detach(mSpeakerNameInputFrag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();

        SoundGramController.getInstance().setSpeakerName(speakerName);

        Log.i(LOG_TAG, "New speaker name: " + speakerName);
    }

}
