package com.leibown.lcfn_library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.leibown.lcfn_library.R;

public class ShopCarCountDialog extends Dialog implements View.OnClickListener {

    private OnCountChangeListener onCountChangeListener;

    private int count;
    private EditText editText;

    public ShopCarCountDialog(@NonNull Context context, int count, OnCountChangeListener onCountChangeListener) {
        super(context, R.style.CustomStyle);
        this.onCountChangeListener = onCountChangeListener;
        this.count = count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_shop_car_count);
        findViewById(R.id.iv_less).setOnClickListener(this);
        findViewById(R.id.iv_add).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);

        editText = findViewById(R.id.tv_num);
        editText.setText(String.valueOf(count));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() <= 0) {
                    count = 1;
                    editText.removeTextChangedListener(this);
                    editText.setText(String.valueOf(count));
                    editText.setSelection(1);
                    editText.addTextChangedListener(this);
                    onCountChange();
                    return;
                }
                int i = Integer.parseInt(s.toString());
                if (i >= 300) {
                    count = 300;
                    editText.removeTextChangedListener(this);
                    editText.setText(String.valueOf(count));
                    editText.setSelection(3);
                    editText.addTextChangedListener(this);
                } else if (i <= 0) {
                    count = 1;
                    editText.removeTextChangedListener(this);
                    editText.setText(String.valueOf(count));
                    editText.setSelection(1);
                    editText.addTextChangedListener(this);
                } else {
                    count = i;
                    editText.setSelection(s.length());
                }
                onCountChange();
            }
        });
    }

    private void onCountChange() {
        if (onCountChangeListener != null)
            onCountChangeListener.onChange(count);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_less) {
            less();
        } else if (i == R.id.iv_add) {
            add();
        } else if (i == R.id.tv_cancel) {
            dismiss();
        } else if (i == R.id.tv_confirm) {
            if (onCountChangeListener != null)
                onCountChangeListener.onConfirm(count);
        }
    }

    private void add() {
        if (count < 300) {
            count++;
            editText.setText(String.valueOf(count));
        }
    }

    private void less() {
        if (count > 1) {
            count--;
            editText.setText(String.valueOf(count));
        }
    }

    public interface OnCountChangeListener {

        void onChange(int count);

        void onConfirm(int count);
    }
}
