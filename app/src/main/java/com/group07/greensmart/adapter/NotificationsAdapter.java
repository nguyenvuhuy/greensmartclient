package com.group07.greensmart.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group07.greensmart.R;
import com.group07.greensmart.listener.RecycleViewOnItemClickListener;
import com.group07.greensmart.model.Notifications;
import com.group07.greensmart.utils.DateUtils;
import com.group07.greensmart.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by nguyenvuhuy on 4/3/18.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private Context context;
    private ArrayList<Notifications> listNotifications;
    private RecycleViewOnItemClickListener recycleViewOnItemClickListener;

    public NotificationsAdapter(Context context, ArrayList<Notifications> listNotifications) {
        this.context = context;
        this.listNotifications = listNotifications;
    }

    public void setRecycleViewOnItemClickListener(RecycleViewOnItemClickListener recycleViewOnItemClickListener) {
        this.recycleViewOnItemClickListener = recycleViewOnItemClickListener;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_notifications, parent, false);
        return new NotificationsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {

        holder.setRecycleViewOnItemClickListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (recycleViewOnItemClickListener != null) {
                    recycleViewOnItemClickListener.onClick(view, position);
                }

            }

            @Override
            public void onLongClick(View view, int position) {
                if (recycleViewOnItemClickListener != null) {
                    recycleViewOnItemClickListener.onLongClick(view, position);
                }
            }
        });

        Notifications notification = listNotifications.get(position);
        holder.txtTitle.setText(notification.getTitle());
        holder.txtSubject.setText(StringUtils.substring(notification.getContent(), 50));
        holder.txtDate.setText(DateUtils.convertDate(notification.getCreatedAt(), DateUtils.DATE_FORMAT_SIMPLE));
        if (notification.isRead()) {
            holder.imgUnread.setVisibility(View.VISIBLE);
        } else {
            holder.imgUnread.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listNotifications.size();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txtTitle, txtSubject, txtDate;
        public ImageView imgUnread;
        private RecycleViewOnItemClickListener recycleViewOnItemClickListener;

        public NotificationsViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            txtTitle = (TextView) view.findViewById(R.id.txt_item_notifications_title);
            txtSubject = (TextView) view.findViewById(R.id.txt_item_notifications_subject);
            txtDate = (TextView) view.findViewById(R.id.txt_item_notifications_date);
            imgUnread = view.findViewById((R.id.img_item_notifications_unread));
        }

        public void setRecycleViewOnItemClickListener(RecycleViewOnItemClickListener recycleViewOnItemClickListener) {
            this.recycleViewOnItemClickListener = recycleViewOnItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (recycleViewOnItemClickListener != null) {
                recycleViewOnItemClickListener.onClick(view, getAdapterPosition());
            }

        }

        @Override
        public boolean onLongClick(View view) {
            if (recycleViewOnItemClickListener != null) {
                recycleViewOnItemClickListener.onLongClick(view, getAdapterPosition());
                return true;
            }
            return false;
        }
    }

}
