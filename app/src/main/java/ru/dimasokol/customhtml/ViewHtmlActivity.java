package ru.dimasokol.customhtml;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ViewHtmlActivity extends AppCompatActivity {

    private static final String ARG_HTML = "html";

    public static Intent newIntent(Context context, String html) {
        Intent intent = new Intent(context, ViewHtmlActivity.class);
        intent.putExtra(ARG_HTML, html);
        return intent;
    }

    private TextView mViewHtml;
    private CheckBox mUseSax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_html);

        mViewHtml = findViewById(R.id.text_view_html);
        mUseSax = findViewById(R.id.check_sax);

        mUseSax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    renderSax();
                } else {
                    renderTree();
                }
            }
        });

        if (mUseSax.isChecked()) {
            renderSax();
        } else {
            renderTree();
        }
    }

    private void renderTree() {
        CustomHtml renderer = new CustomHtml(this);
        mViewHtml.setText(renderer.fromHtml(getIntent().getStringExtra(ARG_HTML)));
    }

    private void renderSax() {
        SaxCustomHtml renderer = new SaxCustomHtml(this);
        mViewHtml.setText(renderer.fromHtml(getIntent().getStringExtra(ARG_HTML)));
    }
}
