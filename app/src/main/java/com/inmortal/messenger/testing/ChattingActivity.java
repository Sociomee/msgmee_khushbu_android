package com.inmortal.messenger.testing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.inmortal.messenger.R;
import com.inmortal.messenger.SocialChat.Chat;
import com.varunjohn1990.audio_record_view.AttachmentOption;
import com.varunjohn1990.audio_record_view.AttachmentOptionsListener;
import com.varunjohn1990.audio_record_view.AudioRecordView;


public class ChattingActivity extends AppCompatActivity implements AudioRecordView.RecordingListener, View.OnClickListener {

    private AudioRecordView audioRecordView;
    private RecyclerView recyclerViewMessages;
    private MessageAdapter messageAdapter;

    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

//        getSupportActionBar().hide();

        audioRecordView = new AudioRecordView();
        // this is to make your layout the root of audio record view, root layout supposed to be empty..
        audioRecordView.initView((FrameLayout) findViewById(R.id.layoutMain));
        // this is to provide the container layout to the audio record view..
        View containerView = audioRecordView.setContainerView(R.layout.layout_chatting);
        audioRecordView.setRecordingListener(this);

        recyclerViewMessages = containerView.findViewById(R.id.recyclerViewMessages);

        messageAdapter = new MessageAdapter();

        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewMessages.setHasFixedSize(false);

        recyclerViewMessages.setAdapter(messageAdapter);
        recyclerViewMessages.setItemAnimator(new DefaultItemAnimator());

        setListener();
        audioRecordView.getMessageView().requestFocus();

        containerView.findViewById(R.id.imageViewTitleIcon).setOnClickListener(this);
        containerView.findViewById(R.id.imageViewMenu).setOnClickListener(this);
        containerView.findViewById(R.id.back_chat).setOnClickListener(this);

//        audioRecordView.setAttachmentOptions(AttachmentOption.getDefaultList(), this);

        audioRecordView.removeAttachmentOptionAnimation(false);
    }

    private void setListener() {

        audioRecordView.getEmojiView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecordView.hideAttachmentOptionView();
                showToast("Emoji Icon Clicked");
            }
        });

        audioRecordView.getCameraView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecordView.hideAttachmentOptionView();
                showToast("Camera Icon Clicked");
            }
        });

        audioRecordView.getSendView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = audioRecordView.getMessageView().getText().toString().trim();
                audioRecordView.getMessageView().setText("");
                messageAdapter.add(new Message(msg));
            }
        });
    }

    @Override
    public void onRecordingStarted() {
        showToast("started");
        debug("started");

        time = System.currentTimeMillis() / (1000);
    }

    @Override
    public void onRecordingLocked() {
        showToast("locked");
        debug("locked");
    }

    @Override
    public void onRecordingCompleted() {
        showToast("completed");
        debug("completed");

        int recordTime = (int) ((System.currentTimeMillis() / (1000)) - time);

        if (recordTime > 1) {
            messageAdapter.add(new Message(recordTime));
        }
    }

    @Override
    public void onRecordingCanceled() {
        showToast("canceled");
        debug("canceled");
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void debug(String log) {
        Log.d("VarunJohn", log);
    }

    @Override
    public void onClick(View view) {
//        showDialog();
        Intent intent = new Intent(ChattingActivity.this  , Chat.class);
        startActivity(intent);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Created by:\nVarun John\nvarunjohn1990@gmail.com\n\nCheck code on Github :\nhttps://github.com/varunjohn/Audio-Recording-Animation")
                .setCancelable(false)
                .setPositiveButton("Github", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String url = "https://github.com/varunjohn/Audio-Recording-Animation";
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setPackage("com.android.chrome");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {
                            i.setPackage(null);
                            try {
                                startActivity(i);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

//    @Override
//    public void onClick(AttachmentOption attachmentOption) {
//        switch (attachmentOption.getId()) {
//
//            case AttachmentOption.DOCUMENT_ID:
//                showToast("Document Clicked");
//                break;
//            case AttachmentOption.CAMERA_ID:
//                showToast("Camera Clicked");
//                break;
//            case AttachmentOption.GALLERY_ID:
//                showToast("Gallery Clicked");
//                break;
//            case AttachmentOption.AUDIO_ID:
//                showToast("Audio Clicked");
//                break;
//            case AttachmentOption.LOCATION_ID:
//                showToast("Location Clicked");
//                break;
//            case AttachmentOption.CONTACT_ID:
//                showToast("Contact Clicked");
//                break;
//        }
//    }
}
