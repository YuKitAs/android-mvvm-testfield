package yukitas.mvvm.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import yukitas.mvvm.views.utils.LocaleHelper;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
