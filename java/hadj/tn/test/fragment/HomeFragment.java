package hadj.tn.test.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import hadj.tn.test.R;

public class HomeFragment extends Fragment {


    ChipNavigationBar chipNavigationBar ;
    Fragment fragment=null;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        VideoView videoView = view.findViewById(R.id.videoweview);
        ImageView imageView = view.findViewById(R.id.img_start_vid);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video;
                Uri uri = Uri.parse(videoPath);
                videoView.setVideoURI(uri);
                imageView.setVisibility(View.INVISIBLE);
            }
        });

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


    }
}