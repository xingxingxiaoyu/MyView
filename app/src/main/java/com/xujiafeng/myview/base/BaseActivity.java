package com.xujiafeng.myview.base;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


/**
 * 描述信息：
 *
 * @author xujiafeng
 * @date 2020-04-29
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";

//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        if ("FrameLayout".equals(name)) {
//            int count = attrs.getAttributeCount();
//            for (int i = 0; i < count; i++) {
//                String attributeName = attrs.getAttributeName(i);
//                String attributeValue = attrs.getAttributeValue(i);
//                if (attributeName.equals("id")) {
//                    int id = Integer.parseInt(attributeValue.substring(1));
//                    String idVal = getResources().getResourceName(id);
//                    if ("android:id/content".equals(idVal)) {
//                        BlackFrameLayout blackFrameLayout = new BlackFrameLayout(context, attrs);
//                        TypedValue a = new TypedValue();
//                        getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
//                        Log.d(TAG, "onCreateView: " + a + " a.data " + Integer.toHexString(a.data) + " a.resourceId " + a.resourceId);
//                        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
//                            // windowBackground is a color
//                            int color = a.data;
//                            blackFrameLayout.setBackgroundDrawable(new ColorDrawable(color));
//
//                        } else {
//                            // windowBackground is not a color, probably a drawable
//                            Drawable c = getResources().getDrawable(a.resourceId);
//                            blackFrameLayout.setBackgroundDrawable(c);
//
//                        }
//                        return blackFrameLayout;
//                    }
//                }
//            }
//        }
//        return super.onCreateView(name, context, attrs);
//    }
}
