/**
 * Tom OLIVIER - 2022 : https://github.com/Tom60chat/
 * Under the Unlicense license.
 */

package fr.katycorp.utils.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SimpleDialog extends DialogFragment {

    //region Constructors

    public SimpleDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.primaryButtonText = builder.primaryButtonText;
        this.secondaryButtonText = builder.secondaryButtonText;
        this.closeButtonText = builder.closeButtonText;
        this.primaryButtonClick = builder.primaryButtonClick;
        this.secondaryButtonClick = builder.secondaryButtonClick;
        this.closeButtonClick = builder.closeButtonClick;
    }

    //endregion

    //region Variables
    public String title;
    public String message;
    public String primaryButtonText;
    public String secondaryButtonText;
    public String closeButtonText;
    public DialogInterface.OnClickListener primaryButtonClick;
    public DialogInterface.OnClickListener secondaryButtonClick;
    public DialogInterface.OnClickListener closeButtonClick;
    //endregion

    //region Methods
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity == null)
            throw new IllegalStateException("Activity cannot be null");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null && !title.isEmpty())
            builder.setTitle(title);

        if (message != null && !message.isEmpty())
            builder.setMessage(message);

        if (primaryButtonText != null && !primaryButtonText.isEmpty())
            builder.setPositiveButton(
                    primaryButtonText,
                    primaryButtonClick
            );

        if (secondaryButtonText != null && !secondaryButtonText.isEmpty())
            builder.setNeutralButton(
                    secondaryButtonText,
                    secondaryButtonClick
            );

        if (closeButtonText != null && !closeButtonText.isEmpty())
            builder.setNegativeButton(
                    closeButtonText,
                    closeButtonClick
            );

        // Create the AlertDialog object and return it
        return builder.create();
    }
    //endregion

    public static class Builder {
        private String title;
        private String message;
        private String primaryButtonText;
        private String secondaryButtonText;
        private String closeButtonText;
        private DialogInterface.OnClickListener primaryButtonClick;
        private DialogInterface.OnClickListener secondaryButtonClick;
        private DialogInterface.OnClickListener closeButtonClick;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPrimaryButtonText(String primaryButtonText) {
            this.primaryButtonText = primaryButtonText;
            return this;
        }

        public Builder setSecondaryButtonText(String secondaryButtonText) {
            this.secondaryButtonText = secondaryButtonText;
            return this;
        }

        public Builder setCloseButtonText(String closeButtonText) {
            this.closeButtonText = closeButtonText;
            return this;
        }

        public Builder setPrimaryButtonClick(DialogInterface.OnClickListener primaryButtonClick) {
            this.primaryButtonClick = primaryButtonClick;
            return this;
        }

        public Builder setSecondaryButtonClick(DialogInterface.OnClickListener secondaryButtonClick) {
            this.secondaryButtonClick = secondaryButtonClick;
            return this;
        }

        public Builder setCloseButtonClick(DialogInterface.OnClickListener closeButtonClick) {
            this.closeButtonClick = closeButtonClick;
            return this;
        }

        public SimpleDialog build() {
            return new SimpleDialog(this);
        }
    }
}
