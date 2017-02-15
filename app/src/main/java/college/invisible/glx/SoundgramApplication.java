package college.invisible.glx;

import android.app.Application;
import android.os.Environment;

import com.parse.Parse;
import com.parse.ParseConfig;
import com.parse.ParseObject;

import college.invisible.soundgram.SoundGram;
import college.invisible.soundgram.SoundGramController;
import college.invisible.soundgram.SoundGramSample;

/**
 * Created by ppham on 2/9/17.
 */

public class SoundGramApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Define sub classes
        ParseObject.registerSubclass(SoundGram.class);
        ParseObject.registerSubclass(SoundGramSample.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("sgrams")
                        .clientKey(null)
                        .server("http://soundgrams.herokuapp.com/parse/")
                .build());

        // Initialize soundgram controller with external storage
        SoundGramController.getInstance(Environment.getExternalStorageDirectory());

        // As a test, try forcing a read of all soundgrams and samples from SD
        // and upload to Parse server
        SoundGramController.getInstance().uploadToParse();
    }

}
