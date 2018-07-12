package yukitas.mvvm.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yukitas.mvvm.R;
import yukitas.mvvm.models.Post;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PostListFragment()).commit();
        }
    }

    public void attachPostFragment(Post post) {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,
                            PostFragment.build(post.getId())).commit();
        }
    }

    public void attachCreatePostFragment() {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,
                            new CreatePostFragment()).commit();
        }
    }

    public void attachEditPostFragment() {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,
                            new EditPostFragment()).commit();
        }
    }
}
