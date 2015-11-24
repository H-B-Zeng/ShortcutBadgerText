package com.terryyamg.shortcutbadgertext;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import me.leolin.shortcutbadger.ShortcutBadger;


public class MainActivity extends Activity {

    private int badgeCount;
    private EditText numInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numInput = (EditText) findViewById(R.id.numInput);

        Button button = (Button) findViewById(R.id.btnSetBadge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(numInput.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "錯誤:", Toast.LENGTH_SHORT).show();
                }

                ShortcutBadger.with(MainActivity.this).count(badgeCount); //次數

                Toast.makeText(MainActivity.this, "通知次數=" + badgeCount, Toast.LENGTH_SHORT).show();
            }
        });


        //find the home launcher Package
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;

        TextView textViewHomePackage = (TextView) findViewById(R.id.textViewHomePackage);
        textViewHomePackage.setText("手機型號:" + currentHomePackage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ShortcutBadger.with(this).remove(); //歸0
        Toast.makeText(this,"已讀",Toast.LENGTH_SHORT).show();
    }
}

