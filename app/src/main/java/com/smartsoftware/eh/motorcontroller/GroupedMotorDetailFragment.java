package com.smartsoftware.eh.motorcontroller;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartsoftware.eh.motorcontroller.vo.MotorInfo;

/**
 * A fragment representing a single GroupedMotor detail screen.
 * This fragment is either contained in a {@link GroupedMotorListActivity}
 * in two-pane mode (on tablets) or a {@link GroupedMotorDetailActivity}
 * on handsets.
 */
public class GroupedMotorDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_POSITION = "item_position";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    private MotorInfo motorInfo;

    public GroupedMotorDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_POSITION)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int position = getArguments().getInt(ARG_ITEM_POSITION, 0);
            motorInfo = GroupedMotorListActivity.motors.get(position);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(motorInfo.getContent());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.groupedmotor_detail, container, false);
        // Show the dummy content as text in a TextView.
        if (motorInfo != null) {
            ((TextView) rootView.findViewById(R.id.groupedmotor_detail)).setText(motorInfo.toString());
        }

        return rootView;
    }
}
