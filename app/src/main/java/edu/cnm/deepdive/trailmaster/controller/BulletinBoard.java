package edu.cnm.deepdive.trailmaster.controller;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter.OnClickListener;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;

public class BulletinBoard extends AppCompatActivity implements OnClickListener {

        private MainViewModel viewModel;
        private RecyclerView trails;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bulletin_board);
            trails = findViewById(R.id.trails);
            findViewById(R.id.add_trail).setOnClickListener((v) -> editTrail(0));
            viewModel = new ViewModelProvider(this).get(MainViewModel.class);
            viewModel.getTrails().observe(this, (trails) -> {
//      Log.d(getClass().getName(), trails.toString() );
                TrailAdapter adapter = new TrailAdapter(this, trails, this);
                this.trails.setAdapter(adapter);
            });

        }

        @Override
        public void onClick(View v, int position, Trail trail) {
          editTrail(trail.getId());
        }

        private void editTrail(long id) {
          EditTrailFragment fragment = EditTrailFragment.newInstance(id);
          fragment.show(getSupportFragmentManager(), fragment.getClass().getName());
        }
}