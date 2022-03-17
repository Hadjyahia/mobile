package hadj.tn.test;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import hadj.tn.test.fragment.AboutUsFragment;
import hadj.tn.test.fragment.DonateBloodFragment;
import hadj.tn.test.fragment.EducateFragment;
import hadj.tn.test.fragment.FindDonorFragment;
import hadj.tn.test.fragment.HistoriqueFragment;
import hadj.tn.test.fragment.HomeFragment;
import hadj.tn.test.fragment.NotificationFragment;
import hadj.tn.test.fragment.ProfileFragment;
import hadj.tn.test.menu.DrawerAdapter;
import hadj.tn.test.menu.DrawerItem;
import hadj.tn.test.menu.SimpleItem;
import hadj.tn.test.menu.SpaceItem;


import java.util.Arrays;



public class HomeActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_HOME = 0;
    private static final int POS_PROFILE = 1;
    private static final int POS_NOTIF = 2;
    private static final int POS_HISTO = 3;
    private static final int POS_ABOUT_US = 4;
    private static final int POS_LOGOUT = 5;
    private String[] screenTitles;
    private Drawable[] screenIcons;

    public static Fragment fragment;

    private SlidingRootNav slidingRootNav;

    ChipNavigationBar chipNavigationBar ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_PROFILE),
                createItemFor(POS_NOTIF),
                createItemFor(POS_HISTO),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_HOME);

        //getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.ic_home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.ic_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.ic_findD:
                        fragment=new FindDonorFragment();
                        break;
                    case R.id.ic_db:
                        fragment=new DonateBloodFragment();
                        break;
                    case R.id.ic_educ:
                        fragment=new EducateFragment();
                        break;
                }
                if (fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }
            }
        });
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT){
            finish();
        }
        if (position == POS_HOME) {
            fragment = new HomeFragment();
        }
        if (position == POS_PROFILE) {
            fragment = new ProfileFragment();
        }
        if (position == POS_NOTIF) {
            fragment = new NotificationFragment();
        }
        if (position == POS_HISTO) {
            fragment = new HistoriqueFragment();
        }
        if (position == POS_ABOUT_US) {
            fragment = new AboutUsFragment();
        }
        slidingRootNav.closeMenu();
        if (fragment!=null)
            showFragment(fragment);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.teal_700))
                .withSelectedTextTint(color(R.color.teal_700));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}
