package com.example.ahorra.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import com.example.ahorra.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        final int[] BOTONES = {
                R.id.frgMenImbInicio,
                R.id.frgMenImbMetas,
                R.id.frgMenImbAprender,
                R.id.frgMenImbHistorial
        };

        for (int i = 0; i < BOTONES.length; i++) {
            ImageButton imgboton = root.findViewById(BOTONES[i]);
            final int ID = i;
            if (imgboton != null) {
                imgboton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 0 => Inicio (BienvenidoActivity)
                        if (ID == 0) {
                            Log.d("MenuFragment", "Ir a BienvenidoActivity (inicio)");
                            Intent intent = new Intent(getActivity(), com.example.ahorra.actividades.BienvenidoActivity.class);
                            // trae la actividad existente al frente si existe (evita crear demasiadas instancias)
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            return;
                        }

                        // 1 => Metas (MetasActivity)
                        if (ID == 1) {
                            Log.d("MenuFragment", "Ir a MetasActivity (metas)");
                            Intent intent = new Intent(getActivity(), com.example.ahorra.actividades.MetasActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            return;
                        }

                        // Para los demás botones, usa la interfaz Menu (comportamiento anterior)
                        if (getActivity() instanceof com.example.ahorra.clases.Menu) {
                            ((com.example.ahorra.clases.Menu) getActivity()).onClickMenu(ID);
                        } else {
                            Log.w("MenuFragment", "Actividad no implementa Menu; id=" + ID);
                        }
                    }
                });
            } else {
                Log.w("MenuFragment", "Botón null para id index=" + i);
            }
        }

        return root;
    }
}
