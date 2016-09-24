package tumblr.mimic.com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import tumblr.mimic.com.bean.PostBean;
import tumblr.mimic.com.myapplication.R;

/**
 * Created by Ayush on 9/21/2016.
 */

public class PostsDataAdapter extends RecyclerView.Adapter<PostsDataAdapter.ViewHolder>{

    private ArrayList<PostBean> posts;
    Context context;

    public PostsDataAdapter(Context context,ArrayList<PostBean> posts) {
        this.posts = posts;
        this.context = context;
        for(PostBean post : posts){
            Log.i("id --",post.getId()+"");
        }
    }

    @Override
    public PostsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_posts,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsDataAdapter.ViewHolder viewHolder, final int position) {

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()  + "/"+ posts.get(position).getId() + ".jpg");
                try {
                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };


        viewHolder.postTitle.setText(posts.get(position).getPostTitle());
        Log.i("POSTS IMAGE",posts.get(position).getPostImage().toString());
        Picasso.with(context).load(posts.get(position).getPostImage()).into(target);
        viewHolder.postTitle.setMovementMethod(LinkMovementMethod.getInstance());
//        Glide.with(context).load(posts.get(position)).asGif().into(viewHolder.postImage);



    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView postImage;
        public TextView postTitle;

        public ViewHolder(View view){
            super(view);
            postTitle = (TextView) view.findViewById(R.id.title);
            postTitle.setMovementMethod(LinkMovementMethod.getInstance());
            postImage = (ImageView) view.findViewById(R.id.img_android);

        }
    }

}
