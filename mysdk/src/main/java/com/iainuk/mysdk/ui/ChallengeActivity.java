package com.iainuk.mysdk.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.iainuk.mysdk.databinding.ActivityChallengeBinding;

public class ChallengeActivity extends AppCompatActivity {

    private ActivityChallengeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChallengeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
