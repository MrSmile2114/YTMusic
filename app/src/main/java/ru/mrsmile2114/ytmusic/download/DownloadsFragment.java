package ru.mrsmile2114.ytmusic.download;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mrsmile2114.ytmusic.R;
import ru.mrsmile2114.ytmusic.dummy.DownloadsItems;
import ru.mrsmile2114.ytmusic.dummy.DownloadsItems.DownloadsItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DownloadsFragment extends Fragment {

        private OnListFragmentInteractionListener mListener;
        private DownloadsRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DownloadsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DownloadsFragment newInstance(int columnCount) {
        DownloadsFragment fragment = new DownloadsFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_downloads_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new DownloadsRecyclerViewAdapter(DownloadsItems.getITEMS(), mListener);
            recyclerView.setAdapter(mAdapter);
        }
        mListener.SetTitle(getResources().getString(R.string.action_manage));
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void RefreshRecyclerView(){
        mAdapter.notifyDataSetChanged();
    }

    public void RemoveItemByDownloadId(String downloadId){
        DownloadsItem item = DownloadsItems.getITEMbyDownloadId(downloadId);
        if (item != null){
            DownloadsItems.getITEMS().remove(item);
        }
        if(mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void SetMainFabVisible(boolean visible);
        void SetMainFabImage(int imageResource);
        void SetMainFabListener(View.OnClickListener listener);
        void RemoveItemByDownloadId(String downloadId, boolean completed);
        void SetMainProgressDialogVisible(boolean visible);
        void SetTitle(String title);
    }
}
