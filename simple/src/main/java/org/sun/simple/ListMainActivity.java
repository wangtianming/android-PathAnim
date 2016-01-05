package org.sun.simple;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        ((ListView) findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        toNext(MainActivity.class);
                        break;
                    case 1:
                        toNext(JoinShopCartActivity.class);
                        break;
                    case 2:
                        toNext(CirclePathActivity.class);
                        break;
                }
            }
        });
    }

    private void toNext(Class<? extends Activity> clazz) {
        startActivity(new Intent(ListMainActivity.this, clazz));
    }
}
