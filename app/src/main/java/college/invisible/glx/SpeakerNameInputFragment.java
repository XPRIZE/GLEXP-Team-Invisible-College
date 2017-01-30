package college.invisible.glx;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpeakerNameInputFragment.SpeakerNameInputListener} interface
 * to handle interaction events.
 * Use the {@link SpeakerNameInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeakerNameInputFragment extends Fragment {

    private SpeakerNameInputListener mListener;

    public SpeakerNameInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SpeakerNameInputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpeakerNameInputFragment newInstance() {
        SpeakerNameInputFragment fragment = new SpeakerNameInputFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_speaker_name_input, container, false);

        final Button submitButton = (Button) rootView.findViewById(R.id.speakername_button);
        final EditText speakerNameEdit = (EditText) rootView.findViewById(R.id.edit_speaker_name);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speakerName = speakerNameEdit.getText().toString();
                if (mListener != null) {
                    mListener.onSpeakerNameInput(speakerName);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SpeakerNameInputListener) {
            mListener = (SpeakerNameInputListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface SpeakerNameInputListener {
        // TODO: Update argument type and name
        void onSpeakerNameInput(String speakerName);
    }
}
