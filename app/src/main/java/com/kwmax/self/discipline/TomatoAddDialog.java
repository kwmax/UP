package com.kwmax.self.discipline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by keweimeng on 2019/10/4.
 */
public class TomatoAddDialog extends DialogFragment {
    private static final String TITLE = "title";
    private operateTomatoListener operateTomatoListener;

    private String title;

    public static TomatoAddDialog getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        TomatoAddDialog tomatoAddDialog = new TomatoAddDialog();
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
        final View view = inflater.inflate(R.layout.dialog_layout, null);

        TextView tv_title = (TextView) view.findViewById(R.id.top_text);
        ImageView menu1 = (ImageView) view.findViewById(R.id.menu1);
        ImageView menu2 = (ImageView) view.findViewById(R.id.menu2);
        menu1.setVisibility(View.VISIBLE);
        menu2.setVisibility(View.VISIBLE);
        menu1.setImageDrawable(getActivity().getDrawable(R.drawable.confirm));
        menu2.setImageDrawable(getActivity().getDrawable(R.drawable.close));

        final EditText content = (EditText) view.findViewById(R.id.todo_content);
        final EditText dura = (EditText) view.findViewById(R.id.todo_dura);

        tv_title.setText(title);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateTomatoListener != null){
                    operateTomatoListener.addTomato(content.getText().toString(),dura.getText().toString());
                }
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateTomatoListener != null){
                    operateTomatoListener.cancal();
                }
            }
        });

        builder.setView(view);
        return builder.create();
    }

}
