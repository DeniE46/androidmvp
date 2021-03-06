/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.antonioleiva.mvpexample.app.Login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.databinding.ActivityLoginBinding;
import com.antonioleiva.mvpexample.app.main.MainActivity;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    ActivityLoginBinding activityLoginBinding;


    private LoginPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initializeViews();
        presenter = new LoginPresenterImpl(this);
    }

    void initializeViews(){
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        activityLoginBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        activityLoginBinding.progress.setVisibility(View.GONE);
    }

    @Override public void setUsernameError() {
        activityLoginBinding.username.setError(getString(R.string.username_error));
    }

    @Override public void setPasswordError() {
        activityLoginBinding.password.setError(getString(R.string.password_error));
    }

    @Override public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override public void onClick(View v) {
        presenter.validateCredentials(activityLoginBinding.username.getText().toString(), activityLoginBinding.password.getText().toString());
    }
}
