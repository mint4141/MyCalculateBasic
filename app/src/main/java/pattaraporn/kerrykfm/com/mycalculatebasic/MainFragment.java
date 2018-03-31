package pattaraporn.kerrykfm.com.mycalculatebasic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// support.v4.app จะได้ api ล่าสุดเลย
public class MainFragment extends Fragment{
//การบ้าน เพิ่มวันที่ update โดย substring , เป็นArray แล้วค่อย substring
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show Rate
        TextView textView = getView().findViewById(R.id.txtShowRate);
        String urlAPI = "http://api.fixer.io/latest?symbols=THB&base=USD";
        try {
            GetCurrentMoneyRate getCurrentMoneyRate = new GetCurrentMoneyRate(getActivity());
            getCurrentMoneyRate.execute(urlAPI);
            String jsonString = getCurrentMoneyRate.get();
            Log.d("31MachV1", "json0 ==>" + jsonString);

            jsonString = jsonString.substring(0, jsonString.length() - 2);
            Log.d("31MachV1", "json1 ==>" + jsonString);

            jsonString = jsonString.substring(jsonString.length() - 5, jsonString.length());
            Log.d("31MachV1", "json2 ==>" + jsonString);

            textView.setText("1 -->> "+jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }

    } //Main Medthod

    //คลิกขวา เลือก genarate และ ะลือก Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main,container,false);
       // container ดึงเอาทั้งหมด false  เป็นการปิดความปลอดภัย
        return view;
    }
}   // Main Class
