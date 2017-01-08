package pv.com.pvcloudgo.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import pv.com.pvcloudgo.BaseActivity;
import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.adapter.GridImgAdapter;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.LoginRespMsg;
import pv.com.pvcloudgo.utils.DESUtil;


public class CloudPointApplyActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_left_logo)
    ImageView toolbarLeftLogo;
    @Bind(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @Bind(R.id.toolbar_left_title)
    TextView toolbarLeftTitle;
    @Bind(R.id.toolbar_right_title)
    TextView toolbarRightTitle;
    @Bind(R.id.image_right)
    ImageView imageRight;
    @Bind(R.id.image_exit)
    ImageView imageExit;
    @Bind(R.id.help_gridView)
    GridView grid;
    @Bind(R.id.expanded_image)
    ImageView expandedImageView;
    @Bind(R.id.img_select_check)
    CheckBox imgSelectCheck;
    private final int REQUEST_IMAGE = 0x8;
    private final int MAX_IMG_NUM = 4;

    private GridImgAdapter gridImgAdapter;
    private ArrayList<String> selectedImages;

    private boolean isBigPicShow;
    /**
     * Hold a reference to the current animator, so that it can be canceled mid-way.
     */
    private Animator mCurrentAnimator;

    /**
     * The system "short" animation time duration, in milliseconds. This duration is ideal for
     * subtle animations or animations that occur very frequently.
     */
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_pp_apply);
        ButterKnife.bind(this);


        initToolBar();
        initView();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("申请服务点");

    }

    private void initView() {

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = 200;

        //grid
        selectedImages = new ArrayList<>();
        selectedImages.add(GridImgAdapter.NULL_IMG_PREFIX);

        imgSelectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imgSelectCheck.animate().scaleX(1.2f).scaleY(1.2f).setDuration(400).setInterpolator(new CycleInterpolator(2)).start();
                }
            }
        });

        gridImgAdapter = new GridImgAdapter(this, selectedImages, 4);
        grid.setAdapter(gridImgAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//
                if (position == selectedImages.size() - 1) {
                    if (getSelectedImgSize() == MAX_IMG_NUM) {
                        zoomImageFromThumb(view, selectedImages.get(position));
                        return;
                    }
                } else {//查看大图
                    zoomImageFromThumb(view, selectedImages.get(position));
                    return;
                }

                Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
// whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, MAX_IMG_NUM);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
// default select images (support array list)
                ArrayList<String> defaulSelectedArray = (ArrayList<String>) selectedImages.clone();
                defaulSelectedArray.remove(defaulSelectedArray.size() - 1);
                intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaulSelectedArray);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


//    public class asyncFeedBackTask extends AsyncTask<Void, Void, List<File>> {
//        private FeedBackReq req;
//
//        public asyncFeedBackTask(FeedBackReq req) {
//            this.req = req;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showProgressDialog("反馈提交中，请稍后...");
//        }
//
//        @Override
//        protected List<File> doInBackground(Void... params) {
//            List<File> mFiles = new ArrayList<>();
//            for (String path : selectedImages) {
//                if (!path.equals(GridImgAdapter.NULL_IMG_PREFIX)) {
//                    mFiles.add(new File(BitmapUtil.zipBitmap(path)));
//                }
//
//            }
//            return mFiles;
//        }
//
//        @Override
//        protected void onPostExecute(List<File> mFiles) {
//            super.onPostExecute(mFiles);
//            executeMultiRequest(URLConstant.METHOD_FEEDBACK, FeedBackResp.class, req, mFiles, new Response.Listener<FeedBackResp>() {
//                @Override
//                public void onResponse(FeedBackResp response) {
//                    hideProgressDialog();
//                    Toast.makeText(HelpActivity.this, "反馈已提交，我们将认真处理您的反馈", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    btnNext.setEnabled(true);
//                    Toast.makeText(HelpActivity.this, "提交失败，请重试", Toast.LENGTH_SHORT).show();
//                    hideProgressDialog();
//                }
//            }, false, "");
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // Get the result list of select image paths
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....
                addImgPath(path);
                gridImgAdapter.bind(selectedImages);
            }
        }
    }

    public int getSelectedImgSize() {
        if (selectedImages == null) return MAX_IMG_NUM;
        int num = 0;
        for (String path : selectedImages) {
            if (!path.equals(GridImgAdapter.NULL_IMG_PREFIX))
                num++;

        }
        return num;
    }

    public void addImgPath(ArrayList<String> paths) {
//        if(selectedImages.size()==MAX_IMG_NUM)selectedImages.remove(selectedImages.size()-1);
        selectedImages = paths;
        if (selectedImages.size() < MAX_IMG_NUM)
            selectedImages.add(GridImgAdapter.NULL_IMG_PREFIX);
        return;
    }

    public void removeImgPath(String path) {
        if (selectedImages == null || selectedImages.size() <= 0) return;

        selectedImages.remove(path);

        if (!selectedImages.contains(GridImgAdapter.NULL_IMG_PREFIX))
            selectedImages.add(GridImgAdapter.NULL_IMG_PREFIX);
        return;
    }


    /**
     * **********************************图片放大************************************
     */

    /**
     * "Zooms" in a thumbnail view by assigning the high resolution image to a hidden "zoomed-in"
     * image view and animating its bounds to fit the entire activity content area. More
     * specifically:
     * <p/>
     * <ol>
     * <li>Assign the high-res image to the hidden "zoomed-in" (expanded) image view.</li>
     * <li>Calculate the starting and ending bounds for the expanded view.</li>
     * <li>Animate each of four positioning/sizing properties (X, Y, SCALE_X, SCALE_Y)
     * simultaneously, from the starting bounds to the ending bounds.</li>
     * <li>Zoom back out by running the reverse animation on click.</li>
     * </ol>
     *
     * @param thumbView The thumbnail view to zoom in.
     * @param path      The high-resolution version of the image represented by the thumbnail.
     */
    private void zoomImageFromThumb(final View thumbView, final String path) {
        isBigPicShow = true;
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        imgSelectCheck.setChecked(true);
        // Load the high-resolution "zoomed-in" image.
        expandedImageView = (ImageView) findViewById(R.id.expanded_image);
//
//        expandedImageView.setImageDrawable(img);
        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        thumbView.setAlpha(0f);
//        Picasso.with(mContext).load(R.drawable.empty_photo).skipMemoryCache().placeholder(R.drawable.empty_photo).into(expandedImageView);
        Picasso.with(mContext).load(new File(path)).skipMemoryCache().placeholder(R.drawable.empty_photo).into(expandedImageView);
        expandedImageView.setVisibility(View.VISIBLE);
        expandedImageView.setAlpha(1f);
        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
                imgSelectCheck.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;

        expandedImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel, back to their
                // original values.
                AnimatorSet set = new AnimatorSet();
                if (imgSelectCheck.isChecked()) {
                    set
                            .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                            .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                            .with(ObjectAnimator
                                    .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                            .with(ObjectAnimator
                                    .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                    set.setInterpolator(new DecelerateInterpolator());
                    set.setDuration(mShortAnimationDuration);
                } else {
                    set
                            .play(ObjectAnimator.ofFloat(expandedImageView, View.ALPHA, 1f, 0.7f))
                            .with(ObjectAnimator
                                    .ofFloat(expandedImageView, View.SCALE_X, 1.1f))
                            .with(ObjectAnimator
                                    .ofFloat(expandedImageView, View.SCALE_Y, 1.1f));
                    set.setInterpolator(new AccelerateInterpolator());
                    set.setDuration(400);
                }

                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        imgSelectCheck.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                        if (!imgSelectCheck.isChecked()) {
                            removeImgPath(path);
                            gridImgAdapter.bind(selectedImages);
                        }
                        isBigPicShow = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        imgSelectCheck.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                        isBigPicShow = false;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (isBigPicShow && expandedImageView != null) {
            expandedImageView.performClick();
            return;
        }
        super.onBackPressed();
    }


    public void login(View view) {


        String phone = null;

        String pwd = null;


        Map<String, Object> params = new HashMap<>(2);
        params.put("phone", phone);
        params.put("password", DESUtil.encode(Contants.DES_KEY, pwd));

        mHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {


            @Override
            public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {


                App application = App.getInstance();
                application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());

                if (application.getIntent() == null) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                    application.jumpToTargetActivity(mContext);
                    finish();

                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });


    }

}
