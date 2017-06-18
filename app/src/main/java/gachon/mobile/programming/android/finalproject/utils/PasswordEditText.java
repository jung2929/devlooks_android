package gachon.mobile.programming.android.finalproject.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tsengvn.typekit.Typekit;

import gachon.mobile.programming.android.finalproject.R;

import static gachon.mobile.programming.android.finalproject.utils.ApplicationClass.CUSTOM_FONT;

/**
 * Created by JJSoft on 2017-05-10.
 */

public class PasswordEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
    private Drawable mTransparentDrawable;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;

    public PasswordEditText(final Context context) {
        super(context);
        init();
    }

    public PasswordEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener onFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(final OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    private void init() {
        final Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_password_black_24dp);
        mTransparentDrawable = DrawableCompat.wrap(tempDrawable);
        DrawableCompat.setTintList(mTransparentDrawable, getHintTextColors());
        mTransparentDrawable.setBounds(0, 0, mTransparentDrawable.getIntrinsicWidth(), mTransparentDrawable.getIntrinsicHeight());

        setClearIconVisible(false);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void onFocusChange(final View view, final boolean isFocus) {
        if (isFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(view, isFocus);
        }
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mTransparentDrawable.isVisible() && x > getWidth() - getPaddingRight() - mTransparentDrawable.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (getInputType() == InputType.TYPE_CLASS_TEXT) {
                    setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    setInputType(InputType.TYPE_CLASS_TEXT);
                }
                setTypeface(Typekit.createFromAsset(getContext(), CUSTOM_FONT));
            }
            return true;
        }

        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override
    public void onTextChanged(final CharSequence charSequence, final int start, final int before, final int count) {
        if (isFocused()) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void setClearIconVisible(final boolean isVisible) {
        mTransparentDrawable.setVisible(isVisible, false);
        setCompoundDrawables(null, null, isVisible ? mTransparentDrawable : null, null);
    }
}
