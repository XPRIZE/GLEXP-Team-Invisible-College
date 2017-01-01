package college.invisible.glx;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ppham on 2/23/16.
 */
public class SoundRecyclerAdapter extends RecyclerView.Adapter<SoundRecyclerAdapter.ViewHolder>
            implements RecyclerView.OnClickListener {

    private final List<SoundGram> sampleData = new ArrayList<>();

    public SoundRecyclerAdapter(RecyclerView rv) {
        rv.setAdapter(this);
        rv.setOnClickListener(this);
        addItem(0, "Hola mundo");
        addItem(0, "Bienvenuto");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.list_basic_item, parentViewGroup, false);


        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final SoundGram rowData = sampleData.get(position);
        viewHolder.nameView.setText(rowData.getName());

        viewHolder.itemView.setTag(rowData);
    }


    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void removeData (int position) {

        sampleData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd, String soundGramTitle) {
        String newName = positionToAdd + "";
        SoundGram sgram = new SoundGram(newName);
        sgram.setName(soundGramTitle);
        sampleData.add(positionToAdd, sgram);
        notifyItemInserted(positionToAdd);
    }

    @Override
    public void onClick(View v) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private final TextView nameView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(
                    R.id.name_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // v is the LinearLayout, it turns out, the parent.
            int position = this.getLayoutPosition();
            SoundGram data = (SoundGram) this.itemView.getTag();
            nameView.setText(data.getName());
            Log.d("Tag1", "At position + " + Integer.toString(position) + " " + v.toString());
        }
    }

}
