package edu.cnm.deepdive.trailmaster.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;
import java.text.NumberFormat;
import java.text.ParseException;

public class EditTrailFragment extends DialogFragment
    implements OnShowListener, TextWatcher {

  private static final String TRAIL_ID_KEY = "id";

  private MainViewModel viewModel;
  private long trailId;
  private Trail trail;
  private View root;
  private AlertDialog dialog;
  private EditText name;
  private EditText comment;
  private EditText latitude;
  private EditText longitude;
  private AppCompatSeekBar rating;
  private NumberFormat numberFormat;

  public static EditTrailFragment newInstance(long trailId) {
     EditTrailFragment fragment = new EditTrailFragment();
     Bundle args = new Bundle();
     args.putLong(TRAIL_ID_KEY, trailId);
     fragment.setArguments(args);
     return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    trailId = (getArguments() != null) ? getArguments().getLong(TRAIL_ID_KEY, 0) : 0;
    numberFormat = NumberFormat.getNumberInstance();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_trail, null, false);
    name = root.findViewById(R.id.name);
    comment = root.findViewById(R.id.comment);
    latitude = root.findViewById(R.id.latitude);
    longitude = root.findViewById(R.id.longitude);
    rating = root.findViewById(R.id.rating);
    name.addTextChangedListener(this);
    comment.addTextChangedListener(this);
    latitude.addTextChangedListener(this);
    longitude.addTextChangedListener(this);
    dialog = new AlertDialog.Builder(getContext())
        .setTitle("Trail Information")
//        .setIcon()
        .setView(root)
        .setNegativeButton(android.R.string.cancel, null)
        .setPositiveButton(android.R.string.ok, (d, w) -> {
          save();
        })
        .create();
    dialog.setOnShowListener(this);
    return dialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    if (trailId != 0) {
      viewModel.getTrail().observe(getViewLifecycleOwner(), (trail) -> {
        this.trail = trail;
        name.setText(trail.getName());
        comment.setText(trail.getComment());
        longitude.setText(numberFormat.format(trail.getLongitude()));
        latitude.setText(numberFormat.format(trail.getLatitude()));
        rating.setProgress(trail.getRating());
      });
      viewModel.setTrailId(trailId);
    } else {
      trail = new Trail();
      name.setText("");
      comment.setText("");
      longitude.setText("");
      latitude.setText("");
      rating.setProgress(0);
    }
  }

  @Override
  public void onShow(DialogInterface dialog) {
    checkSubmitConditions();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    // Dont do anything.
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  //Dont do anything.
  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  public void checkSubmitConditions() {
    Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    positive.setEnabled(
        !name.getText().toString().trim().isEmpty()
        && !comment.getText().toString().trim().isEmpty()
        && !latitude.getText().toString().trim().isEmpty()
        && !longitude.getText().toString().trim().isEmpty()
    );
  }

  private void save() {
    try {
      trail.setName(name.getText().toString().trim());
      trail.setComment(comment.getText().toString().trim());
      trail.setLatitude(numberFormat.parse(latitude.getText().toString().trim()).doubleValue());
      trail.setLongitude(numberFormat.parse(longitude.getText().toString().trim()).doubleValue());
      trail.setRating(rating.getProgress());
      viewModel.save(trail);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
