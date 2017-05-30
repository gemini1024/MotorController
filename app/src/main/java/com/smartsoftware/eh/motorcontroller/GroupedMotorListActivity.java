package com.smartsoftware.eh.motorcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartsoftware.eh.motorcontroller.vo.MotorActions;
import com.smartsoftware.eh.motorcontroller.vo.MotorInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Motors.
 * the activity presents a list of items, which when touched,
 * lead to a {@link GroupedMotorDetailActivity} representing
 * item details.
 */
public class GroupedMotorListActivity extends AppCompatActivity {
    private int mGid;
    public static ArrayList<MotorInfo> motors;

    @BindView(R.id.groupedmotor_list)
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupedmotor_list);
        ButterKnife.bind(this);

        mGid = getIntent().getIntExtra("Group", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mGid<0? "그룹미지정" : "Group "+mGid);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        motors = new ArrayList<>();
        // Add some sample items.
        for (int i = 1; i <= 30; i++)
            motors.add(new MotorInfo( MotorInfo.makeId(mGid, i), mGid, "Motor " + i, MotorActions.STOP));

        recyclerView.setAdapter(new ItemRecyclerViewAdapter(motors));
    }

    class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {
        private final List<MotorInfo> motors;

        ItemRecyclerViewAdapter(List<MotorInfo> items) {
            motors = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.groupedmotor_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final MotorInfo motorInfo = motors.get(position);
            holder.idView.setText(String.format("%06d", motorInfo.getId()));
            holder.contentView.setText(motorInfo.getContent());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), GroupedMotorDetailActivity.class);
                    intent.putExtra(GroupedMotorDetailFragment.ARG_ITEM_POSITION, position);
                    startActivity(intent);
                    GroupedMotorListActivity.this.overridePendingTransition(R.anim.animation_enter_detail,
                            R.anim.animation_enter_list);
                }
            });
        }

        @Override
        public int getItemCount() {
            return motors.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            @BindView(R.id.id) TextView idView;
            @BindView(R.id.content) TextView contentView;

            ViewHolder(View view) {
                super(view);
                mView = view;
                ButterKnife.bind(this, view);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + contentView.getText() + "'";
            }
        }
    }
}
