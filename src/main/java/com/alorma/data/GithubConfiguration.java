package com.alorma.data;

import com.alorma.data.github.IssueService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Configuration
public class GithubConfiguration {

    private static final String ENDPOINT = "https://api.github.com";

    @Bean
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(this::addInterceptors).build();
    }

    private Response addInterceptors(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();

        builder.addHeader("User-Agent", "Gitskarios");
        builder.addHeader("Accept", "application/vnd.github.v3.json");
        builder.method(original.method(), original.body());

        return chain.proceed(builder.build());
    }

    @Bean
    public Retrofit provideRetrofit(OkHttpClient okClient) {
        return new Retrofit.Builder().baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create()).client(okClient).build();
    }

    @Bean
    public IssueService provideIssueService(Retrofit retrofit) {
        return retrofit.create(IssueService.class);
    }
}
