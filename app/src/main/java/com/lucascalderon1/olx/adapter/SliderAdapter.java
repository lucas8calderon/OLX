package com.lucascalderon1.olx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.lucascalderon1.olx.R;
import com.lucascalderon1.olx.model.ImagemUpload;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private final List<ImagemUpload> urlsImagens;

    public SliderAdapter(List<ImagemUpload> urlsImagens) {
        this.urlsImagens = urlsImagens;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        ImagemUpload imagemUpload = urlsImagens.get(position);

        Picasso.get().load(imagemUpload.getCaminhoImagem()).into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return urlsImagens.size();
    }

    static class SliderAdapterVH extends ViewHolder {

        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageViewBackground);
        }
    }

}
