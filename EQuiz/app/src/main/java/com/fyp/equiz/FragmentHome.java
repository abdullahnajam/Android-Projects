package com.fyp.equiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

public class FragmentHome extends Fragment {
    View view;
    CardView firstButton;
    TextView signOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frame_home, container, false);
        // get the reference of Button

        signOut = (TextView) view.findViewById(R.id.s_sign_out);
        firstButton = (CardView) view.findViewById(R.id.TestOpenview);
        // perform setOnClickListener on first Button
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //using Lovely dailog library  https://github.com/yarolegovich/LovelyDialog
                new LovelyTextInputDialog(getActivity(), R.style.EditTextintTheme)
                        .setTopColorRes(R.color.titleGreen)
                        .setTitle(R.string.start)
                        .setMessage(R.string.enterKey)
                        .setIcon(R.drawable.ic_test)
                        .setInputFilter(R.string.enterKey, new LovelyTextInputDialog.TextFilter() {
                            @Override
                            public boolean check(String text) {
                                return text.matches("\\w+");
                            }
                        })
                        .setConfirmButton(R.string.action, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                //Condition to check enrollment key
                                /*if(text=="key")
                                {
                                    startActivity();
                                }*/
                                Intent intent = new Intent(getActivity(), TestView.class);
                                startActivity(intent);
                            }
                        })
                        .show();

            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return view;
    }


}
