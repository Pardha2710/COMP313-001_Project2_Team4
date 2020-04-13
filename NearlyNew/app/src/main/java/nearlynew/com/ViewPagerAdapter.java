package nearlynew.com;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new AbuyersFragment();
        }
        else if (position == 1)
        {
            fragment = new AsellersFragment();
        }
        else if (position == 2)
        {
            fragment = new AproductsFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Buyers";
        }
        else if (position == 1)
        {
            title = "Sellers";
        }
        else if (position == 2)
        {
            title = "Products";
        }
        return title;
    }
}
