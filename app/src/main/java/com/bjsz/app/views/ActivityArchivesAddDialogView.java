package com.bjsz.app.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bjsz.app.R;

/**
 * 自定义对话框
 * @author enmaoFu
 * @date 2017-02-06
 */
public class ActivityArchivesAddDialogView extends Dialog {
    private EditText editText;
    private Button positiveButton, negativeButton;
    private TextView title;

    public ActivityArchivesAddDialogView(Context context,String str,String hiht) {
        super(context);
        setCustomDialog(str,hiht);
    }

    private void setCustomDialog(String str,String hiht) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.activity_archives_add_dialog, null);
        title = (TextView) mView.findViewById(R.id.title);
        title.setText(str);
        editText = (EditText) mView.findViewById(R.id.str);
        editText.setHint(hiht);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }


    public View getEditText(){
        return editText;
    }

    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }
}