package summious.ple.wow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String APP_NAME = "LexiCount";
    private TextView appNameText;
    private ImageView logoImage;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appNameText = findViewById(R.id.appNameText);
        logoImage = findViewById(R.id.logoImage);

        // Logo fade-in & scale-up animation
        AlphaAnimation fadeInLogo = new AlphaAnimation(0f, 1f);
        fadeInLogo.setDuration(700);
        fadeInLogo.setFillAfter(true);
        logoImage.startAnimation(fadeInLogo);

        // App name fade-in
        AlphaAnimation fadeInText = new AlphaAnimation(0f, 1f);
        fadeInText.setDuration(1000);
        fadeInText.setFillAfter(true);

        // Delay the text fade-in to happen after logo animation
        handler.postDelayed(() -> {
            appNameText.setText(APP_NAME);
            appNameText.startAnimation(fadeInText);
        }, 700);

        // Navigate to MainActivity after splash
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000); // Total splash duration
    }
}
