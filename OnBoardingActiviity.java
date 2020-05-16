package com.prince.women_security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

import static android.content.Context.MODE_PRIVATE;

public class OnBoardingActiviity extends TutorialActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new Step.Builder().setTitle("Developer-Prince Kasaudhan")
                .setContent("Safety of Women in India has become a major issue in India now. The crime rates against women in the country have only risen to a great extent. This is, unfortunately, the sad reality of our country that lives in constant fear.\nThis Application can help Women in large extend regarding there safety \nThankYou")
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.sosa)
                .build());
        addFragment(new Step.Builder().setTitle("Step 1")
                .setContent("Allow all the Permissions.")
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.image2a)
                .build());
        addFragment(new Step.Builder().setTitle("Step 2")
                .setContent("Click on the Setting given in Menu Option")
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.image3a)
                .build());
        addFragment(new Step.Builder().setTitle("Step 3")
                .setContent("Save All the Informations.\n\nThen Press Share to Family Button\nShare to Whatsapp Button Feature will be coming soon" )
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.image4a)
                .build());
        addFragment(new Step.Builder().setTitle("Result")
                .setContent("A Message with your current Location will be send to the number you saved ")
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.image5a)
                .build());
        addFragment(new Step.Builder().setTitle("Result")
                .setContent("On clicking the message your gaurdian will get exact location of your place and a route will be drawan ")
                .setBackgroundColor(Color.parseColor("#ED0C44"))
                .setDrawable(R.drawable.image6a)
                .build());
    }
    @Override
    public void finishTutorial()
    {
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(OnBoardingActiviity.this, locationeanble.class));

            Toast.makeText(OnBoardingActiviity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        }
        else
        {
            startActivity(new Intent(OnBoardingActiviity.this, WomenSecurity.class));

        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
    }
    @Override
    public void currentFragmentPosition(int position) {

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();      // it inflate the menu item in the code and its xml file will be too inflated
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.help) {
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + " Subject of Your Query...  " + "&body=" + " Please Write your Query... " + "&to=" + "pkasaudhan93@gmail.com");
            mailIntent.setData(data);
            startActivity(mailIntent);
            return true;
        } else if (item.getItemId() == R.id.About) {

            Intent intent = new Intent(getApplicationContext(), AboutFeedback.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.Share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        else if (item.getItemId() == R.id.setting) {

            Intent intent = new Intent(getApplicationContext(), AboutFeedback.class);
            startActivity(intent);

        }


        return false;
    }
}
