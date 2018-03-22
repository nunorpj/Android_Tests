package nunorpj.github.com.video_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        VideoView vidView = (VideoView) findViewById(R.id.videoView);

        vidView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demo);

        MediaController mController = new MediaController(this);

        mController.setAnchorView(vidView);

        vidView.setMediaController(mController);
        
        vidView.start();
    }
}
