package yukitas.mvvm.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import yukitas.mvvm.R;
import yukitas.mvvm.models.Post;

public class MainActivity extends AppCompatActivity {
    private static final String POST_LIST_FRAGMENT_TAG = "post_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void attachPostFragment(Post post) {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            PostFragment.build(post.getId()))
                    .addToBackStack(null).commit();
        }
    }

    public void attachCreatePostFragment() {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new CreatePostFragment())
                    .addToBackStack(null).commit();
        }
    }

    public void attachEditPostFragment() {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new EditPostFragment())
                    .addToBackStack(null).commit();
        }
    }
}
