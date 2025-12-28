package com.example.overlayapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class OverlayService extends Service {
    WindowManager windowManager;
    View view;

    @Override
    public IBinder onBind(Intent intent){ return null; }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        view = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 200;

        Button shoot = view.findViewById(R.id.btnShoot);
        shoot.setOnClickListener(v -> {
            Toast.makeText(this, "FIRE Button Clicked!", Toast.LENGTH_SHORT).show();
        });

        windowManager.addView(view, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) windowManager.removeView(view);
    }
}
