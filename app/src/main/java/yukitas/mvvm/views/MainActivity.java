package yukitas.mvvm.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import yukitas.mvvm.R;
import yukitas.mvvm.models.Post;
import yukitas.mvvm.views.utils.LocaleHelper;

public class MainActivity extends BaseActivity {
    private static final String POST_LIST_FRAGMENT_TAG = "post_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PostListFragment(), POST_LIST_FRAGMENT_TAG).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(POST_LIST_FRAGMENT_TAG).isVisible()) {
            Log.i("onBackPressed", "Exiting app...");
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_lang_de:
                setLanguage("de");
                return true;
            case R.id.item_lang_en:
                setLanguage("en");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void attachPostFragment(Post post) {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            PostFragment.build(post.getId()))
                    .addToBackStack(null).commit();
        }
    }

    public void attachEditPostFragment(Boolean isCreating) {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            EditPostFragment.build(isCreating))
                    .addToBackStack(null).commit();
        }
    }

    private void setLanguage(String language) {
        LocaleHelper.setLocale(this, language);

        recreate();
    }
}
