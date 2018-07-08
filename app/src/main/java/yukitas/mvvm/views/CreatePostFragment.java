package yukitas.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.FragmentCreatePostBinding;

public class CreatePostFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCreatePostBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false);

        return binding.getRoot();
    }
}
