package com.example.coccocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.coccocapp.asysTasks.ReadRss;
import com.example.coccocapp.fragments.NewestListFragment;
import com.example.coccocapp.models.New;
import com.example.coccocapp.util.AsyncResponse;

public class MainActivity extends AppCompatActivity implements NewestListFragment.OnListFragmentInteractionListener{

    private WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare

        Fragment fragment = NewestListFragment.newInstance(1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fr_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(New item) {

    }

}
