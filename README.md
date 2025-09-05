package com.yourpackage.worldy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView textResult, textResultTitle;
    private AppCompatButton btnCount, btnShare, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure XML file is activity_main.xml

        inputText = findViewById(R.id.inputText);
        textResult = findViewById(R.id.textResult);
        textResultTitle = findViewById(R.id.textResultTitle);
        btnCount = findViewById(R.id.btnCount);
        btnShare = findViewById(R.id.btnShare);
        btnRestart = findViewById(R.id.btnRestart);

        btnCount.setOnClickListener(v -> countWordsAndChars());
        btnShare.setOnClickListener(v -> shareResult());
        btnRestart.setOnClickListener(v -> resetAll());
    }

    private void countWordsAndChars() {
        String text = inputText.getText().toString().trim();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Please enter some text!", Toast.LENGTH_SHORT).show();
            return;
        }

        int charCount = text.length();
        int wordCount = text.isEmpty() ? 0 : text.split("\\s+").length;

        String result = wordCount + " Words, " + charCount + " Characters";
        textResult.setText(result);

        // Make result and buttons visible
        textResult.setVisibility(View.VISIBLE);
        textResultTitle.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.VISIBLE);
        btnRestart.setVisibility(View.VISIBLE);
    }

    private void shareResult() {
        String result = textResult.getText().toString();
        if (TextUtils.isEmpty(result)) {
            Toast.makeText(this, "Nothing to share!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, result);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void resetAll() {
        inputText.setText("");
        textResult.setText("0 Words, 0 Characters");
        textResult.setVisibility(View.VISIBLE); // Show placeholder initially
        textResultTitle.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.GONE);
        btnRestart.setVisibility(View.GONE);
    }
}
