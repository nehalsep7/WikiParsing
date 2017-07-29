package com.example.nehal.wikiparsing;

import com.example.nehal.wikiparsing.Model.WikiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by nehal on 28/7/17.
 */

public interface Api {
    @GET("w/api.php")
    Observable<WikiResponse> getWikiResponse(@Query("action") String action, @Query("format") String format, @Query("formatversion") String formatVersion, @Query("prop") String prop,@Query("generator") String generator,@Query("redirects") String redirect,@Query("piprop") String piprop,@Query("pithumbsize") String piThumbSize,@Query("pilimit") String piLimit,@Query("wbptterms") String description,@Query("gpssearch") String gpsSearch,@Query("gpslimit") String limit,@Query("inprop") String inProp);
}
