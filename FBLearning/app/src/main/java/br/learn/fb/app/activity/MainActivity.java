package br.learn.fb.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import br.learn.fb.app.R;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess");
                AccessToken accessToken = loginResult.getAccessToken();
                Log.d(TAG, "accessToken.getUserId() : " + accessToken.getUserId());
                Log.d(TAG, "accessToken.getApplicationId() : " + accessToken.getApplicationId());
                Log.d(TAG, "accessToken.getPermissions() : " + accessToken.getPermissions());
                Log.d(TAG, "accessToken.getDeclinedPermissions() : " + accessToken.getDeclinedPermissions());
                Log.d(TAG, "accessToken.getExpires() : " + accessToken.getExpires().toString());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError");
                Log.d(TAG, error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
