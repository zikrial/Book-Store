package com.example.booksearch_173040027;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mBookInput;
    private TextView mAuthorText, mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mAuthorText = (TextView) findViewById(R.id.authorText);
        mTitleText = (TextView) findViewById(R.id.titleText);
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText("Pleas Input Title Book");
        } else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText("Please Input Title Books");
            } else {
                mAuthorText.setText("");
                mTitleText.setText("Please Check Your Connection");
            }
        }
    }

    public void helpBooks(View view) {
        Toast.makeText(getApplicationContext(), "1. Aplikasi ini dapat digunakan " + "untuk mencari buku. " + "\n2. Pencarian hanya mengeluarkan 1 hasil. " + "pastikan mencari judul dengan tepat.", Toast.LENGTH_LONG).show();
    }
}

