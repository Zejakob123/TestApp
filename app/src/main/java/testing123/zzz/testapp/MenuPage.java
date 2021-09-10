package testing123.zzz.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuPage extends AppCompatActivity {

    private static final String TAG = "Huawei Kit App";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MyHuaweiKit myhuaweikit =(MyHuaweiKit)this.getApplication();

    List<kit> kitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kitList = myhuaweikit.getKitList();

        recyclerView = findViewById(R.id.rv_kitlist);
        //use this setting to improve performance if you know that changes
        //in content do not change the layout size of he RecyclerView
        recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter(kitList, MenuPage.this);
        recyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onCreate: " + kitList.toString());


    }


}