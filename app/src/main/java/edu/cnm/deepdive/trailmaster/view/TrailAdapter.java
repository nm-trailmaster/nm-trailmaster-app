package edu.cnm.deepdive.trailmaster.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter.Holder;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;

public class TrailAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Trail> trails;
  private final DateFormat dateFormat;
  private final NumberFormat numberFormat;
  private final OnClickListener listener;

  public TrailAdapter(Context context,
      List<Trail> trails,
      OnClickListener listener) {
    this.context = context;
    this.trails = trails;
    this.listener = listener;
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    numberFormat = NumberFormat.getInstance();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return new Holder(LayoutInflater.from(context).inflate(R.layout.item_trail, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull TrailAdapter.Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return trails.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final TextView name;
    private final TextView comment;
    private final TextView author;
    private final TextView latitude;
    private final TextView longitude;
    private final TextView rating;

    public Holder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      name = itemView.findViewById(R.id.name);
      comment = itemView.findViewById(R.id.comment);
      author = itemView.findViewById(R.id.author);
      latitude = itemView.findViewById(R.id.latitude);
      longitude = itemView.findViewById(R.id.longitude);
      rating = itemView.findViewById(R.id.rating);
    }

    private void bind(int position) {
      Trail trail = trails.get(position);
      name.setText(trail.getName());
      comment.setText(trail.getComment());
//      author.setText(trail.getAuthor().getUsername());
      latitude.setText(numberFormat.format(trail.getLatitude()));
      longitude.setText(numberFormat.format(trail.getLongitude()));
      rating.setText(numberFormat.format(trail.getRating()));
      itemView.setOnClickListener((view) -> listener.onClick(view, position, trail));
    }
  }

  @FunctionalInterface
  public interface OnClickListener {
    void onClick(View v, int position, Trail trail);
  }
}
