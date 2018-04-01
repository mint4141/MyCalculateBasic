package pattaraporn.kerrykfm.com.mycalculatebasic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



// support.v4.app จะได้ api ล่าสุดเลย
public class MainFragment extends Fragment{

    private String text;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Show Rate
        showRate();

    } //Main Medthod

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_calculate) {
//            Replace Fragment
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMainFragment,CalculateFragment.calculateInstance(text))
                    .addToBackStack(null)
                    .commit();
            return true;
            //addToBackStack คงหน้าเดิมไว้ตอนกดBack
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_th));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("USD ==> THB");
        //อนุญาตให้เมนูมาอยู่ในtoolbar
        setHasOptionsMenu(true);
    }

    private void showRate() {
        TextView textView = getView().findViewById(R.id.txtShowRate);
        TextView txtDate = getView().findViewById(R.id.txtDate);
        String urlAPI = "http://api.fixer.io/latest?symbols=THB&base=USD";
        try {
            GetCurrentMoneyRate getCurrentMoneyRate = new GetCurrentMoneyRate(getActivity());
            getCurrentMoneyRate.execute(urlAPI);
            String jsonString = getCurrentMoneyRate.get().replace("}", "");
          /*  Log.d("31MachV1", "json0 ==>" + jsonString);

            jsonString = jsonString.substring(0, jsonString.length() - 2);
            Log.d("31MachV1", "json1 ==>" + jsonString);

            jsonString = jsonString.substring(jsonString.length() - 5, jsonString.length());
            Log.d("31MachV1", "json2 ==>" + jsonString);

            textView.setText("1 -->> "+jsonString);*/
            String[] jsonSplit = jsonString.split(",");
            Log.d("31MachV1", "json0 ==>" +jsonSplit[0]);
            txtDate.setText(jsonSplit[1].substring(8,18));
            text = jsonSplit[2].substring(15,20);
            textView.setText("1 -->> " + jsonSplit[2].substring(15,20));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //คลิกขวา เลือก genarate และ ะลือก Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main,container,false);
       // container ดึงเอาทั้งหมด false  เป็นการปิดความปลอดภัย
        return view;
    }
}   // Main Class
