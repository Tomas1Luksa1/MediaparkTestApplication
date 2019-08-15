package com.example.mediaparktestapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mediaparktestapplication.MainActivity;
import com.example.mediaparktestapplication.R;
import com.example.mediaparktestapplication.models.Car;
import com.example.mediaparktestapplication.models.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Car> mCars;
    private Context mContext;

    public RecyclerAdapter(Context context, List<Car> cars) {
        mCars = cars;
        mContext = context;
    }

    public void setCars(final List<Car> cars) {
        this.mCars = cars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Set TextViews
        ((ViewHolder)viewHolder).mModelName.setText(mCars.get(i).getModel().getTitle());
        ((ViewHolder)viewHolder).mPlate.setText(mCars.get(i).getPlateNumber());
        ((ViewHolder)viewHolder).mBattery.setText("Battery: " + mCars.get(i).getBatteryPercentage());
        ((ViewHolder)viewHolder).mDistance.setText("Distance: " + CalculateDistance(mCars.get(i).getLocation()) + " km");

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mCars.get(i).getModel().getPhotoUrl())
                .into(((ViewHolder)viewHolder).mImage);
    }

    @Override
    public int getItemCount() {
        return mCars == null ? 0 : mCars.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImage;
        private TextView mModelName;
        private TextView mPlate;
        private TextView mBattery;
        private TextView mDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mPlate = itemView.findViewById(R.id.plate);
            mBattery = itemView.findViewById(R.id.battery);
            mModelName = itemView.findViewById(R.id.model_name);
            mDistance = itemView.findViewById(R.id.distance);
        }
    }

    public static double CalculateDistance(Location location){
        LatLng latLngLocation = new LatLng(location.getLatitude(), location.getLongitude());
        double distance = SphericalUtil.computeDistanceBetween(MainActivity.getCurrentLocation(), latLngLocation);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(distance / 1000));
    }
}
