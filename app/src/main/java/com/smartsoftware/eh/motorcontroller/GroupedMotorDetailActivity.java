package com.smartsoftware.eh.motorcontroller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.smartsoftware.eh.motorcontroller.vo.MotorActions;
import com.smartsoftware.eh.motorcontroller.vo.MotorInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item details are presented side-by-side with a list of items
 * in a {@link GroupedMotorListActivity}.
 */
public class GroupedMotorDetailActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupedmotor_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        mPosition = getIntent().getIntExtra(GroupedMotorDetailFragment.ARG_ITEM_POSITION, 0);


        final MotorInfo motorInfo = GroupedMotorListActivity.motors.get(mPosition);
        if(motorInfo.getCurrentAction() == MotorActions.STOP)
            fab.setImageResource(android.R.drawable.ic_media_play);
        else
            fab.setImageResource(android.R.drawable.ic_media_pause);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(motorInfo.getCurrentAction() == MotorActions.STOP) {
                    GroupedMotorListActivity.motors.get(mPosition).setCurrentAction(MotorActions.PLAY);
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    Snackbar.make(view, "Start This Motor", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    GroupedMotorListActivity.motors.get(mPosition).setCurrentAction(MotorActions.STOP);
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    Snackbar.make(view, "Stop This Motor", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                Bundle arguments = new Bundle();
                arguments.putInt(GroupedMotorDetailFragment.ARG_ITEM_POSITION, mPosition);
                GroupedMotorDetailFragment fragment = new GroupedMotorDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.groupedmotor_detail_container, fragment)
                        .commit();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(GroupedMotorDetailFragment.ARG_ITEM_POSITION, mPosition);
            GroupedMotorDetailFragment fragment = new GroupedMotorDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.groupedmotor_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
//            NavUtils.navigateUpTo(this, new Intent(this, GroupedMotorListActivity.class));
            finish();
            overridePendingTransition(R.anim.animation_leave_list, R.anim.animation_leave_detail);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation_leave_list, R.anim.animation_leave_detail);
    }
}
