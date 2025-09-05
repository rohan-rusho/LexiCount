package summious.ple.wow;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView textResult, textResultTitle;
    AppCompatButton btnCount, btnShare, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        inputText = findViewById(R.id.inputText);
        textResult = findViewById(R.id.textResult);
        textResultTitle = findViewById(R.id.textResultTitle);
        btnCount = findViewById(R.id.btnCount);
        btnShare = findViewById(R.id.btnShare);
        btnRestart = findViewById(R.id.btnRestart);

        // Count Button
        btnCount.setOnClickListener(v -> {
            String text = inputText.getText().toString().trim();

            if (text.isEmpty()) {
                Toast.makeText(this, "Please enter some text!", Toast.LENGTH_SHORT).show();
                return;
            }

            int wordCount = countWords(text);
            int charCount = text.length();

            textResult.setText(wordCount + " Words, " + charCount + " Characters");

            // Show Share and Reset buttons
            btnShare.setVisibility(View.VISIBLE);
            btnRestart.setVisibility(View.VISIBLE);
        });

        // Share Button
        btnShare.setOnClickListener(v -> {
            String resultText = textResult.getText().toString();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, resultText);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // Reset Button
        btnRestart.setOnClickListener(v -> {
            inputText.setText("");
            textResult.setText("Result will appear here");
            btnShare.setVisibility(View.GONE);
            btnRestart.setVisibility(View.GONE);
        });
    }

    // Helper method to count words
    private int countWords(String text) {
        if (text.isEmpty()) return 0;

        // Split on whitespace, tabs, newlines
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
