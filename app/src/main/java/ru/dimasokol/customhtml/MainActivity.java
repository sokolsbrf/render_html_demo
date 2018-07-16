package ru.dimasokol.customhtml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHtmlText;
    private View mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHtmlText = findViewById(R.id.edit_html);
        mButton = findViewById(R.id.button_view);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html = mHtmlText.getText().toString();
                Intent view = ViewHtmlActivity.newIntent(v.getContext(), html);
                startActivity(view);
            }
        });
    }
}
