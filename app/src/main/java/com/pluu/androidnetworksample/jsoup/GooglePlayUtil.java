package com.pluu.androidnetworksample.jsoup;

import android.content.Context;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by nohhs on 2015-08-26.
 */
public class GooglePlayUtil {

	private static final String PLAY_STORE = "https://play.google.com/store/apps/details?id=";

	public static void checkVersion(Context context, final String id) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				GooglePlayEvent event = new GooglePlayEvent();

				try {
					Document doc = Jsoup.connect(PLAY_STORE + id)
										.timeout(3000)
										.get();

					event.imgUrl = doc.select(".cover-container .cover-image").attr("src");
					event.appName = doc.select(".document-title").text();
					event.verName = doc.select("div[itemprop=softwareVersion]").text();
					event.verCode = doc.select(".review-filters div").last().select("li").last().select(".dropdown-child")
									   .attr("data-dropdown-value");
					event.isSuccess = true;
				} catch (IOException e) {
					e.printStackTrace();
				}

				OttoBusHolder.get().post(event);
			}
		}).start();
	}

}
