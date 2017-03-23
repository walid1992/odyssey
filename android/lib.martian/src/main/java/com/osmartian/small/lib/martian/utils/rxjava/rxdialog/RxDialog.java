package com.osmartian.small.lib.martian.utils.rxjava.rxdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.osmartian.small.lib.martian.app.MartianApp;
import com.osmartian.small.lib.martian.mvp.MartianActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Observable;

/**
 * Author   : walid
 * Data     : 2016-09-22  15:14
 * Describe : RxDialog 封装,自动在activity destroy生命周期中进行dismiss操作,防止内存泄露
 */

public class RxDialog {

    public static Observable<DialogAction> on(Builder builder) {
        Preconditions.checkNotNull(builder, "builder is not empty !!!");
        return Observable
                .create(subscriber -> {
                    AlertDialog dialog = new AlertDialog.Builder(builder.activity)
                            .setCancelable(false)
                            .setTitle(builder.title)
                            .setView(builder.view)
                            .setMessage(builder.message)
                            .setPositiveButton(builder.positive, (dialogPosi, which) -> {
                                subscriber.onNext(new DialogAction(dialogPosi, DialogAction.ActionType.POSITIVE_CLICK));
                            })
                            .setNegativeButton(builder.negative, (dialogNage, which) -> {
                                dialogNage.dismiss();
                                subscriber.onNext(new DialogAction(dialogNage, DialogAction.ActionType.NEGATIVE_CLICK));
                            })
                            .setOnKeyListener((dialog1, keyCode, event) -> {
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                    dialog1.dismiss();
                                    subscriber.onNext(new DialogAction(dialog1, DialogAction.ActionType.KEY_BACK_CLICK));
                                    return true;
                                }
                                return false;
                            })
                            .show();

                    if (builder.activity instanceof MartianActivity) {
                        MartianActivity martianActivity = (MartianActivity) builder.activity;
                        martianActivity.lifecycle().subscribe(activityEvent -> {
                            if (activityEvent == ActivityEvent.DESTROY) {
                                dialog.dismiss();
                                if (subscriber.isUnsubscribed()) {
                                    subscriber.unsubscribe();
                                }
                            }
                        });
                    }

                });
    }

    public static class DialogAction {

        @IntDef({ActionType.POSITIVE_CLICK, ActionType.NEGATIVE_CLICK, ActionType.KEY_BACK_CLICK})
        @Retention(RetentionPolicy.SOURCE)
        public @interface ActionType {
            int POSITIVE_CLICK = 0;
            int NEGATIVE_CLICK = 1;
            int KEY_BACK_CLICK = 2;
        }

        private DialogInterface dialog;
        @ActionType
        private int actionType;

        public DialogAction(DialogInterface dialog, int actionType) {
            this.dialog = dialog;
            this.actionType = actionType;
        }

        public DialogInterface getDialog() {
            return dialog;
        }

        public void setDialog(DialogInterface dialog) {
            this.dialog = dialog;
        }

        public int getActionType() {
            return actionType;
        }

        public void setActionType(int actionType) {
            this.actionType = actionType;
        }

        public boolean isPositiveClick() {
            return actionType == ActionType.POSITIVE_CLICK;
        }

        public boolean isNegativeClick() {
            return actionType == ActionType.NEGATIVE_CLICK;
        }

        public boolean isKeyBackClick() {
            return actionType == ActionType.KEY_BACK_CLICK;
        }

    }

    public static class Builder {

        private String message;
        private String title;
        private String positive;
        private String negative;
        private View view;
        private Activity activity;

        private Builder(Activity activity) {
            this.activity = activity;
        }

        public static Builder create(Activity activit) {
            return new Builder(activit);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setPositive(String positive) {
            this.positive = positive;
            return this;
        }

        public Builder setPositiveRes(@StringRes int positiveRes) {
            positive = MartianApp.instance().getString(positiveRes);
            return this;
        }

        public Builder setNegative(String negative) {
            this.negative = negative;
            return this;
        }

        public Builder setNegativeRes(@StringRes int negativeRes) {
            this.negative = MartianApp.instance().getString(negativeRes);
            return this;
        }
    }

}
