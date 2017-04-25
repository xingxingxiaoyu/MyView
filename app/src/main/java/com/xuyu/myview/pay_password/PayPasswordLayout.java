package com.xuyu.myview.pay_password;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuyu.myview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/4/25.
 * <p>
 * 描述：输入类描述
 */
public class PayPasswordLayout extends FrameLayout implements TextWatcher, View.OnClickListener
{
    private View mView;
    private List<ImageView> mImageViewList;
    private EditText mEditText;
    private String password;

    public PayPasswordLayout(Context context)
    {
        this(context, null);
    }

    public PayPasswordLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(R.layout.pay_password_layout, this);
        init();
    }

    private void init()
    {
        ImageView imageView1 = (ImageView) mView.findViewById(R.id.image_1);
        ImageView imageView2 = (ImageView) mView.findViewById(R.id.image_2);
        ImageView imageView3 = (ImageView) mView.findViewById(R.id.image_3);
        ImageView imageView4 = (ImageView) mView.findViewById(R.id.image_4);
        ImageView imageView5 = (ImageView) mView.findViewById(R.id.image_5);
        ImageView imageView6 = (ImageView) mView.findViewById(R.id.image_6);
        mImageViewList = new ArrayList<>();
        mImageViewList.add(imageView1);
        mImageViewList.add(imageView2);
        mImageViewList.add(imageView3);
        mImageViewList.add(imageView4);
        mImageViewList.add(imageView5);
        mImageViewList.add(imageView6);

        mEditText = (EditText) mView.findViewById(R.id.edittext);
        mEditText.addTextChangedListener(this);

        TextView tvFinish = (TextView) mView.findViewById(R.id.tv_finish);
        tvFinish.setOnClickListener(this);
        showPoint(0);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String string = s.toString();
        int length = string.length();
        if (length >= 0 && length <= 6)
        {
            showPoint(length);
        }
        if (length == 6)
        {
            password = string;
        }
    }

    private void showPoint(int length)
    {
        for (int i = 0; i < 6; i++)
        {
            if (i < length)
            {
                mImageViewList.get(i).setImageResource(R.drawable.black_point);
            }
            else
            {
                mImageViewList.get(i).setImageBitmap(null);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public void onClick(View v)
    {
        if (password==null||password.length() != 6)
        {
            Toast.makeText(this.getContext(), "请输入六位数密码", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (mOnFinish != null)
            {
                mOnFinish.finish(password);
            }
        }
    }

    private OnFinish mOnFinish;

    public void setOnFinish(OnFinish onFinish)
    {
        mOnFinish = onFinish;
    }

    public interface OnFinish
    {
        void finish(String string);
    }
}
