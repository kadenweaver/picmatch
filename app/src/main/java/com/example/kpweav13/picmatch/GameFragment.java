package com.example.kpweav13.picmatch;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.os.Handler;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Drawable> pics = new ArrayList<Drawable>();
    ArrayList<Drawable> mixpics = new ArrayList<Drawable>();
    HashMap<ImageButton, Drawable> map;
    HashMap<Integer,Integer> ibtod;
    HashMap<Integer,Drawable> newmap;
    HashMap<Drawable, Integer> picIdent;
    HashMap<Integer,Boolean> status;
    HashMap<Integer,Integer> idkeep;
    Handler h = new Handler();
    ImageButton first = null;


    int counteram=0;
    int gamestat=7;
    private int mCorrect, mWrong;
    private SoundPool mSoundPool;
    private float mVolume = 1f;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        pics = new ArrayList<Drawable>();
        mixpics = new ArrayList<Drawable>();
        map = new HashMap<ImageButton,Drawable>();
        ibtod = new HashMap<Integer, Integer>();
        newmap = new HashMap<>();
        idkeep = new HashMap<>();
        picIdent = new HashMap<Drawable,Integer>();
        status = new HashMap<>();

        h = new Handler();

        counteram = 0;
        gamestat = 7;


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mCorrect = mSoundPool.load(getActivity(), R.raw.correct, 1);
        mWrong = mSoundPool.load(getActivity(), R.raw.wrong, 1);
        pics.add(getResources().getDrawable(R.drawable.pic1));
        pics.add(getResources().getDrawable(R.drawable.pic2));
        pics.add(getResources().getDrawable(R.drawable.pic3));
        pics.add(getResources().getDrawable(R.drawable.pic4));
        pics.add(getResources().getDrawable(R.drawable.pic5));
        pics.add(getResources().getDrawable(R.drawable.pic6));
        pics.add(getResources().getDrawable(R.drawable.pic7));
        pics.add(getResources().getDrawable(R.drawable.pic8));
        pics.add(getResources().getDrawable(R.drawable.pic9));
        pics.add(getResources().getDrawable(R.drawable.pic10));
        pics.add(getResources().getDrawable(R.drawable.pic11));
        pics.add(getResources().getDrawable(R.drawable.pic12));
        pics.add(getResources().getDrawable(R.drawable.pic13));
        pics.add(getResources().getDrawable(R.drawable.pic14));
        pics.add(getResources().getDrawable(R.drawable.pic15));
        pics.add(getResources().getDrawable(R.drawable.pic16));
        Collections.shuffle(pics);
        mixpics.add(pics.get(9));
        picIdent.put(pics.get(9),9);
        mixpics.add(pics.get(0));
        picIdent.put(pics.get(0),0);
        mixpics.add(pics.get(1));
        picIdent.put(pics.get(1),1);
        mixpics.add(pics.get(2));
        picIdent.put(pics.get(2),2);
        mixpics.add(pics.get(4));
        picIdent.put(pics.get(4),4);
        mixpics.add(pics.get(3));
        picIdent.put(pics.get(3),3);
        mixpics.add(pics.get(6));
        picIdent.put(pics.get(6),6);
        mixpics.add(pics.get(5));
        picIdent.put(pics.get(5),5);
        mixpics.add(pics.get(2));
        mixpics.add(pics.get(0));
        mixpics.add(pics.get(3));
        mixpics.add(pics.get(5));
        mixpics.add(pics.get(4));
        mixpics.add(pics.get(9));
        mixpics.add(pics.get(6));
        mixpics.add(pics.get(1));
        for(int i = 1;i<17; i++ ){
            int id = getResources().getIdentifier("spot" +i, "id",getActivity().getPackageName());
            Drawable nd = mixpics.get(mixpics.size()-1);
            status.put(id,false);
            newmap.put(id, mixpics.remove(mixpics.size()-1));
            ibtod.put(id,picIdent.get(nd));
        }




    }




    public void check(ImageButton i){
        int firstid = ibtod.get(first.getId());

        int iid = ibtod.get(i.getId());
        if(firstid==iid){
            status.put(firstid,true);
            status.put(iid,true);
            first=null;
            gamestat--;
            mSoundPool.play(mCorrect, mVolume, mVolume, 1, 0, 1f);

        }
        else{

            mSoundPool.play(mWrong, mVolume, mVolume, 1, 0, 1f);
            status.put(firstid,false);
            status.put(iid,false);
            first.setImageDrawable(getResources().getDrawable(R.drawable.button));
            i.setImageDrawable(getResources().getDrawable(R.drawable.button));
            first.setEnabled(true);
            i.setEnabled(true);
            first = null;

        }
        ((GameActivity)getActivity()).stopThinking();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        Resources r = getResources();
       //((GameActivity)getActivity()).setClick(counteram);


        for(int i =1; i<17; i++){
            int id = r.getIdentifier("spot"+i,"id", getActivity().getPackageName());
            final ImageButton ib = (ImageButton) root.findViewById(id);
            if(status.get(id)){
                ib.setImageDrawable(newmap.get(id));
                ib.setEnabled(false);
            }


            ib.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    ((GameActivity)getActivity()).addClick(counteram);
                    counteram++;
                    if(first==null){

                        first = ib;
                        ib.setImageDrawable(newmap.get(ib.getId()));
                        status.put(ib.getId(),true);
                        ib.setEnabled(false);
                    }
                    else{
                        ib.setImageDrawable(newmap.get(ib.getId()));
                        ib.setEnabled(false);
                        ((GameActivity)getActivity()).startThinking();
                        Runnable c = new Runnable() {
                            @Override
                            public void run() {
                                check(ib);
                            }
                        };
                        h.postDelayed(c, 1000);
                        if(gamestat==0){
                            Runnable n = new Runnable() {
                                @Override
                                public void run() {
                                    ((GameActivity)getActivity()).asknewgame();
                                }
                            };
                            h.postDelayed(n,1000);
                        }

                    }




                }
            });
        }
        return root;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/


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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
