package com.trendyol.playlist.config;


import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        try {
            couchbaseCluster.query("CREATE INDEX playlistArray ON `Playlist`(DISTINCT ARRAY `m`.`id` FOR m in `tracks` END)");
            couchbaseCluster.query("CREATE INDEX `playlist_id` ON `Playlist`(`id`)");
            couchbaseCluster.query("CREATE INDEX `playlist_userid` ON `Playlist`(`userId`)");
        } catch (Exception e) {

        }
    }
}
