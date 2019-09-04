package io.github.apmwar.classifile;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListFragment extends Fragment {

    ListView lst;
    String title[] = {"Image 1", "Image 2", "Image 3", "Image 4"};
    String desc[] = {"This is image 1", "This is image 2", "This is image 3", "This is image 4"};
    Integer id[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    //lst=(ListView)findViewById(R.id.listView);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}
