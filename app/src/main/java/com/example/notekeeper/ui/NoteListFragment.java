package com.example.notekeeper.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notekeeper.databinding.FragmentNoteListBinding;
import com.example.notekeeper.viewmodel.NoteListViewModel;

public class NoteListFragment extends Fragment {

    private FragmentNoteListBinding binding;
    private NoteListViewModel noteListViewModel;
    private NoteListAdapter noteListAdapter;

    public NoteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteListViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(NoteListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setNoteListViewModel(noteListViewModel);

        noteListAdapter = new NoteListAdapter(this::goToNote);
        binding.noteList.setAdapter(noteListAdapter);

        binding.addNoteBtn.setOnClickListener(v -> goToNote(0));
    }

    private void goToNote(int noteId){
        NavHostFragment.findNavController(this).navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteFragment().setNoteId(noteId));
    }

}