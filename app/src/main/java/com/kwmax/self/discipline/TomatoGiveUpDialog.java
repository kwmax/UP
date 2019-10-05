package com.kwmax.self.discipline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kwmax on 2019/10/4.
 */
public class TomatoGiveUpDialog extends DialogFragment {
    private static final String TITLE = "title";
    private operateTomatoListener operateTomatoListener;
    private String title;

    public static TomatoGiveUpDialog getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        TomatoGiveUpDialog tomatoAddDialog = new TomatoGiveUpDialog();
        tomatoAddDialog.setArguments(bundle);
        return tomatoAddDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(TITLE);
    }

    public void show(FragmentManager fragmentManager, operateTomatoListener operateTomatoListener) {
        this.operateTomatoListener = operateTomatoListener;
        show(fragmentManager, "TomatoAddDialogFragment");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_tomato_giveup, null);

        TextView tv_title = (TextView) view.findViewById(R.id.top_text);
        tv_title.setText(title);

        final EditText reason = (EditText) view.findViewById(R.id.giveup_reason);
        Button cancel = (Button) view.findViewById(R.id.giveup_cancel);
        final Button confirm = (Button) view.findViewById(R.id.giveup_confirm);

        reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(reason.getText().toString())){
                    confirm.setClickable(true);
                    confirm.setTextColor(getResources().getColor(R.color.black));
                }else {
                    confirm.setClickable(false);
                    confirm.setTextColor(getResources().getColor(R.color.gray));
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateTomatoListener != null){
                    operateTomatoListener.cancal();
                }
               dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateTomatoListener != null){
                    operateTomatoListener.giveup(reason.getText().toString());
                }
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

}
