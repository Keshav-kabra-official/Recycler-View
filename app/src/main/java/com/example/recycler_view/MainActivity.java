package com.example.recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionMode acm;
    Button b;
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        t1 = findViewById(R.id.delete_text_button);
        t2 = findViewById(R.id.share_text_button);

        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Set-On-Click-Listener Invoked", Toast.LENGTH_SHORT).show();
                t1.setVisibility((t1.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
                t2.setVisibility((t2.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
                if(t1.getVisibility()==View.VISIBLE && t2.getVisibility()==View.VISIBLE){
                    t1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Option-1 (Delete) Selected", Toast.LENGTH_SHORT).show();
                            t1.setVisibility(View.GONE);
                            t2.setVisibility(View.GONE);
                        }
                    });
                    t2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Option-2 (Share) Selected", Toast.LENGTH_SHORT).show();
                            t1.setVisibility(View.GONE);
                            t2.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "Set-On-Long-Click-listener Invoked", Toast.LENGTH_SHORT).show();
                if(acm != null){
                    return false;
                }

                acm = startSupportActionMode(acm_callback);
                return true;
            }
        });
    }

    private ActionMode.Callback acm_callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.example_menu, menu);
            mode.setTitle("Choose Option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.option_1:
                    Toast.makeText(MainActivity.this, "Option-1 (Delete) Selected", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;

                case R.id.option_2:
                    Toast.makeText(MainActivity.this, "Option-2 (Share) Selected", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            acm = null;
        }
    };
}
