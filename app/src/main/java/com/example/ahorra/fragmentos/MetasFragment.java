package com.example.ahorra.fragmentos;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ahorra.R;
import com.example.ahorra.clases.Menu;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MetasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MetasFragment extends Fragment {

    private final static int BOTONES[] ={R.id.frgMenImbInicio, R.id.frgMenImbMetas, R.id.frgMenImbAprender, R.id.frgMenImbHistorial};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MetasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MetasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MetasFragment newInstance(String param1, String param2) {
        MetasFragment fragment = new MetasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_metas, container, false);
        ImageButton imgboton;
        for (int i = 0; i < BOTONES.length; i++) {
            imgboton = vista.findViewById(BOTONES[i]);
            final int ID = i;
            imgboton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity activity = getActivity();
                    ((Menu) activity).onClickMenu(ID);
                }
            });
        }
        return vista;
    }
}