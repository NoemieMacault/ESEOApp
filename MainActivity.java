package eseo.eseoapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton buttonFacebook;
    private ImageButton buttonTwitter;
    private ImageButton buttonStages;
    private ImageButton buttonCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFacebook = (ImageButton) findViewById(R.id.fbImage);
        buttonTwitter = (ImageButton) findViewById(R.id.twImage);
        buttonStages = (ImageButton) findViewById(R.id.stagesImage);
        buttonCV = (ImageButton) findViewById(R.id.cvImage);


        buttonFacebook.setOnClickListener(this);
        buttonTwitter.setOnClickListener(this);
        buttonStages.setOnClickListener(this);
        buttonCV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonStages){
            startActivity(new Intent(this,ViewAllStages.class));
        }
        if(view == buttonCV){
            startActivity(new Intent(this,MainCV.class));
        }
        if(view == buttonFacebook){
            facebookListener(view);
        }
        if(view == buttonTwitter){
            twitterListener(view);
        }
    }


    public void facebookListener(View view) {
        try {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/201118476572983"));
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/GroupeESEO/?fref=ts"));
            startActivity(webIntent);
        }
    }

    public void twitterListener(View view) {
        try {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=130789836"));
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/[Groupe_ESEO]"));
            startActivity(webIntent);
        }
    }
}

