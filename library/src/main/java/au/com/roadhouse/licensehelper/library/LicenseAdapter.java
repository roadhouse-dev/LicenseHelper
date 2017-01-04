package au.com.roadhouse.licensehelper.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LicenseAdapter extends RecyclerView.Adapter<LicenseAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<LicenseParser.License> mData;
    private OnLicenseSelectedListener mOnLicenseSelectedListener;

    public LicenseAdapter(Context context, List<LicenseParser.License> data){
        mLayoutInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.internal_license_row_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LicenseParser.License license = mData.get(position);
        holder.mName.setText(license.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnLicenseSelectedListener != null){
                    mOnLicenseSelectedListener.onLicenseSelected(mData.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null? 0 : mData.size();
    }

    public void setOnLicenseSelectedListener(OnLicenseSelectedListener onLicenseSelectedListener) {
        mOnLicenseSelectedListener = onLicenseSelectedListener;
    }

    public void swapData( List<LicenseParser.License> data){
        mData = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.internalTextViewName);
        }
    }

    public interface OnLicenseSelectedListener {
        void onLicenseSelected(LicenseParser.License license);
    }
}
