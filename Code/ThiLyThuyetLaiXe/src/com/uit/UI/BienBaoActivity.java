//package com.uit.UI;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.graphics.Rect;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.Html;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//import android.view.Window;
//import android.view.animation.AlphaAnimation;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.ListView;
//import android.widget.TabHost;
//import android.widget.TabWidget;
//import android.widget.TextView;
//import android.widget.TextView.OnEditorActionListener;
//import com.krazevina.thioto.objects.ItemBienbao;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//public class Bienbao extends Activity
//  implements TextView.OnEditorActionListener
//{
//  String[] astr;
//  Button back;
//  ArrayList<ItemBienbao> bb_biencam = new ArrayList();
//  ArrayList<ItemBienbao> bb_bienphu = new ArrayList();
//  ArrayList<ItemBienbao> bb_chidan = new ArrayList();
//  ArrayList<ItemBienbao> bb_hieulenh = new ArrayList();
//  ArrayList<ItemBienbao> bb_nguyhiem = new ArrayList();
//  ArrayList<ItemBienbao> bb_vachke = new ArrayList();
//  int bienbaohientai = -1;
//  int bienhientai = 0;
//  HorizontalPaper cuon_ngang;
//  HorizontalPaper cuon_ngang_grid;
//  int curr_postion_listall = -1;
//  int currentSreen = -1;
//  int currentscreen = 0;
//  int currentscreen1 = 0;
//  int currentview = -1;
//  private LinearLayout detailbienbaolinearlayout;
//  ProgressDialog dialog;
//  String[] dstr;
//  EditText edit;
//  String[] estr;
//  boolean first = true;
//  boolean fromgridview = false;
//  private GridView[] gridview = new GridView[6];
//  Handler handler;
//  HashMap<String, Integer> hash;
//  LinearLayout header1;
//  int height = -1;
//  LinearLayout idtitle;
//  ImageView[] image = new ImageView[6];
//  InputMethodManager inputMgr;
//  boolean isactive;
//  boolean isrunning;
//  boolean isshowedtoast = false;
//  boolean isshowedtoast1 = false;
//  String[] istr;
//  boolean isviewinglist = false;
//  LayoutInflater li;
//  LinearLayout[] lin3 = new LinearLayout[6];
//  LinearLayout linsearch;
//  LinearLayout linsearchinput;
//  LinearLayout linviewinggrid;
//  LinearLayout linviewinglist;
//  ListView list1;
//  ArrayList<ItemBienbao> list_bienhientai = new ArrayList();
//  private ArrayList<ItemBienbao> list_for_adapter;
//  private ArrayList<ItemBienbao> list_item_bienbao = new ArrayList();
//  ArrayList<ItemBienbao> list_khac = new ArrayList();
//  ArrayList<ItemBienbao> list_search = new ArrayList();
//  ArrayList<itembienbao> listallbienbao;
//  boolean load = true;
//  boolean loaddata = false;
//  TextView loaibientext;
//  private HashMap<String, Integer> map;
//  LinearLayout[] next = new LinearLayout[6];
//  boolean nochangetitle = false;
//  TextView[] noidung = new TextView[6];
//  int num_item_bienphu = 0;
//  int num_item_chidan = 0;
//  int num_item_hieulenh = 0;
//  int num_item_nguyhiem = 0;
//  int num_item_vachke = 0;
//  boolean ok = false;
//  String[] ostr;
//  String pharse;
//  int position_pressed = -1;
//  LinearLayout[] prev = new LinearLayout[6];
//  boolean search = false;
//  private Button select;
//  int size_item_bienphu = 0;
//  int size_item_chidan = 0;
//  int size_item_hieulenh = 0;
//  int size_item_nguyhiem = 0;
//  int size_item_vachke = 0;
//  int sttdatahientai = 0;
//  private HorizontalPaper.OnScreenSwitchListener switchListener = new HorizontalPaper.OnScreenSwitchListener()
//  {
//    public void onScreenSwitched(int paramInt)
//    {
//      if (paramInt > 1)
//        if (1 + Bienbao.this.bienbaohientai <= 205)
//        {
//          Bienbao localBienbao4 = Bienbao.this;
//          localBienbao4.bienbaohientai = (1 + localBienbao4.bienbaohientai);
//          Bienbao.this.adddatatoView(0, Bienbao.this.bienbaohientai - 1);
//          Bienbao.this.adddatatoView(1, Bienbao.this.bienbaohientai);
//          Bienbao.changelayout_next localchangelayout_next = new Bienbao.changelayout_next(Bienbao.this);
//          Void[] arrayOfVoid2 = new Void[1];
//          arrayOfVoid2[0] = null;
//          localchangelayout_next.execute(arrayOfVoid2);
//        }
//      while (true)
//      {
//        if ((paramInt == 0) && (!Bienbao.this.isshowedtoast1))
//          Bienbao.this.isshowedtoast1 = true;
//        if ((paramInt == 206) && (!Bienbao.this.isshowedtoast1))
//          Bienbao.this.isshowedtoast1 = true;
//        if ((Bienbao.this.currentscreen1 != 0) && (Bienbao.this.currentscreen1 != 206))
//          Bienbao.this.isshowedtoast1 = false;
//        Bienbao.this.currentscreen1 = paramInt;
//        return;
//        if (paramInt < 1)
//        {
//          if (Bienbao.this.bienbaohientai - 1 < 1)
//            continue;
//          Bienbao localBienbao3 = Bienbao.this;
//          localBienbao3.bienbaohientai -= 1;
//          Bienbao.this.adddatatoView(1, Bienbao.this.bienbaohientai);
//          Bienbao.this.adddatatoView(2, 1 + Bienbao.this.bienbaohientai);
//          Bienbao.changelayout_prev localchangelayout_prev = new Bienbao.changelayout_prev(Bienbao.this);
//          Void[] arrayOfVoid1 = new Void[1];
//          arrayOfVoid1[0] = null;
//          localchangelayout_prev.execute(arrayOfVoid1);
//          continue;
//        }
//        if (Bienbao.this.bienbaohientai == 206)
//        {
//          Bienbao localBienbao2 = Bienbao.this;
//          localBienbao2.bienbaohientai -= 1;
//          continue;
//        }
//        if (Bienbao.this.bienbaohientai != 0)
//          continue;
//        Bienbao localBienbao1 = Bienbao.this;
//        localBienbao1.bienbaohientai = (1 + localBienbao1.bienbaohientai);
//      }
//    }
//  };
//  private HorizontalPaper.OnScreenSwitchListener switchListener_grid = new HorizontalPaper.OnScreenSwitchListener()
//  {
//    public void onScreenSwitched(int paramInt)
//    {
//      if ((paramInt == 0) && (!Bienbao.this.isshowedtoast))
//        Bienbao.this.isshowedtoast = true;
//      if ((paramInt == 5) && (!Bienbao.this.isshowedtoast))
//        Bienbao.this.isshowedtoast = true;
//      if ((Bienbao.this.currentscreen != 0) && (Bienbao.this.currentscreen != 5))
//        Bienbao.this.isshowedtoast = false;
//      Bienbao.this.currentscreen = paramInt;
//      Bienbao.this.sttdatahientai = paramInt;
//      Log.d("sttdatahientai", Bienbao.this.sttdatahientai);
//      if (!Bienbao.this.xemtungvung);
//      while (true)
//      {
//        return;
//        if (Bienbao.this.sttdatahientai == 0)
//        {
//          Bienbao.this.setTextButton("Biá»ƒn cáº¥m");
//          Bienbao.this.textheader.setText("Biá»ƒn cáº¥m");
//          Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_biencams, Bienbao.this.map));
//          Bienbao.this.list_for_adapter = Toancuc.bb_biencams;
//        }
//        if (Bienbao.this.sttdatahientai == 1)
//        {
//          Bienbao.this.setTextButton("Nguy hiá»ƒm");
//          Bienbao.this.textheader.setText("Biá»ƒn nguy hiá»ƒm");
//          Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_nguyhiems, Bienbao.this.map));
//          Bienbao.this.list_for_adapter = Toancuc.bb_nguyhiems;
//        }
//        if (Bienbao.this.sttdatahientai == 2)
//        {
//          Bienbao.this.setTextButton("Hiá»‡u lá»‡nh");
//          Bienbao.this.textheader.setText("Biá»ƒn hiá»‡u lá»‡nh");
//          Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_hieulenhs, Bienbao.this.map));
//          Bienbao.this.list_for_adapter = Toancuc.bb_hieulenhs;
//        }
//        if (Bienbao.this.sttdatahientai == 3)
//        {
//          Bienbao.this.setTextButton("Chá»‰ dáº«n");
//          Bienbao.this.textheader.setText("Biá»ƒn chá»‰ dáº«n");
//          Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_chidans, Bienbao.this.map));
//          Bienbao.this.list_for_adapter = Toancuc.bb_chidans;
//        }
//        if (Bienbao.this.sttdatahientai == 4)
//        {
//          Bienbao.this.setTextButton("Biá»ƒn phá»¥");
//          Bienbao.this.textheader.setText("Biá»ƒn phá»¥");
//          Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_bienphus, Bienbao.this.map));
//          Bienbao.this.list_for_adapter = Toancuc.bb_bienphus;
//        }
//        if (Bienbao.this.sttdatahientai != 5)
//          continue;
//        Bienbao.this.setTextButton("Váº¡ch káº»");
//        Bienbao.this.textheader.setText("Váº¡ch káº»");
//        Bienbao.this.list1.setAdapter(new BienbaoListAdapter(Bienbao.this, 2130903050, Toancuc.bb_vachkes, Bienbao.this.map));
//        Bienbao.this.list_for_adapter = Toancuc.bb_vachkes;
//      }
//    }
//  };
//  TextView[] tenbienbao = new TextView[6];
//  TextView textheader;
//  String[] ustr;
//  private Button viewmode;
//  View viewpressed = null;
//  boolean xemtungvung = false;
//  boolean xulyclickitem;
//  int xx;
//  int xxsave;
//  String[] ystr;
//  int yy;
//  int yysave;
//
//  public Bienbao()
//  {
//    String[] arrayOfString1 = new String[1];
//    arrayOfString1[0] = "Ä‘";
//    this.dstr = arrayOfString1;
//    String[] arrayOfString2 = new String[17];
//    arrayOfString2[0] = "Ã¡";
//    arrayOfString2[1] = "Ã ";
//    arrayOfString2[2] = "áº¡";
//    arrayOfString2[3] = "Ã£";
//    arrayOfString2[4] = "áº£";
//    arrayOfString2[5] = "Ã¢";
//    arrayOfString2[6] = "áº¥";
//    arrayOfString2[7] = "áº§";
//    arrayOfString2[8] = "áº­";
//    arrayOfString2[9] = "áº«";
//    arrayOfString2[10] = "áº©";
//    arrayOfString2[11] = "Äƒ";
//    arrayOfString2[12] = "áº¯";
//    arrayOfString2[13] = "áº±";
//    arrayOfString2[14] = "áº·";
//    arrayOfString2[15] = "áºµ";
//    arrayOfString2[16] = "áº³";
//    this.astr = arrayOfString2;
//    String[] arrayOfString3 = new String[11];
//    arrayOfString3[0] = "Ã©";
//    arrayOfString3[1] = "Ã¨";
//    arrayOfString3[2] = "áº¹";
//    arrayOfString3[3] = "áº½";
//    arrayOfString3[4] = "áº»";
//    arrayOfString3[5] = "Ãª";
//    arrayOfString3[6] = "áº¿";
//    arrayOfString3[7] = "á»�";
//    arrayOfString3[8] = "á»‡";
//    arrayOfString3[9] = "á»…";
//    arrayOfString3[10] = "á»ƒ";
//    this.estr = arrayOfString3;
//    String[] arrayOfString4 = new String[5];
//    arrayOfString4[0] = "Ã­";
//    arrayOfString4[1] = "Ã¬";
//    arrayOfString4[2] = "á»‹";
//    arrayOfString4[3] = "Ä©";
//    arrayOfString4[4] = "á»‰";
//    this.istr = arrayOfString4;
//    String[] arrayOfString5 = new String[11];
//    arrayOfString5[0] = "Ãº";
//    arrayOfString5[1] = "Ã¹";
//    arrayOfString5[2] = "á»¥";
//    arrayOfString5[3] = "Å©";
//    arrayOfString5[4] = "á»§";
//    arrayOfString5[5] = "Æ°";
//    arrayOfString5[6] = "á»©";
//    arrayOfString5[7] = "á»«";
//    arrayOfString5[8] = "á»±";
//    arrayOfString5[9] = "á»¯";
//    arrayOfString5[10] = "á»­";
//    this.ustr = arrayOfString5;
//    String[] arrayOfString6 = new String[17];
//    arrayOfString6[0] = "Ã³";
//    arrayOfString6[1] = "Ã²";
//    arrayOfString6[2] = "á»�";
//    arrayOfString6[3] = "Ãµ";
//    arrayOfString6[4] = "á»�";
//    arrayOfString6[5] = "Ã´";
//    arrayOfString6[6] = "á»‘";
//    arrayOfString6[7] = "á»“";
//    arrayOfString6[8] = "á»™";
//    arrayOfString6[9] = "á»—";
//    arrayOfString6[10] = "á»•";
//    arrayOfString6[11] = "Æ¡";
//    arrayOfString6[12] = "á»›";
//    arrayOfString6[13] = "á»�";
//    arrayOfString6[14] = "á»£";
//    arrayOfString6[15] = "á»¡";
//    arrayOfString6[16] = "á»Ÿ";
//    this.ostr = arrayOfString6;
//    String[] arrayOfString7 = new String[5];
//    arrayOfString7[0] = "á»·";
//    arrayOfString7[1] = "á»³";
//    arrayOfString7[2] = "á»µ";
//    arrayOfString7[3] = "á»¹";
//    arrayOfString7[4] = "á»·";
//    this.ystr = arrayOfString7;
//    this.listallbienbao = new ArrayList();
//    this.hash = new HashMap();
//    this.isactive = true;
//    this.isrunning = true;
//    this.xulyclickitem = true;
//    this.handler = new Handler();
//  }
//
//  private void UnsetDetailBienbao()
//  {
//    this.detailbienbaolinearlayout.setVisibility(8);
//  }
//
//  private void addView()
//  {
//    int i = 1;
//    if (i >= 4)
//      this.detailbienbaolinearlayout.addView(this.cuon_ngang);
//    for (int k = 1; ; k++)
//    {
//      if (k >= 7)
//      {
//        this.linviewinggrid.addView(this.cuon_ngang_grid);
//        return;
//        int j = i;
//        LinearLayout localLinearLayout1 = (LinearLayout)this.li.inflate(2130903051, null);
//        this.cuon_ngang.addView(localLinearLayout1);
//        this.prev[(i - 1)] = ((LinearLayout)localLinearLayout1.findViewById(2131361861));
//        this.next[(i - 1)] = ((LinearLayout)localLinearLayout1.findViewById(2131361862));
//        this.tenbienbao[(i - 1)] = ((TextView)localLinearLayout1.findViewById(2131361855));
//        this.noidung[(i - 1)] = ((TextView)localLinearLayout1.findViewById(2131361864));
//        this.image[(i - 1)] = ((ImageView)localLinearLayout1.findViewById(2131361854));
//        this.prev[(j - 1)].setOnClickListener(new View.OnClickListener()
//        {
//          public void onClick(View paramView)
//          {
//            if (Bienbao.this.bienbaohientai == 0)
//              return;
//            if (Bienbao.this.bienbaohientai == 404)
//              Bienbao.this.cuon_ngang.setCurrentScreen(1, true);
//            while (true)
//            {
//              Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.prev[1].getWindowToken(), 0);
//              break;
//              Bienbao.this.cuon_ngang.setCurrentScreen(0, true);
//            }
//          }
//        });
//        this.next[(j - 1)].setOnClickListener(new View.OnClickListener()
//        {
//          public void onClick(View paramView)
//          {
//            if (Bienbao.this.bienbaohientai == 404)
//              return;
//            if (Bienbao.this.bienbaohientai == 0)
//              Bienbao.this.cuon_ngang.setCurrentScreen(1, true);
//            while (true)
//            {
//              Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.next[1].getWindowToken(), 0);
//              break;
//              Bienbao.this.cuon_ngang.setCurrentScreen(2, true);
//            }
//          }
//        });
//        i++;
//        break;
//      }
//      int m = k;
//      LinearLayout localLinearLayout2 = (LinearLayout)this.li.inflate(2130903073, null);
//      this.gridview[(m - 1)] = ((GridView)localLinearLayout2.findViewById(2131361929));
//      this.gridview[(m - 1)].setOnTouchListener(new View.OnTouchListener(m)
//      {
//        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
//        {
//          Bienbao.this.xx = (int)paramMotionEvent.getX();
//          Bienbao.this.yy = (int)paramMotionEvent.getY();
//          int i = Bienbao.this.gridview[(this.val$jj - 1)].pointToPosition(Bienbao.this.xx, Bienbao.this.yy) - Bienbao.this.gridview[(this.val$jj - 1)].getFirstVisiblePosition();
//          int j;
//          if (paramMotionEvent.getAction() == 0)
//            if (Bienbao.this.gridview[(this.val$jj - 1)].getChildAt(i) == null)
//            {
//              Log.d("ACTION_DOWN null", "actiondown null");
//              j = 0;
//            }
//          while (true)
//          {
//            return j;
//            AlphaAnimation localAlphaAnimation3 = new AlphaAnimation(0.3F, 0.3F);
//            localAlphaAnimation3.setDuration(100L);
//            localAlphaAnimation3.setFillAfter(true);
//            Bienbao.this.position_pressed = i;
//            Bienbao.this.xxsave = Bienbao.this.xx;
//            Bienbao.this.yysave = Bienbao.this.yy;
//            Bienbao.this.viewpressed = Bienbao.this.gridview[(this.val$jj - 1)].getChildAt(i);
//            Bienbao.this.viewpressed.startAnimation(localAlphaAnimation3);
//            j = 0;
//            continue;
//            if ((Math.abs(Bienbao.this.xx - Bienbao.this.xxsave) < 10) && (Math.abs(Bienbao.this.yy - Bienbao.this.yysave) < 10))
//              break;
//            if (Bienbao.this.viewpressed == null)
//            {
//              j = 0;
//              continue;
//            }
//            AlphaAnimation localAlphaAnimation1 = new AlphaAnimation(1.0F, 1.0F);
//            localAlphaAnimation1.setDuration(0L);
//            localAlphaAnimation1.setFillAfter(true);
//            Bienbao.this.viewpressed.startAnimation(localAlphaAnimation1);
//          }
//          if (paramMotionEvent.getAction() == 1)
//          {
//            Log.d("action up", "action up");
//            AlphaAnimation localAlphaAnimation2 = new AlphaAnimation(1.0F, 1.0F);
//            localAlphaAnimation2.setDuration(0L);
//            localAlphaAnimation2.setFillAfter(true);
//            Bienbao.this.viewpressed.startAnimation(localAlphaAnimation2);
//          }
//          if ((paramMotionEvent.getAction() == 1) && (i == Bienbao.this.position_pressed))
//          {
//            ItemBienbao localItemBienbao = (ItemBienbao)Bienbao.this.list_item_bienbao.get(i + Bienbao.this.getsttview(Bienbao.this.sttdatahientai).from + Bienbao.this.gridview[(this.val$jj - 1)].getFirstVisiblePosition());
//            Log.d("id bien", localItemBienbao.id);
//            Bienbao.this.detailbienbaolinearlayout.setVisibility(0);
//            Bienbao.this.back.setVisibility(0);
//            Bienbao.this.linviewinggrid.setVisibility(8);
//            Bienbao.this.viewmode.setVisibility(8);
//            Bienbao.this.linsearch.setVisibility(8);
//            Bienbao.this.fromgridview = true;
//            Bienbao.this.bienbaohientai = (Integer.parseInt(localItemBienbao.id) - 1);
//            if (Bienbao.this.bienbaohientai != 0)
//              break label633;
//            Bienbao.this.adddatatoView(0, Bienbao.this.bienbaohientai);
//            Bienbao.this.adddatatoView(1, 1 + Bienbao.this.bienbaohientai);
//            Bienbao.this.adddatatoView(2, 2 + Bienbao.this.bienbaohientai);
//            Bienbao.this.cuon_ngang.setCurrentScreen(0, false);
//          }
//          while (true)
//          {
//            j = 0;
//            break;
//            label633: if (Bienbao.this.bienbaohientai == 206)
//            {
//              Bienbao.this.adddatatoView(0, Bienbao.this.bienbaohientai - 2);
//              Bienbao.this.adddatatoView(1, Bienbao.this.bienbaohientai - 1);
//              Bienbao.this.adddatatoView(2, Bienbao.this.bienbaohientai);
//              Bienbao.this.cuon_ngang.setCurrentScreen(2, false);
//              continue;
//            }
//            Bienbao.this.adddatatoView(0, Bienbao.this.bienbaohientai - 1);
//            Bienbao.this.adddatatoView(1, Bienbao.this.bienbaohientai);
//            Bienbao.this.adddatatoView(2, 1 + Bienbao.this.bienbaohientai);
//            Bienbao.this.cuon_ngang.setCurrentScreen(1, false);
//          }
//        }
//      });
//      this.cuon_ngang_grid.addView(localLinearLayout2);
//    }
//  }
//
//  private void adddatatoGridView(int paramInt1, int paramInt2)
//  {
//    ItemView localItemView = getsttview(paramInt2);
//    this.gridview[paramInt1].setAdapter(new BienbaoGridAdapter(localItemView.from, localItemView.to, this, this.list_item_bienbao, this.map));
//  }
//
//  private void adddatatoView(int paramInt1, int paramInt2)
//  {
//    Log.d("addatatoview tai vi tri", paramInt2);
//    ItemBienbao localItemBienbao = (ItemBienbao)this.list_item_bienbao.get(paramInt2);
//    this.image[paramInt1].setImageResource(((Integer)this.map.get(localItemBienbao.link_anh)).intValue());
//    this.tenbienbao[paramInt1].setText("  " + localItemBienbao.ten_bien_bao);
//    this.noidung[paramInt1].setText("  " + localItemBienbao.noi_dung);
//  }
//
//  private ArrayList<ItemBienbao> combine()
//  {
//    ArrayList localArrayList = new ArrayList();
//    Iterator localIterator1 = this.list_bienhientai.iterator();
//    Iterator localIterator2;
//    if (!localIterator1.hasNext())
//    {
//      ItemBienbao localItemBienbao = new ItemBienbao();
//      localItemBienbao.ten_bien_bao = "khac";
//      localArrayList.add(localItemBienbao);
//      localIterator2 = this.list_khac.iterator();
//    }
//    while (true)
//    {
//      if (!localIterator2.hasNext())
//      {
//        Log.d("so ket qua", localArrayList.size());
//        this.list_for_adapter = localArrayList;
//        return localArrayList;
//        localArrayList.add((ItemBienbao)localIterator1.next());
//        break;
//      }
//      localArrayList.add((ItemBienbao)localIterator2.next());
//    }
//  }
//
//  private String convert(String paramString)
//  {
//    String[] arrayOfString1 = this.astr;
//    int i = arrayOfString1.length;
//    int j = 0;
//    String[] arrayOfString2;
//    int m;
//    label31: String[] arrayOfString3;
//    int i1;
//    label52: String[] arrayOfString4;
//    int i3;
//    label73: String[] arrayOfString5;
//    int i5;
//    label94: String[] arrayOfString6;
//    int i7;
//    label115: String[] arrayOfString7;
//    int i8;
//    if (j >= i)
//    {
//      arrayOfString2 = this.dstr;
//      int k = arrayOfString2.length;
//      m = 0;
//      if (m < k)
//        break label163;
//      arrayOfString3 = this.estr;
//      int n = arrayOfString3.length;
//      i1 = 0;
//      if (i1 < n)
//        break label182;
//      arrayOfString4 = this.istr;
//      int i2 = arrayOfString4.length;
//      i3 = 0;
//      if (i3 < i2)
//        break label201;
//      arrayOfString5 = this.ustr;
//      int i4 = arrayOfString5.length;
//      i5 = 0;
//      if (i5 < i4)
//        break label220;
//      arrayOfString6 = this.ostr;
//      int i6 = arrayOfString6.length;
//      i7 = 0;
//      if (i7 < i6)
//        break label239;
//      arrayOfString7 = this.ystr;
//      i8 = arrayOfString7.length;
//    }
//    for (int i9 = 0; ; i9++)
//    {
//      if (i9 >= i8)
//      {
//        return paramString;
//        paramString = paramString.replace(arrayOfString1[j], "a");
//        j++;
//        break;
//        label163: paramString = paramString.replace(arrayOfString2[m], "d");
//        m++;
//        break label31;
//        label182: paramString = paramString.replace(arrayOfString3[i1], "e");
//        i1++;
//        break label52;
//        label201: paramString = paramString.replace(arrayOfString4[i3], "i");
//        i3++;
//        break label73;
//        label220: paramString = paramString.replace(arrayOfString5[i5], "u");
//        i5++;
//        break label94;
//        label239: paramString = paramString.replace(arrayOfString6[i7], "o");
//        i7++;
//        break label115;
//      }
//      paramString = paramString.replace(arrayOfString7[i9], "y");
//    }
//  }
//
//  private ArrayList<ItemBienbao> copy(ArrayList<ItemBienbao> paramArrayList)
//  {
//    ArrayList localArrayList = new ArrayList();
//    for (int i = 0; ; i++)
//    {
//      if (i >= paramArrayList.size())
//        return localArrayList;
//      ItemBienbao localItemBienbao1 = (ItemBienbao)paramArrayList.get(i);
//      ItemBienbao localItemBienbao2 = new ItemBienbao();
//      localItemBienbao2.id = localItemBienbao1.id;
//      localItemBienbao2.ten_bien_bao = localItemBienbao1.ten_bien_bao;
//      localItemBienbao2.noi_dung = localItemBienbao1.noi_dung;
//      localItemBienbao2.link_anh = localItemBienbao1.link_anh;
//      localItemBienbao2.phan_loai = localItemBienbao1.phan_loai;
//      localArrayList.add(localItemBienbao2);
//    }
//  }
//
//  private void exe()
//  {
//    this.handler.post(new Runnable()
//    {
//      public void run()
//      {
//        Rect localRect = new Rect();
//        Bienbao.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
//        if (localRect.bottom < Bienbao.this.height)
//        {
//          Tab.tabwidget.setVisibility(8);
//          Bienbao.this.xulyclickitem = false;
//        }
//        while (true)
//        {
//          return;
//          Bienbao.this.xulyclickitem = true;
//          Tab.tabwidget.setVisibility(0);
//        }
//      }
//    });
//  }
//
//  private void getDataBienbao()
//  {
//    this.list_item_bienbao.clear();
//    Log.d("at bienbao toancuc.list_item_bienbao", Toancuc.list_item_bienbao.size());
//    this.list_item_bienbao = copy(Toancuc.list_item_bienbao);
//    Log.d("Du lieu sqlite ", this.list_item_bienbao.size() + " hang ");
//  }
//
//  private String getTitle(int paramInt)
//  {
//    String str;
//    switch (paramInt)
//    {
//    default:
//      str = null;
//    case 1:
//    case 2:
//    case 3:
//    case 4:
//    case 5:
//    case 6:
//    }
//    while (true)
//    {
//      return str;
//      str = "Biá»ƒn cáº¥m";
//      continue;
//      str = "Biá»ƒn nguy hiá»ƒm";
//      continue;
//      str = "Biá»ƒn hiá»‡u lá»‡nh";
//      continue;
//      str = "Biá»ƒn chá»‰ dáº«n";
//      continue;
//      str = "Biá»ƒn phá»¥";
//      continue;
//      str = "Váº¡ch káº»";
//    }
//  }
//
//  private ItemView getsttview(int paramInt)
//  {
//    ItemView localItemView = null;
//    switch (paramInt)
//    {
//    default:
//    case 0:
//    case 1:
//    case 2:
//    case 3:
//    case 4:
//    case 5:
//    }
//    while (true)
//    {
//      return localItemView;
//      localItemView = new ItemView(0, 45);
//      continue;
//      localItemView = new ItemView(46, 97);
//      continue;
//      localItemView = new ItemView(98, 115);
//      continue;
//      localItemView = new ItemView(116, 159);
//      continue;
//      localItemView = new ItemView(160, 174);
//      continue;
//      localItemView = new ItemView(175, 206);
//    }
//  }
//
//  private void initData(ArrayList<ItemBienbao> paramArrayList)
//  {
//    this.bb_biencam.clear();
//    this.bb_nguyhiem.clear();
//    this.bb_hieulenh.clear();
//    this.bb_chidan.clear();
//    this.bb_bienphu.clear();
//    this.bb_vachke.clear();
//    Iterator localIterator = paramArrayList.iterator();
//    while (true)
//    {
//      if (!localIterator.hasNext())
//        return;
//      ItemBienbao localItemBienbao = (ItemBienbao)localIterator.next();
//      switch (Integer.parseInt(localItemBienbao.phan_loai))
//      {
//      default:
//        Log.d("Eror", "Fatal");
//        break;
//      case 1:
//        this.bb_biencam.add(localItemBienbao);
//        break;
//      case 2:
//        this.bb_nguyhiem.add(localItemBienbao);
//        break;
//      case 3:
//        this.bb_hieulenh.add(localItemBienbao);
//        break;
//      case 4:
//        this.bb_chidan.add(localItemBienbao);
//        break;
//      case 5:
//        this.bb_bienphu.add(localItemBienbao);
//        break;
//      case 6:
//        this.bb_vachke.add(localItemBienbao);
//      }
//    }
//  }
//
//  /** @deprecated */
//  private void initData_showAll(ArrayList<ItemBienbao> paramArrayList)
//  {
//    monitorenter;
//    while (true)
//    {
//      Iterator localIterator2;
//      Iterator localIterator3;
//      Iterator localIterator4;
//      Iterator localIterator5;
//      Iterator localIterator6;
//      try
//      {
//        paramArrayList.clear();
//        Log.d("so phan tu cua Lisitembienbao ", this.list_item_bienbao.size());
//        paramArrayList.clear();
//        Log.d("bb_biencam.size", this.bb_biencam.size());
//        Iterator localIterator1 = this.bb_biencam.iterator();
//        if (localIterator1.hasNext())
//          continue;
//        ItemBienbao localItemBienbao1 = new ItemBienbao();
//        localItemBienbao1.ten_bien_bao = "nguyhiem";
//        paramArrayList.add(localItemBienbao1);
//        localIterator2 = this.bb_nguyhiem.iterator();
//        if (!localIterator2.hasNext())
//        {
//          ItemBienbao localItemBienbao2 = new ItemBienbao();
//          localItemBienbao2.ten_bien_bao = "hieulenh";
//          paramArrayList.add(localItemBienbao2);
//          localIterator3 = this.bb_hieulenh.iterator();
//          if (localIterator3.hasNext())
//            break label755;
//          ItemBienbao localItemBienbao3 = new ItemBienbao();
//          localItemBienbao3.ten_bien_bao = "chidan";
//          paramArrayList.add(localItemBienbao3);
//          localIterator4 = this.bb_chidan.iterator();
//          if (localIterator4.hasNext())
//            break label773;
//          ItemBienbao localItemBienbao4 = new ItemBienbao();
//          localItemBienbao4.ten_bien_bao = "bienphu";
//          paramArrayList.add(localItemBienbao4);
//          localIterator5 = this.bb_bienphu.iterator();
//          if (localIterator5.hasNext())
//            break label791;
//          ItemBienbao localItemBienbao5 = new ItemBienbao();
//          localItemBienbao5.ten_bien_bao = "vachke";
//          paramArrayList.add(localItemBienbao5);
//          localIterator6 = this.bb_vachke.iterator();
//          if (localIterator6.hasNext())
//            break label809;
//          this.size_item_nguyhiem = (this.bb_nguyhiem.size() + this.bb_biencam.size());
//          this.size_item_hieulenh = (1 + (this.bb_hieulenh.size() + this.size_item_nguyhiem));
//          this.size_item_chidan = (1 + (this.bb_chidan.size() + this.size_item_hieulenh));
//          this.size_item_bienphu = (1 + (this.bb_bienphu.size() + this.size_item_chidan));
//          this.size_item_vachke = (1 + (this.bb_vachke.size() + this.size_item_bienphu));
//          this.num_item_nguyhiem = this.bb_nguyhiem.size();
//          this.num_item_hieulenh = this.bb_hieulenh.size();
//          this.num_item_chidan = this.bb_chidan.size();
//          this.num_item_bienphu = this.bb_bienphu.size();
//          this.num_item_vachke = this.bb_vachke.size();
//          Log.d(" size_item_nguyhiem (giá»›i háº¡n Ä‘á»ƒ cuá»™n)", this.size_item_nguyhiem);
//          Log.d(" size_item_hieulenh ", this.size_item_hieulenh);
//          Log.d(" size_item_chidan ", this.size_item_chidan);
//          Log.d(" size_item_bienphu ", this.size_item_bienphu);
//          Log.d(" size_item_vachke ", this.size_item_vachke);
//          Log.d(" num_item_nguyhiem ", this.num_item_nguyhiem);
//          Log.d(" num_item_hieulenh ", this.num_item_hieulenh);
//          Log.d(" num_item_chidan ", this.num_item_chidan);
//          Log.d(" num_item_bienphu ", this.num_item_bienphu);
//          Log.d(" num_item_vachke ", this.num_item_vachke);
//          Log.d("so phan tu cua Lisitembienbao ", this.list_item_bienbao.size());
//          return;
//          paramArrayList.add((ItemBienbao)localIterator1.next());
//          continue;
//        }
//      }
//      finally
//      {
//        monitorexit;
//      }
//      paramArrayList.add((ItemBienbao)localIterator2.next());
//      continue;
//      label755: paramArrayList.add((ItemBienbao)localIterator3.next());
//      continue;
//      label773: paramArrayList.add((ItemBienbao)localIterator4.next());
//      continue;
//      label791: paramArrayList.add((ItemBienbao)localIterator5.next());
//      continue;
//      label809: paramArrayList.add((ItemBienbao)localIterator6.next());
//    }
//  }
//
//  private boolean search(String paramString1, String paramString2)
//  {
//    Log.d(paramString1, paramString2);
//    if (convert(paramString2.toLowerCase()).toLowerCase().indexOf(convert(paramString1).trim()) != -1);
//    for (int i = 1; ; i = 0)
//      return i;
//  }
//
//  private void setAtributeList(ListView paramListView)
//  {
//    paramListView.setDivider(null);
//    paramListView.setDividerHeight(0);
//    paramListView.setFadingEdgeLength(0);
//    paramListView.setFocusable(false);
//    paramListView.setFocusableInTouchMode(false);
//  }
//
//  private void setClick()
//  {
//    this.idtitle.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramView)
//      {
//        if (!Bienbao.this.xulyclickitem)
//        {
//          Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.idtitle.getWindowToken(), 0);
//          Bienbao.this.xulyclickitem = true;
//        }
//      }
//    });
//    this.select.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramView)
//      {
//        CustomPopupWindow localCustomPopupWindow = new CustomPopupWindow(Bienbao.this.select, Bienbao.this);
//        localCustomPopupWindow.setContentView(2130903063);
//        localCustomPopupWindow.showDropDown();
//      }
//    });
//    this.list1.setOnItemClickListener(new AdapterView.OnItemClickListener()
//    {
//      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
//      {
//        Log.d("vi tri bien bao:", paramInt);
//        if (!Bienbao.this.xulyclickitem)
//        {
//          Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.list1.getWindowToken(), 0);
//          Bienbao.this.xulyclickitem = true;
//        }
//        while (true)
//        {
//          return;
//          if (((ItemBienbao)Bienbao.this.list_for_adapter.get(paramInt)).link_anh != null)
//          {
//            Bienbao.this.setDetailBienbao(paramInt);
//            Bienbao.this.detailbienbaolinearlayout.setVisibility(0);
//            Bienbao.this.back.setVisibility(0);
//            Bienbao.this.linsearch.setVisibility(8);
//            Bienbao.this.viewmode.setVisibility(8);
//            continue;
//          }
//        }
//      }
//    });
//    this.back.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramView)
//      {
//        if (Bienbao.this.fromgridview)
//        {
//          Bienbao.this.fromgridview = false;
//          Bienbao.this.linviewinggrid.setVisibility(0);
//        }
//        Bienbao.this.linsearch.setVisibility(0);
//        Bienbao.this.viewmode.setVisibility(0);
//        Bienbao.this.back.setVisibility(4);
//        Bienbao.this.UnsetDetailBienbao();
//        Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.back.getWindowToken(), 0);
//      }
//    });
//    this.header1.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramView)
//      {
//        Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.header1.getWindowToken(), 0);
//      }
//    });
//    this.viewmode.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramView)
//      {
//        Bienbao.this.inputMgr.hideSoftInputFromWindow(Bienbao.this.list1.getWindowToken(), 0);
//        if (Bienbao.this.isviewinglist)
//        {
//          Bienbao.this.isviewinglist = false;
//          Bienbao.this.linviewinglist.setVisibility(8);
//          Bienbao.this.linsearchinput.setVisibility(4);
//          Bienbao.this.linviewinggrid.setVisibility(0);
//          Bienbao.this.viewmode.setText("Xem dáº¡ng list");
//        }
//        while (true)
//        {
//          return;
//          Bienbao.this.isviewinglist = true;
//          Bienbao.this.linviewinglist.setVisibility(0);
//          Bienbao.this.linviewinggrid.setVisibility(8);
//          Bienbao.this.linsearchinput.setVisibility(0);
//          Bienbao.this.viewmode.setText("Xem dáº¡ng grid");
//          Bienbao.this.linsearchinput.setVisibility(0);
//        }
//      }
//    });
//  }
//
//  private void setCrollList()
//  {
//    this.list1.setOnScrollListener(new AbsListView.OnScrollListener()
//    {
//      public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
//      {
//        if (Bienbao.this.search)
//        {
//          Log.d("firstvisible", paramInt1);
//          Log.d("listkhac.size", Bienbao.this.list_bienhientai.size());
//          if (paramInt1 > Bienbao.this.list_bienhientai.size())
//            Bienbao.this.textheader.setText("NhÃ³m khÃ¡c : " + Bienbao.this.list_khac.size() + " biá»ƒn");
//        }
//        while (true)
//        {
//          return;
//          Bienbao.this.textheader.setText(Bienbao.this.getTitle(Bienbao.this.bienhientai) + ":" + Bienbao.this.list_bienhientai.size() + " biá»ƒn");
//          continue;
//          Log.d("first visible ", paramInt1);
//          if (Bienbao.this.nochangetitle)
//            continue;
//          Log.d("nochange title", "=true");
//          if (Bienbao.this.first)
//          {
//            Bienbao.this.textheader.setText("Biá»ƒn cáº¥m");
//            Bienbao.this.first = false;
//            continue;
//          }
//          if ((paramInt1 > Bienbao.this.bb_biencam.size()) && (paramInt1 <= Bienbao.this.size_item_nguyhiem))
//          {
//            Bienbao.this.textheader.setText("Biá»ƒn nguy hiá»ƒm");
//            continue;
//          }
//          if ((paramInt1 > Bienbao.this.size_item_nguyhiem) && (paramInt1 <= Bienbao.this.size_item_hieulenh))
//          {
//            Bienbao.this.textheader.setText("Biá»ƒn hiá»‡u lá»‡nh");
//            continue;
//          }
//          if ((paramInt1 > Bienbao.this.size_item_hieulenh) && (paramInt1 <= Bienbao.this.size_item_chidan))
//          {
//            Bienbao.this.textheader.setText("Biá»ƒn chá»‰ dáº«n");
//            continue;
//          }
//          if ((paramInt1 > Bienbao.this.size_item_chidan) && (paramInt1 <= Bienbao.this.size_item_bienphu))
//          {
//            Bienbao.this.textheader.setText("Biá»ƒn phá»¥");
//            continue;
//          }
//          if ((paramInt1 > Bienbao.this.size_item_bienphu) && (paramInt1 <= Bienbao.this.size_item_vachke))
//          {
//            Bienbao.this.textheader.setText(" váº¡ch káº»");
//            continue;
//          }
//          Bienbao.this.textheader.setText("Biá»ƒn cáº¥m");
//        }
//      }
//
//      public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
//      {
//      }
//    });
//  }
//
//  private int setDetailBienbao(int paramInt)
//  {
//    ItemBienbao localItemBienbao = (ItemBienbao)this.list_for_adapter.get(paramInt);
//    Log.d("id cua bien bao ", localItemBienbao.id);
//    this.bienbaohientai = (Integer.parseInt(localItemBienbao.id) - 1);
//    if (this.bienbaohientai == 0)
//    {
//      adddatatoView(0, this.bienbaohientai);
//      adddatatoView(1, 1 + this.bienbaohientai);
//      adddatatoView(2, 2 + this.bienbaohientai);
//      this.cuon_ngang.setCurrentScreen(0, false);
//    }
//    while (true)
//    {
//      return this.bienbaohientai;
//      if (this.bienbaohientai == 206)
//      {
//        adddatatoView(0, this.bienbaohientai - 2);
//        adddatatoView(1, this.bienbaohientai - 1);
//        adddatatoView(2, this.bienbaohientai);
//        this.cuon_ngang.setCurrentScreen(2, false);
//        continue;
//      }
//      adddatatoView(0, this.bienbaohientai - 1);
//      adddatatoView(1, this.bienbaohientai);
//      adddatatoView(2, 1 + this.bienbaohientai);
//      this.cuon_ngang.setCurrentScreen(1, false);
//    }
//  }
//
//  private void setView()
//  {
//    UnsetDetailBienbao();
//    this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.list_for_adapter, this.map));
//    Rect localRect = new Rect();
//    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
//    this.height = localRect.bottom;
//  }
//
//  private void showDialogConfirmExit()
//  {
//    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//    localBuilder.setMessage(Html.fromHtml("Báº¡n muá»‘n thoÃ¡t chÆ°Æ¡ng trÃ¬nh?")).setCancelable(false).setPositiveButton("ThoÃ¡t", new DialogInterface.OnClickListener()
//    {
//      public void onClick(DialogInterface paramDialogInterface, int paramInt)
//      {
//        Bienbao.this.finish();
//      }
//    }).setNegativeButton("KhÃ´ng", new DialogInterface.OnClickListener()
//    {
//      public void onClick(DialogInterface paramDialogInterface, int paramInt)
//      {
//      }
//    });
//    localBuilder.create().show();
//  }
//
//  private void showDialogNotInformation()
//  {
//    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//    localBuilder.setMessage(Html.fromHtml("KhÃ´ng tÃ¬m tháº¥y má»¥c nÃ o!")).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
//    {
//      public void onClick(DialogInterface paramDialogInterface, int paramInt)
//      {
//      }
//    });
//    localBuilder.create().show();
//  }
//
//  public void onBackPressed()
//  {
//    showDialogConfirmExit();
//  }
//
//  public void onCreate(Bundle paramBundle)
//  {
//    super.onCreate(paramBundle);
//    setContentView(2130903043);
//    this.inputMgr = ((InputMethodManager)getSystemService("input_method"));
//    this.loaibientext = ((TextView)findViewById(2131361821));
//    this.linsearch = ((LinearLayout)findViewById(2131361823));
//    this.idtitle = ((LinearLayout)findViewById(2131361829));
//    this.viewmode = ((Button)findViewById(2131361822));
//    this.linviewinglist = ((LinearLayout)findViewById(2131361828));
//    this.linviewinggrid = ((LinearLayout)findViewById(2131361831));
//    this.linsearchinput = ((LinearLayout)findViewById(2131361825));
//    new kbcheck().start();
//  }
//
//  protected void onDestroy()
//  {
//    this.isrunning = false;
//    super.onDestroy();
//  }
//
//  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
//  {
//    int i;
//    if (paramKeyEvent == null)
//      i = 0;
//    while (true)
//    {
//      return i;
//      if ((paramKeyEvent.getKeyCode() == 66) && (this.load))
//      {
//        this.pharse = this.edit.getText().toString().toLowerCase();
//        if (this.pharse.compareTo("") != 0)
//        {
//          this.dialog = new ProgressDialog(this);
//          this.dialog.setMessage("Ä�ang tÃ¬m kiáº¿m...");
//          this.dialog.show();
//          search localsearch = new search();
//          Void[] arrayOfVoid = new Void[1];
//          arrayOfVoid[0] = null;
//          localsearch.execute(arrayOfVoid);
//        }
//        ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 0);
//        this.load = false;
//        i = 1;
//        continue;
//      }
//      this.load = true;
//      i = 0;
//    }
//  }
//
//  protected void onPause()
//  {
//    this.isactive = false;
//    super.onPause();
//  }
//
//  protected void onResume()
//  {
//    super.onResume();
//    this.isactive = true;
//    if (Toancuc.dangthi)
//      Tab.tabHost.setCurrentTab(2);
//    while (true)
//    {
//      return;
//      Log.d("onresume of Bien bao", "resume bien bao");
//      if (this.loaddata)
//        continue;
//      this.loaddata = true;
//      this.dialog = new ProgressDialog(this);
//      this.dialog.setMessage("Ä�ang táº£i dá»¯ liá»‡u...");
//      this.dialog.show();
//      InitView0 localInitView0 = new InitView0();
//      Void[] arrayOfVoid = new Void[1];
//      arrayOfVoid[0] = null;
//      localInitView0.execute(arrayOfVoid);
//    }
//  }
//
//  public boolean onTouchEvent(MotionEvent paramMotionEvent)
//  {
//    if (paramMotionEvent.getAction() == 0)
//      this.inputMgr.hideSoftInputFromInputMethod(this.header1.getWindowToken(), 0);
//    return super.onTouchEvent(paramMotionEvent);
//  }
//
//  public void onWindowFocusChanged(boolean paramBoolean)
//  {
//    Rect localRect = new Rect();
//    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
//    if (localRect.bottom < this.height)
//      Tab.tabwidget.setVisibility(8);
//    super.onWindowFocusChanged(paramBoolean);
//  }
//
//  public ArrayList<ItemBienbao> searchItem()
//  {
//    this.list_search.clear();
//    this.list_bienhientai.clear();
//    this.list_khac.clear();
//    Iterator localIterator = this.list_item_bienbao.iterator();
//    while (true)
//    {
//      if (!localIterator.hasNext())
//      {
//        Log.d("tim thay", this.list_search.size());
//        Log.d("list_bienhientai", this.list_bienhientai.size());
//        Log.d("list_khac", this.list_khac.size());
//        return this.list_search;
//      }
//      ItemBienbao localItemBienbao = (ItemBienbao)localIterator.next();
//      Log.d("Ten bien bao", localItemBienbao.ten_bien_bao);
//      if (!search(this.pharse, localItemBienbao.ten_bien_bao))
//        continue;
//      if (this.bienhientai == 0)
//      {
//        this.list_search.add(localItemBienbao);
//        continue;
//      }
//      if (Integer.parseInt(localItemBienbao.phan_loai) == this.bienhientai)
//      {
//        this.list_bienhientai.add(localItemBienbao);
//        continue;
//      }
//      this.list_khac.add(localItemBienbao);
//    }
//  }
//
//  public void setGridView()
//  {
//    adddatatoGridView(0, 0);
//    adddatatoGridView(1, 1);
//    adddatatoGridView(2, 2);
//    adddatatoGridView(3, 3);
//    adddatatoGridView(4, 4);
//    adddatatoGridView(5, 5);
//  }
//
//  public void setTextButton(String paramString)
//  {
//    this.select.setText(paramString);
//  }
//
//  /** @deprecated */
//  public void setViewBienbao(int paramInt)
//  {
//    monitorenter;
//    while (true)
//    {
//      try
//      {
//        this.bienhientai = paramInt;
//        this.search = false;
//        if (paramInt != 0)
//          continue;
//        initData(this.list_item_bienbao);
//        this.list_for_adapter = new ArrayList();
//        initData_showAll(this.list_for_adapter);
//        this.select.setText("Táº¥t cáº£");
//        setView();
//        this.nochangetitle = false;
//        return;
//        this.nochangetitle = true;
//        UnsetDetailBienbao();
//        if (paramInt == 1)
//        {
//          this.select.setText("Biá»ƒn cáº¥m");
//          this.textheader.setText("Biá»ƒn cáº¥m");
//          this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_biencam, this.map));
//          this.list_for_adapter = this.bb_biencam;
//          continue;
//        }
//      }
//      finally
//      {
//        monitorexit;
//      }
//      if (paramInt == 2)
//      {
//        this.select.setText("Nguy hiá»ƒm");
//        this.textheader.setText("Biá»ƒn nguy hiá»ƒm");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_nguyhiem, this.map));
//        this.list_for_adapter = this.bb_nguyhiem;
//        continue;
//      }
//      if (paramInt == 3)
//      {
//        this.select.setText("Hiá»‡u lá»‡nh");
//        this.textheader.setText("Biá»ƒn hiá»‡u lá»‡nh");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_hieulenh, this.map));
//        this.list_for_adapter = this.bb_hieulenh;
//        continue;
//      }
//      if (paramInt == 4)
//      {
//        this.select.setText("Chá»‰ dáº«n");
//        this.textheader.setText("Biá»ƒn chá»‰ dáº«n");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_chidan, this.map));
//        this.list_for_adapter = this.bb_chidan;
//        continue;
//      }
//      if (paramInt == 5)
//      {
//        this.select.setText("Biá»ƒn phá»¥");
//        this.textheader.setText("Biá»ƒn phá»¥");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_bienphu, this.map));
//        this.list_for_adapter = this.bb_bienphu;
//        continue;
//      }
//      if (paramInt != 6)
//        continue;
//      this.select.setText("Váº¡ch káº»");
//      this.textheader.setText("Váº¡ch káº»");
//      this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.bb_vachke, this.map));
//      this.list_for_adapter = this.bb_vachke;
//    }
//  }
//
//  /** @deprecated */
//  public void setViewBienbaos(int paramInt)
//  {
//    monitorenter;
//    while (true)
//    {
//      try
//      {
//        this.bienhientai = paramInt;
//        this.search = false;
//        if (paramInt != 0)
//          continue;
//        initData(this.list_item_bienbao);
//        this.list_for_adapter = new ArrayList();
//        initData_showAll(this.list_for_adapter);
//        this.select.setText("Táº¥t cáº£");
//        setView();
//        this.nochangetitle = false;
//        this.sttdatahientai = 0;
//        this.cuon_ngang_grid.setCurrentScreen(0, false);
//        this.xemtungvung = false;
//        return;
//        this.xemtungvung = true;
//        this.nochangetitle = true;
//        UnsetDetailBienbao();
//        if (paramInt == 1)
//        {
//          this.select.setText("Biá»ƒn cáº¥m");
//          this.textheader.setText("Biá»ƒn cáº¥m");
//          this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_biencams, this.map));
//          this.list_for_adapter = Toancuc.bb_biencams;
//          this.sttdatahientai = 0;
//          this.cuon_ngang_grid.setCurrentScreen(0, false);
//          continue;
//        }
//      }
//      finally
//      {
//        monitorexit;
//      }
//      if (paramInt == 2)
//      {
//        this.select.setText("Nguy hiá»ƒm");
//        this.textheader.setText("Biá»ƒn nguy hiá»ƒm");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_nguyhiems, this.map));
//        this.list_for_adapter = Toancuc.bb_nguyhiems;
//        this.sttdatahientai = 1;
//        this.cuon_ngang_grid.setCurrentScreen(1, false);
//        continue;
//      }
//      if (paramInt == 3)
//      {
//        this.select.setText("Hiá»‡u lá»‡nh");
//        this.textheader.setText("Biá»ƒn hiá»‡u lá»‡nh");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_hieulenhs, this.map));
//        this.list_for_adapter = Toancuc.bb_hieulenhs;
//        this.sttdatahientai = 2;
//        this.cuon_ngang_grid.setCurrentScreen(2, false);
//        continue;
//      }
//      if (paramInt == 4)
//      {
//        this.select.setText("Chá»‰ dáº«n");
//        this.textheader.setText("Biá»ƒn chá»‰ dáº«n");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_chidans, this.map));
//        this.list_for_adapter = Toancuc.bb_chidans;
//        this.sttdatahientai = 3;
//        this.cuon_ngang_grid.setCurrentScreen(3, false);
//        continue;
//      }
//      if (paramInt == 5)
//      {
//        this.select.setText("Biá»ƒn phá»¥");
//        this.textheader.setText("Biá»ƒn phá»¥");
//        this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_bienphus, this.map));
//        this.list_for_adapter = Toancuc.bb_bienphus;
//        this.sttdatahientai = 4;
//        this.cuon_ngang_grid.setCurrentScreen(4, false);
//        continue;
//      }
//      if (paramInt != 6)
//        continue;
//      this.select.setText("Váº¡ch káº»");
//      this.textheader.setText("Váº¡ch káº»");
//      this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, Toancuc.bb_vachkes, this.map));
//      this.list_for_adapter = Toancuc.bb_vachkes;
//      this.sttdatahientai = 5;
//      this.cuon_ngang_grid.setCurrentScreen(5, false);
//    }
//  }
//
//  public void setViewSearch()
//  {
//    initData(this.list_search);
//    initData_showAll(this.list_search);
//    Log.d("setview search", "setview search");
//    if (this.bienhientai == 0)
//    {
//      this.list1.setAdapter(new BienbaoListAdapter(this, 2130903050, this.list_search, this.map));
//      this.search = false;
//      this.list_for_adapter = this.list_search;
//    }
//    while (true)
//    {
//      return;
//      this.textheader.setText(getTitle(this.bienhientai));
//      this.list1.setAdapter(new BienbaoListSearchAdapter(this, 2130903050, combine(), this.map));
//      this.search = true;
//    }
//  }
//
//  protected class InitView extends AsyncTask<Void, Void, Void>
//  {
//    protected InitView()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.select = ((Button)Bienbao.this.findViewById(2131361824));
//      Bienbao.this.header1 = ((LinearLayout)Bienbao.this.findViewById(2131361820));
//      Bienbao.this.detailbienbaolinearlayout = ((LinearLayout)Bienbao.this.findViewById(2131361827));
//      Bienbao.this.cuon_ngang = new HorizontalPaper(Bienbao.this);
//      Bienbao.this.li = ((LayoutInflater)Bienbao.this.getSystemService("layout_inflater"));
//      Bienbao.this.cuon_ngang.setOnScreenSwitchListener(Bienbao.this.switchListener);
//      Bienbao.this.cuon_ngang.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
//      Bienbao.this.cuon_ngang_grid = new HorizontalPaper(Bienbao.this);
//      Bienbao.this.li = ((LayoutInflater)Bienbao.this.getSystemService("layout_inflater"));
//      Bienbao.this.cuon_ngang_grid.setOnScreenSwitchListener(Bienbao.this.switchListener_grid);
//      Bienbao.this.cuon_ngang_grid.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
//      Bienbao.this.edit = ((EditText)Bienbao.this.findViewById(2131361826));
//      Bienbao.this.edit.setOnEditorActionListener(Bienbao.this);
//      Bienbao.this.list1 = ((ListView)Bienbao.this.findViewById(2131361830));
//      Bienbao.this.textheader = ((TextView)Bienbao.this.findViewById(2131361817));
//      Bienbao.this.back = ((Button)Bienbao.this.findViewById(2131361811));
//      Bienbao.this.back.setVisibility(8);
//      Bienbao.this.setClick();
//      Bienbao.this.setCrollList();
//      Bienbao.this.setAtributeList(Bienbao.this.list1);
//      Bienbao.this.map = Toancuc.getMap();
//      Bienbao.this.list_for_adapter = new ArrayList();
//      Bienbao.this.addView();
//      Bienbao.this.setView();
//      Bienbao.laydulieu locallaydulieu = new Bienbao.laydulieu(Bienbao.this);
//      Void[] arrayOfVoid = new Void[1];
//      arrayOfVoid[0] = null;
//      locallaydulieu.execute(arrayOfVoid);
//    }
//  }
//
//  protected class InitView0 extends AsyncTask<Void, Void, Void>
//  {
//    protected InitView0()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.InitView localInitView = new Bienbao.InitView(Bienbao.this);
//      Void[] arrayOfVoid = new Void[1];
//      arrayOfVoid[0] = null;
//      localInitView.execute(arrayOfVoid);
//    }
//  }
//
//  class ItemView
//  {
//    int from;
//    int to;
//
//    public ItemView(int paramInt1, int arg3)
//    {
//      this.from = paramInt1;
//      int i;
//      this.to = i;
//    }
//  }
//
//  protected class changelayout_next extends AsyncTask<Void, Void, Void>
//  {
//    protected changelayout_next()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.cuon_ngang.setCurrentScreen(1, false);
//      Bienbao.this.adddatatoView(2, 1 + Bienbao.this.bienbaohientai);
//    }
//  }
//
//  protected class changelayout_next_grid extends AsyncTask<Void, Void, Void>
//  {
//    protected changelayout_next_grid()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.cuon_ngang_grid.setCurrentScreen(1, false);
//      Bienbao.this.adddatatoGridView(2, 1 + Bienbao.this.sttdatahientai);
//    }
//  }
//
//  protected class changelayout_prev extends AsyncTask<Void, Void, Void>
//  {
//    protected changelayout_prev()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.cuon_ngang.setCurrentScreen(1, false);
//      Bienbao.this.adddatatoView(0, Bienbao.this.bienbaohientai - 1);
//    }
//  }
//
//  protected class changelayout_prev_grid extends AsyncTask<Void, Void, Void>
//  {
//    protected changelayout_prev_grid()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.cuon_ngang_grid.setCurrentScreen(1, false);
//      Bienbao.this.adddatatoGridView(0, Bienbao.this.sttdatahientai - 1);
//    }
//  }
//
//  class itembienbao
//  {
//    String id = null;
//    String noidung = null;
//    int phanloai = -1;
//    String ten = null;
//    String tenanh = null;
//
//    itembienbao()
//    {
//    }
//  }
//
//  class kbcheck extends Thread
//  {
//    kbcheck()
//    {
//    }
//
//    public void run()
//    {
//      while (true)
//      {
//        if (!Bienbao.this.isrunning)
//          return;
//        try
//        {
//          if (Bienbao.this.isactive)
//            Bienbao.this.exe();
//          Thread.sleep(100L);
//        }
//        catch (InterruptedException localInterruptedException)
//        {
//          localInterruptedException.printStackTrace();
//        }
//      }
//    }
//  }
//
//  protected class laydulieu extends AsyncTask<Void, Void, Void>
//  {
//    protected laydulieu()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      Bienbao.this.getDataBienbao();
//      Bienbao.this.initData(Bienbao.this.list_item_bienbao);
//      Bienbao.this.initData_showAll(Bienbao.this.list_for_adapter);
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Bienbao.this.setView();
//      Bienbao.this.setGridView();
//      Bienbao.this.dialog.dismiss();
//    }
//  }
//
//  protected class search extends AsyncTask<Void, Void, Void>
//  {
//    ArrayList<ItemBienbao> items;
//
//    protected search()
//    {
//    }
//
//    protected Void doInBackground(Void[] paramArrayOfVoid)
//    {
//      this.items = Bienbao.this.searchItem();
//      return null;
//    }
//
//    protected void onPostExecute(Void paramVoid)
//    {
//      Log.d("******", "post execute");
//      Bienbao.this.dialog.dismiss();
//      if ((this.items.isEmpty()) && (Bienbao.this.list_bienhientai.isEmpty()) && (Bienbao.this.list_khac.isEmpty()))
//      {
//        Log.d("items.empty", "empty");
//        Bienbao.this.setView();
//        Bienbao.this.showDialogNotInformation();
//      }
//      while (true)
//      {
//        return;
//        Bienbao.this.UnsetDetailBienbao();
//        Bienbao.this.setViewSearch();
//      }
//    }
//  }
//}