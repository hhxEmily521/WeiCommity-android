package com.sexample.emily.myapplication.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sexample.emily.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubRegistNormal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubRegistNormal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubRegistNormal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view,view2;
    private  ImageButton Imagebtn_PersonIcon;
    private Button btn_register;
    private RegistActivity camera;

    private OnFragmentInteractionListener mListener;

    public SubRegistNormal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubRegistNormal.
     */
    // TODO: Rename and change types and number of parameters
    public static SubRegistNormal newInstance(String param1, String param2) {
        SubRegistNormal fragment = new SubRegistNormal();
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
        view= inflater.inflate(R.layout.fragment_sub_regist_music, container,false);
//        Imagebtn_PersonIcon = (ImageButton)view.findViewById(R.id.iv_personal_icon);
//        btn_register= (Button)view.findViewById(R.id.email_sign_in_button);
//        camera= (RegistActivity) getActivity();
      return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Imagebtn_PersonIcon = (ImageButton)view.findViewById(R.id.iv_personal_icon);
        btn_register= (Button)view.findViewById(R.id.email_sign_in_button);
        camera= (RegistActivity) getActivity();
        Imagebtn_PersonIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test", "onClick: fgsdrgsdgsdfgsdgsdgsdfgslalala");
                  camera.showChoosePicDialog();
                Toast.makeText(getActivity(), "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.showChoosePicDialog();

                Toast.makeText(getActivity(), "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
            }

        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {//Fragment和activity建立关联时调用。
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {//Fragment和activity解除关联时调用。
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
