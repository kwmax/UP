package com.kwmax.up.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kwmax.up.R;
import com.kwmax.up.db.DiaryReviewsOperation;
import com.kwmax.up.model.DiaryReview;
import java.util.List;

/**
 * Created by keweimeng on 2019/3/10.
 */
public class NoteTabFragment extends BasicFragment {

    private RelativeLayout noteAddLayout;
    private RelativeLayout noteEditLayout;
    private RelativeLayout noteShowLayout;

    private TextView noteAdd;
    private EditText noteEditTitle;
    private EditText noteEditContent;
    private TextView noteShowTitle;
    private TextView noteShowContent;
    private Button noteSave;
    private Button noteEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View noteView = inflater.inflate(R.layout.fragment_note, container, false);

        TextView title = noteView.findViewById(R.id.top_text);
        title.setText("复盘");

        noteAddLayout = noteView.findViewById(R.id.frame_note_add);
        noteEditLayout = noteView.findViewById(R.id.frame_note_edit);
        noteShowLayout = noteView.findViewById(R.id.frame_note_show);

        noteAdd = noteView.findViewById(R.id.note_add);
        noteEditTitle = noteView.findViewById(R.id.note_edit_title);
        noteEditContent = noteView.findViewById(R.id.note_edit_content);
        noteShowTitle = noteView.findViewById(R.id.note_show_title);
        noteShowContent = noteView.findViewById(R.id.note_show_content);

        noteSave = noteView.findViewById(R.id.note_save);
        noteEdit = noteView.findViewById(R.id.note_edit);

        refreshAddLayout(true);

        noteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshEditLayout(true);
            }
        });
        noteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(noteEditTitle.getText().toString()) &&
                    !TextUtils.isEmpty(noteEditContent.getText().toString())){
                    DiaryReview review = new DiaryReview();
                    review.setName(noteEditTitle.getText().toString());
                    review.setContent(noteEditContent.getText().toString());
                    DiaryReviewsOperation.insertData(getActivity(),review);
                    refreshShowLayout(true);
                    refreshData();
                }
            }
        });
        noteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshEditLayout(true);
            }
        });

        return noteView;
    }

    private void refreshData(){
        List<DiaryReview> reviews = DiaryReviewsOperation.queryAll(getActivity());
        if (reviews!= null && reviews.size()>0){
            refreshShowLayout(true);
            noteShowTitle.setText(reviews.get(0).getName());
            noteShowContent.setText(reviews.get(0).getContent());
        }
    }

    private void refreshAddLayout(boolean visible){
        noteAddLayout.setVisibility(visible?View.VISIBLE:View.GONE);
        noteEditLayout.setVisibility(visible?View.GONE:View.VISIBLE);
        noteShowLayout.setVisibility(visible?View.GONE:View.VISIBLE);
    }

    private void refreshEditLayout(boolean visible){
        noteEditLayout.setVisibility(visible?View.VISIBLE:View.GONE);
        noteAddLayout.setVisibility(visible?View.GONE:View.VISIBLE);
        noteShowLayout.setVisibility(visible?View.GONE:View.VISIBLE);
    }

    private void refreshShowLayout(boolean visible){
        noteEditLayout.setVisibility(visible?View.GONE:View.VISIBLE);
        noteAddLayout.setVisibility(visible?View.GONE:View.VISIBLE);
        noteShowLayout.setVisibility(visible?View.VISIBLE:View.GONE);
    }

}
