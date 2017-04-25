package com.xuyu.myview.pay_password;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/4/25.
 * <p>
 * 描述：输入类描述
 */
public class PayPasswordTool
{
    public void showPayPassworsDialog(Context context, final InputFinishListener listener)
    {
        PayPasswordLayout payPasswordLayout = new PayPasswordLayout(context);
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(payPasswordLayout)
                .create();
        payPasswordLayout.setOnFinish(new PayPasswordLayout.OnFinish()
        {
            @Override
            public void finish(String string)
            {
                listener.inputFinish(string);
                if (alertDialog!=null&&alertDialog.isShowing())
                {
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }
    public interface InputFinishListener
    {
        void inputFinish(String password);
    }
}
