package pattaraporn.kerrykfm.com.mycalculatebasic;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

//AsyncTask<String,Void,String>  string , void คือ Process , ได้อะไรคืนมา
public class GetCurrentMoneyRate extends AsyncTask<String,Void,String> {
    // context เปรียบเหมือนท่อส่องข้อมูล จาก Fragment สิ่งนี้เรียก เทรด

    Context context;

    public GetCurrentMoneyRate(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
} //End Class
