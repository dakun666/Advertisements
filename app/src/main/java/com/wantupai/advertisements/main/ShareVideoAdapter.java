package com.wantupai.advertisements.main;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wantupai.advertisements.R;
import com.wantupai.advertisements.main.model.ShareVideoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuedakun on 2019-12-24 17:25
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class ShareVideoAdapter extends RecyclerView.Adapter<ShareVideoAdapter.ViewHolder> {

    public interface OnShareVideoItemClickListener {
        void playVideo(ShareVideoModel shareVideoModel);

        void moreOption(ShareVideoModel shareVideoModel);

        void shareVideo(ShareVideoModel shareVideoModel);
    }

    private final Context context;

    private final OnShareVideoItemClickListener onShareVideoItemClickListener;

    private final RequestOptions videoCoverGlideOption;

    private List<ShareVideoModel> dataList;

    public ShareVideoAdapter(Context context, OnShareVideoItemClickListener onShareVideoItemClickListener) {
        this.context = context;
        this.onShareVideoItemClickListener = onShareVideoItemClickListener;
        videoCoverGlideOption = new RequestOptions()
                .centerCrop();
    }

    public void setDataList(List<ShareVideoModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addDataList(List<ShareVideoModel> dataList) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_share_video, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        }
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ShareVideoModel data;

        private TextView commission;
        private ImageView cover;
        private TextView title;
        private TextView shareSurplusTimes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commission = itemView.findViewById(R.id.share_video_item_commission);
            cover = itemView.findViewById(R.id.share_video_item_cover);
            title = itemView.findViewById(R.id.share_video_item_title);
            shareSurplusTimes = itemView.findViewById(R.id.share_video_item_share_surplus_times);

            LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, shareSurplusTimes.getPaint().getTextSize(), context.getResources().getColor(R.color.default_gradient_color_0), context.getResources().getColor(R.color.default_gradient_color_1), Shader.TileMode.CLAMP);
            shareSurplusTimes.getPaint().setShader(mLinearGradient);

            itemView.findViewById(R.id.share_video_item_play).setOnClickListener(this);
            itemView.findViewById(R.id.video_share_item_more).setOnClickListener(this);
            itemView.findViewById(R.id.video_share_item_share).setOnClickListener(this);
        }

        public void onBind(int position) {
            if(dataList != null && dataList.size() > position) {
                data = dataList.get(position);
                commission.setText(context.getString(R.string.video_share_item_commission, data.getCommission()));
                Glide.with(context).load(data.getCoverUrl()).apply(videoCoverGlideOption).into(cover);
                title.setText(data.getTitle());
                shareSurplusTimes.setText(context.getString(R.string.video_share_item_share_surplus_times, data.getShareSurplusTimesHint()));
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.share_video_item_play:
                    if(onShareVideoItemClickListener != null) {
                        onShareVideoItemClickListener.playVideo(data);
                    }
                    break;
                case R.id.video_share_item_more:
                    if(onShareVideoItemClickListener != null) {
                        onShareVideoItemClickListener.moreOption(data);
                    }
                    break;
                case R.id.video_share_item_share:
                    if(onShareVideoItemClickListener != null) {
                        onShareVideoItemClickListener.shareVideo(data);
                    }
                    break;
            }
        }
    }
}
