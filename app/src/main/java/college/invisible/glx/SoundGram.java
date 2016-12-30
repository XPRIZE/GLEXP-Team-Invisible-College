package college.invisible.glx;

import java.util.ArrayList;
import java.util.List;

/**
 * A combination of a word / phrase / sentence to pronounce
 * plus a collection of associated sound files
 * Created by ppham on 2/23/16.
 */
public class SoundGram {

    private String mName; /* Descriptive name for this soundgram */
    private List<String> mFilenames; /* Filenames of all recorded sounds */

    public SoundGram(String name) {
        this.mName = name;
        this.mFilenames = new ArrayList<String>();
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<String> getFilenames() {
        return this.mFilenames;
    }

    public void addFilename(String newFilename) {
        this.mFilenames.add(newFilename);
    }


}
