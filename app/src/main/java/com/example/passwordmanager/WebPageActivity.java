package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WebPageActivity extends AppCompatActivity {
    private static final int ADD_PLATFORM_REQUEST = 1;
    private static final int EDIT_DETAILS_REQUEST = 2;
    private WebModal webModal;
    private WebPageRVAdapter adapter;
    private ArrayList<LoginDetailsModal> detailsListFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        SearchView searchView = findViewById(R.id.swPlatform);

        RecyclerView detailsRV = findViewById(R.id.idRVPlatform);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebPageActivity.this, AddPlatformActivity.class);
                startActivityForResult(intent, ADD_PLATFORM_REQUEST);
            }
        });

        detailsRV.setLayoutManager(new LinearLayoutManager(this));
        detailsRV.setHasFixedSize(true);

        adapter = new WebPageRVAdapter();

        detailsRV.setAdapter(adapter);

        webModal = ViewModelProviders.of(this).get(WebModal.class);

        webModal.getAllDetails().observe(this, new Observer<List<LoginDetailsModal>>() {
            @Override
            public void onChanged(List<LoginDetailsModal> models) {
                detailsListFull = (ArrayList<LoginDetailsModal>) models; // Update your detailsListFull
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                webModal.delete(adapter.getDetailAt(viewHolder.getAdapterPosition()));
                Toast.makeText(WebPageActivity.this, "Login details deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(detailsRV);

        adapter.setOnItemClickListener(new WebPageRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LoginDetailsModal model) {
                Intent intent = new Intent(WebPageActivity.this, AddPlatformActivity.class);
                intent.putExtra(AddPlatformActivity.EXTRA_ID, model.getId());
                intent.putExtra(AddPlatformActivity.EXTRA_PLATFORM_NAME, model.getPlatformName());
                intent.putExtra(AddPlatformActivity.EXTRA_USERNAME, model.getUsername());
                intent.putExtra(AddPlatformActivity.EXTRA_PASSWORD, model.getPassword());

                startActivityForResult(intent, EDIT_DETAILS_REQUEST);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PLATFORM_REQUEST && resultCode == RESULT_OK) {
            String platformName = data.getStringExtra(AddPlatformActivity.EXTRA_PLATFORM_NAME);
            String username = data.getStringExtra(AddPlatformActivity.EXTRA_USERNAME);
            String password = data.getStringExtra(AddPlatformActivity.EXTRA_PASSWORD);


            LoginDetailsModal model = new LoginDetailsModal(platformName, username, password);
            webModal.insert(model);
            Toast.makeText(this, "Login details saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_DETAILS_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddPlatformActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Login can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String platformName = data.getStringExtra(AddPlatformActivity.EXTRA_PLATFORM_NAME);
            String username = data.getStringExtra(AddPlatformActivity.EXTRA_USERNAME);
            String password = data.getStringExtra(AddPlatformActivity.EXTRA_PASSWORD);


            LoginDetailsModal model = new LoginDetailsModal(platformName, username, password);
            model.setId(id);
            webModal.update(model);
            Toast.makeText(this, "Login details updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login details not saved", Toast.LENGTH_SHORT).show();
        }
    }

}
