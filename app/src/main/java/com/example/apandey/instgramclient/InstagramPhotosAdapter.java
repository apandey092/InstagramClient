package com.example.apandey.instgramclient;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by apandey on 10/25/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter <InstagramPhoto> {

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtLikes = (TextView)convertView.findViewById(R.id.txtLikes);
        TextView txtTime = (TextView)convertView.findViewById(R.id.txtTime);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        ImageView ivUser = (ImageView)convertView.findViewById(R.id.ivImageUser);

        tvCaption.setText(photo.caption);
        String userName = "<b>" +photo.userName+" - </b>";
        txtName.setText(Html.fromHtml(userName));
        String likes = "<b>" +photo.likesCount+" Likes </b>";
        txtLikes.setText(Html.fromHtml(likes));
        String uploadTime = DateUtils.getRelativeTimeSpanString(Long.parseLong(photo.uploadTime)*1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();

        txtTime.setText(uploadTime);

        ivPhoto.setImageResource(0);
        ivUser.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).fit().centerInside().into(ivPhoto);
        Picasso.with(getContext()).load(photo.userPhotoUrl).transform(new CircleTransform()).into(ivUser);
        return convertView;
    }


}
