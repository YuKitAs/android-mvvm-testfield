package yukitas.mvvm.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.FragmentCreatePostBinding;
import yukitas.mvvm.viewmodels.CreatePostViewModel;

public class CreatePostFragment extends Fragment {
    private FragmentCreatePostBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CreatePostViewModel viewModel =
                ViewModelProviders.of(this).get(CreatePostViewModel.class);
        binding.setCreatePostViewModel(viewModel);
    }
}
