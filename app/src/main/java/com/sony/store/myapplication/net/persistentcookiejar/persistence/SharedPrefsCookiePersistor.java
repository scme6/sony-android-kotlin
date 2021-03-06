/*
 * Copyright (C) 2016 Francisco José Montiel Navarro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sony.store.myapplication.net.persistentcookiejar.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.Cookie;

public class SharedPrefsCookiePersistor implements CookiePersistor {


    private SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        final String SHARED_PREFERENCES_NAME = "CookiePersistence";

        sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public List<Cookie> loadAll() {
        List<Cookie> cookies = new ArrayList<>();
        List<String> preRemoved = new ArrayList<>();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String serializedCookie = (String) entry.getValue();
            Cookie cookie = new SerializableCookie().decode(serializedCookie);
            if (cookie != null) {
                Log.d("TAG","cookie-loadAll-add:" + cookies);
                cookies.add(cookie);
            } else {
                Log.d("TAG","cookie-loadAll-remove:" + cookies);
                preRemoved.add(entry.getKey());
            }
        }

        for (String key : preRemoved) {
            sharedPreferences.getAll().remove(key);
        }

        Log.d("TAG","cookie-loadAll:" + cookies);
        return cookies;
    }

    @Override
    public void saveAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (Cookie cookie : cookies) {
            //if (cookie.persistent()) {
            String key = createCookieKey(cookie);
            //Logger.d("cookie-save:" + key);
            editor.putString(key, new SerializableCookie().encode(cookie));
            //}
        }
        editor.apply();
    }

    @Override
    public void removeAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (Cookie cookie : cookies) {
            editor.remove(createCookieKey(cookie));
        }
        editor.apply();
    }

    private static String createCookieKey(Cookie cookie) {
        return (cookie.secure() ? "https" : "http") + "://" + cookie.domain() + cookie.path() + "|" + cookie.name();
    }

    @Override
    public void removeCookieByRegular(String keyRegular) {
        List<String> preRemoved = new ArrayList<>();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String serializedCookie = (String) entry.getValue();
            Cookie cookie = new SerializableCookie().decode(serializedCookie);
            if ((entry.getKey() + "").matches(keyRegular)) {
                preRemoved.add(entry.getKey());
                //Logger.d(this, "removeCookieByRegular:" + keyRegular + ":" + entry.getKey());
            }
        }

        for (String key : preRemoved) {
            sharedPreferences.edit().remove(key).commit();
        }
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().commit();
    }
}
