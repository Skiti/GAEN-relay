/****************************************************************************************
 * Copyright (c) 2016, 2017, 2019 Vincent Hiribarren                                    *
 *                                                                                      *
 * This program is free software; you can redistribute it and/or modify it under        *
 * the terms of the GNU General Public License as published by the Free Software        *
 * Foundation; either version 3 of the License, or (at your option) any later           *
 * version.                                                                             *
 *                                                                                      *
 * Linking Beacon Simulator statically or dynamically with other modules is making      *
 * a combined work based on Beacon Simulator. Thus, the terms and conditions of         *
 * the GNU General Public License cover the whole combination.                          *
 *                                                                                      *
 * As a special exception, the copyright holders of Beacon Simulator give you           *
 * permission to combine Beacon Simulator program with free software programs           *
 * or libraries that are released under the GNU LGPL and with independent               *
 * modules that communicate with Beacon Simulator solely through the                    *
 * net.alea.beaconsimulator.bluetooth.AdvertiseDataGenerator and the                    *
 * net.alea.beaconsimulator.bluetooth.AdvertiseDataParser interfaces. You may           *
 * copy and distribute such a system following the terms of the GNU GPL for             *
 * Beacon Simulator and the licenses of the other code concerned, provided that         *
 * you include the source code of that other code when and as the GNU GPL               *
 * requires distribution of source code and provided that you do not modify the         *
 * net.alea.beaconsimulator.bluetooth.AdvertiseDataGenerator and the                    *
 * net.alea.beaconsimulator.bluetooth.AdvertiseDataParser interfaces.                   *
 *                                                                                      *
 * The intent of this license exception and interface is to allow Bluetooth low energy  *
 * closed or proprietary advertise data packet structures and contents to be sensibly   *
 * kept closed, while ensuring the GPL is applied. This is done by using an interface   *
 * which only purpose is to generate android.bluetooth.le.AdvertiseData objects.        *
 *                                                                                      *
 * This exception is an additional permission under section 7 of the GNU General        *
 * Public License, version 3 (“GPLv3”).                                                 *
 *                                                                                      *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                      *
 * You should have received a copy of the GNU General Public License along with         *
 * this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 ****************************************************************************************/

package com.spritz.gaenrelay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.akexorcist.localizationactivity.LocalizationActivity;

import com.spritz.gaenrelay.bluetooth.model.BeaconType;

import com.spritz.gaenrelay.FragmentBeaconEdit;
import com.spritz.gaenrelay.R;

import java.util.UUID;

public class ActivityBeaconEdit extends LocalizationActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TYPE = "EXTRA_TYPE";

    private final static String TAG_FRAGMENT_BEACON_EDIT = "fragmentBeaconEdit";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // If not already added to the Fragment manager add it. If you don't do this a new Fragment will be added every time this method is called (Such as on orientation change)
        if (savedInstanceState == null) {
            FragmentBeaconEdit fragmentBeaconEdit = new FragmentBeaconEdit();
            fragmentBeaconEdit.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragmentBeaconEdit, TAG_FRAGMENT_BEACON_EDIT)
                    .commit();
        }
   }

    public static void createNewBeacon(Activity activity, BeaconType type) {
        Intent intent = new Intent(activity, ActivityBeaconEdit.class);
        intent.putExtra(EXTRA_TYPE, type);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    public static void editBeacon(Activity activity, UUID id) {
        Intent intent = new Intent(activity, ActivityBeaconEdit.class);
        intent.putExtra(EXTRA_ID, id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentBeaconEdit fragmentBeaconEdit = (FragmentBeaconEdit) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_BEACON_EDIT);
        switch (item.getItemId()) {
            case android.R.id.home:
                fragmentBeaconEdit.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentBeaconEdit fragmentBeaconEdit = (FragmentBeaconEdit) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_BEACON_EDIT);
        fragmentBeaconEdit.onBackPressed();
    }
}