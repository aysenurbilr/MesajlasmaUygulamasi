package com.example.mychatapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychatapp.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class GalleryFragment extends Fragment {
        public static final String TAG = "--GALLERY--";
        ArrayList<MsgGonderModel> msgGonder;
        MsgGonderAdapter msgGonderAdapter;
        RecyclerView msgGonderRv;
        ArrayList<RehberModel> rehberModel;
        RehberAdapter rehberAdapter;
        RecyclerView rehberRv;


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_gallery, container, false);
            msgGonderRv = root.findViewById(R.id.rv_msg_gonder_secili_grup);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(RecyclerView.HORIZONTAL);
            llm.setReverseLayout(false);
            msgGonderRv.setLayoutManager(llm);

            msgGonder = new ArrayList<>();
            msgGonderAdapter = new MsgGonderAdapter(getActivity(), msgGonder);
            msgGonderRv.setAdapter(msgGonderAdapter);

            rehberRv = root.findViewById(R.id.rv_rehber);
            rehberRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

            rehberModel = new ArrayList<>();
            rehberAdapter = new RehberAdapter(getActivity(), rehberModel);
            rehberRv.setAdapter(rehberAdapter);

            getCloudData();
            return root;
        }

        private void getCloudData() {
            db.collection("msggonder")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isComplete()) {
                                for(QueryDocumentSnapshot doc: task.getResult()) {
                                    Log.d(TAG, doc.getData().toString());
                                    MsgGonderModel mgm = doc.toObject(MsgGonderModel.class);
                                    msgGonder.add(mgm);
                                    msgGonderAdapter.notifyDataSetChanged();
                                }
                            }
                            else{
                                Log.d(TAG, "Veri YOK!");
                            }
                        }
                    });
            db.collection("rehber")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isComplete()) {
                                for(QueryDocumentSnapshot doc: task.getResult()) {
                                    Log.d(TAG, doc.getData().toString());
                                    RehberModel rm = doc.toObject(RehberModel.class);
                                    rehberModel.add(rm);
                                    rehberAdapter.notifyDataSetChanged();
                                }
                            }
                            else{
                                Log.d(TAG, "Veri YOK!");
                            }
                        }
                    });
        }

    }
}