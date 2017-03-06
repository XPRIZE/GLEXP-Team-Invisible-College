package college.invisible.glx;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import college.invisible.soundgram.SoundGramController;
import college.invisible.soundgram.SoundGramSample;
import college.invisible.soundgram.SoundGram;

/**
 * Created by ppham on 2/23/16.
 */
public class SoundRecyclerAdapter extends RecyclerView.Adapter<SoundRecyclerAdapter.ViewHolder>
            implements RecyclerView.OnClickListener, SoundGram.NewSampleListener {

    private final String LOG_TAG = "SoundRecyclerAdapter";
    private final List<SoundGram> sampleData = SoundGramController.getInstance().getSoundGrams();

    public SoundRecyclerAdapter(RecyclerView rv) {
        rv.setAdapter(this);
        rv.setOnClickListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.list_basic_item, parentViewGroup, false);


        return new ViewHolder (rowView);
    }

    public void updateViewHolder(ViewHolder viewHolder, SoundGram data) {
        viewHolder.nameView.setText(data.getDisplayName());
        List<Button> playButtons = viewHolder.playButtons;
        Button recordButton = viewHolder.recordButton;
        List<SoundGramSample> samples = data.getSamples();
        LinearLayout ll = (LinearLayout) viewHolder.itemView;
        if (samples.size() > playButtons.size()) {
            // Add more buttons to accommodate more samples than current buttons
            int diff = samples.size() - playButtons.size();
            for (int i = 0; i < diff; i++) {
                // create buttons with same context as the single record button
                Button newButton = new Button(recordButton.getContext());
                playButtons.add(newButton);
                ll.addView(newButton);
            }
        } else if (samples.size() < playButtons.size()) {
            int diff = playButtons.size() - samples.size();
            for (int i = 0; i< diff; i++) {
                Button oldButton = playButtons.get(0);
                ll.removeView(oldButton);
                playButtons.remove(oldButton); // remove all at the beginning
            }
        } // else they are equal, and we don't add/remove anything.
        assert(playButtons.size() == samples.size());

        for (int i = 0; i < samples.size(); i++) {
            Button playButton = playButtons.get(i);
            SoundGramSample sample = samples.get(i);
            playButton.setText(sample.getSpeakerName());
            sample.setOnClickListener(playButton);
        }

        data.setRecordButtonOnClickListener(viewHolder.recordButton);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final SoundGram data = sampleData.get(position);
        data.setViewHolder(viewHolder);
        updateViewHolder(viewHolder, data);
        viewHolder.itemView.setTag(data);
    }


    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void removeData (int position) {

        sampleData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd, String displayTitle) {
        SoundGram sgram = SoundGram.createFromName(displayTitle);
        sampleData.add(positionToAdd, sgram);
        sgram.setNewSampleListener(this);
        notifyItemInserted(positionToAdd);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onNewSample(RecyclerView.ViewHolder viewHolder, SoundGram soundGram) {
        updateViewHolder((ViewHolder)viewHolder, soundGram);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private final TextView nameView;
        private final Button recordButton; // there is a single record button for each sound gram
        private final List<Button> playButtons; // there is a playbutton for each sample

        /*
         * The constructor is where we initialize this ViewHolder and its sub-views.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(
                    R.id.name_view);
            recordButton = (Button) itemView.findViewById(R.id.record_button);
            playButtons = new ArrayList<>();
            itemView.setOnClickListener(this);
        }

        /*
         * The onClick method is where we rebind soundgram data to a view when it becomes visible.
         * This involves updating the state of static members (whose numbers do not change between
         * data items), and removing / updating
         * dynamic items (whose numbers could change from data to data item).
         * The view is passed in by RecyclerAdapter base class.
         */
        @Override
        public void onClick(View v) {
            // v is the LinearLayout, it turns out, the parent.
            int position = this.getLayoutPosition();
            SoundGram data = (SoundGram) this.itemView.getTag();
            nameView.setText(data.getDisplayName());
            Log.d("Tag1", "At position + " + Integer.toString(position) + " " + v.toString());
        }
    }

}
