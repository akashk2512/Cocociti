package akash.com.cocociti_app.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import akash.com.cocociti_app.Model.Showcase;
import akash.com.cocociti_app.R;

/**
 * Created by Akash on 6/23/2017.
 */

public class ShowCaseAdapter extends RecyclerView.Adapter<ShowCaseAdapter.ViewHolder> {

    private Context context;
    private List<Showcase> showcaseList;
    Showcase showcasemodel;
    public ShowCaseAdapter(Context context, List<Showcase> list) {
        this.context = context;
        this.showcaseList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.feed_row_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        showcasemodel = showcaseList.get(position);
        holder.id.setText(showcasemodel.getId());
        holder.title.setText(showcasemodel.getTitle());
        holder.description.setText(showcasemodel.getDescription());
        holder.year.setText(showcasemodel.getYear());

    }

    @Override
    public int getItemCount() {
        return showcaseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView title;
        TextView description;
        TextView year;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.number);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.discription);
            year = (TextView) itemView.findViewById(R.id.year);

        }
    }
}
