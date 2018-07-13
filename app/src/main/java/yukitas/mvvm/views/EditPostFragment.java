package yukitas.mvvm.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import yukitas.mvvm.R;
import yukitas.mvvm.databinding.FragmentEditPostBinding;
import yukitas.mvvm.viewmodels.PostViewModel;
import yukitas.mvvm.views.utils.RequiredFieldValidator;

public class EditPostFragment extends Fragment {
    private FragmentEditPostBinding binding;
    private static final String IS_CREATING = "is_creating";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_post, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isAdded()) {
            final PostViewModel viewModel =
                    ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(PostViewModel.class);

            Boolean isCreating = Objects.requireNonNull(getArguments()).getBoolean(IS_CREATING);
            if (isCreating) {
                binding.btnSave.setText(R.string.btn_publish);
                viewModel.clearSelectedPost();
            } else {
                binding.btnSave.setText(R.string.btn_update);
            }

            binding.setPostViewModel(viewModel);
            binding.setLifecycleOwner(this);
            binding.btnSave.setOnClickListener((btn) -> {
                if (RequiredFieldValidator.validate(viewModel.getTitle().getValue())) {
                    if (isCreating) {
                        viewModel.sendPost();
                    } else {
                        viewModel.updatePost();
                    }

                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {
                    Toast.makeText(getContext(), R.string.toast_text_require_title, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static EditPostFragment build(Boolean isCreating) {
        EditPostFragment fragment = new EditPostFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_CREATING, isCreating);
        fragment.setArguments(args);

        return fragment;
    }
}
