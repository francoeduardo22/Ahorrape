package com.example.ahorra.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull; // <-- AÑADIDO
import androidx.annotation.Nullable; // <-- AÑADIDO
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation; // <-- AÑADIDO

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton; // <-- AÑADIDO

import com.example.ahorra.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AprenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AprenderFragment extends Fragment {

    // ... (Tu código de mParam1, mParam2, etc. no cambia) ...
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public AprenderFragment() {
        // Required empty public constructor
    }

    /**
     * ... (Tu método newInstance no cambia) ...
     */
    public static AprenderFragment newInstance(String param1, String param2) {
        AprenderFragment fragment = new AprenderFragment();
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
        return inflater.inflate(R.layout.fragment_aprender, container, false);
    }

    // ========================================================
    // == LÓGICA DEL PASO 1 AÑADIDA AQUÍ ==
    // ========================================================
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Encontrar los ImageButtons por su ID
        ImageButton imgbtModulo1 = view.findViewById(R.id.imgbtModulo1);
        ImageButton imgbtModulo2 = view.findViewById(R.id.imgbtModulo2);
        ImageButton imgbtModulo3 = view.findViewById(R.id.imgbtModulo3);

        // 2. Asignar el OnClickListener para Modulo 1
        imgbtModulo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Llamar a la acción de navegación que definiste en nav_graph.xml
                Navigation.findNavController(v).navigate(R.id.action_aprenderFragment_to_leccionFragment);
            }
        });

        // 3. Asignar el OnClickListener para Modulo 2
        imgbtModulo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // (Por ahora, todos van a la misma lección.
                // Si quieres que vayan a lecciones diferentes,
                // tendrías que crear más <action> en tu nav_graph.xml)
                Navigation.findNavController(v).navigate(R.id.action_aprenderFragment_to_leccionFragment);
            }
        });

        // 4. Asignar el OnClickListener para Modulo 3
        imgbtModulo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_aprenderFragment_to_leccionFragment);
            }
        });
    }
}