package pv.com.pvcloudgo.vc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.w4lle.library.NineGridAdapter;
import com.w4lle.library.NineGridlayout;

import java.util.List;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.model.bean.Order;
import pv.com.pvcloudgo.model.bean.OrderItem;


/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class MyOrderAdapter extends SimpleAdapter<Order> {





    public MyOrderAdapter(Context context, List<Order> datas) {
        super(context, R.layout.template_my_orders,datas);


    }


    @Override
    protected void convert(BaseViewHolder viewHoder, final Order item) {

        viewHoder.getTextView(R.id.txt_order_num).setText("订单号："+item.getOrderNum());
        viewHoder.getTextView(R.id.txt_order_money).setText("实付金额： ￥："+item.getAmount());

        TextView txtStatus = viewHoder.getTextView(R.id.txt_status);


        switch (item.getStatus()){

            case Order.STATUS_SUCCESS:
                txtStatus.setText("成功");
                txtStatus.setTextColor(Color.parseColor("#ff4CAF50"));
                break;


            case Order.STATUS_PAY_FAIL:
                txtStatus.setText("支付失败");
                txtStatus.setTextColor(Color.parseColor("#ffF44336"));
                break;

            case Order.STATUS_PAY_WAIT:
                txtStatus.setText("等待支付");
                txtStatus.setTextColor(Color.parseColor("#ffFFEB3B"));
                break;

        }


       NineGridlayout nineGridlayout= (NineGridlayout) viewHoder.getView(R.id.iv_ngrid_layout);

        nineGridlayout.setGap(5);
        nineGridlayout.setDefaultWidth(50);
        nineGridlayout.setDefaultHeight(50);

        nineGridlayout.setAdapter(new OrderItemAdapter(context,item.getItems()));




    }


    class  OrderItemAdapter extends NineGridAdapter{

        private  List<OrderItem> items ;

        public OrderItemAdapter(Context context, List<OrderItem> items) {
            super(context, items);
            this.items = items;
        }

        @Override
        public int getCount() {
            return (items == null) ? 0 : items.size();
        }

        @Override
        public String getUrl(int position) {

            OrderItem item = getItem(position);

            return  item.getWares().getImgUrl();

        }

        @Override
        public OrderItem getItem(int position) {
            return (items == null) ? null : items.get(position);
        }

        @Override
        public long getItemId(int position) {

            OrderItem item = getItem(position);
            return item==null?0:item.getId();
        }

        @Override
        public View getView(int i, View view) {

            ImageView iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
            Picasso.with(context).load(getUrl(i)).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
            return iv;
        }


    }










}
