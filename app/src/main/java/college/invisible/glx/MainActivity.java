package college.invisible.glx;

import android.content.Context;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import college.invisible.soundgram.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    public MainActivity() {
        SoundGramControls.checkAndCreateDirectory();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Programmatically set the layout
        LinearLayout ll = new LinearLayout(this);

        SoundGramControls sgc = new SoundGramControls(ll, this.getApplicationContext(), "First Gram");


        LayoutInflater inflater =
                (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //RecyclerView mRecyclerView = new RecyclerView(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
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

        mAdapter.addItem(0, "ba ba black sheep");
        mAdapter.addItem(1, "three bags full");
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
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
