package pers.solax.Base;

import com.rishiqing.conn.ConnectClient;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import pers.solax.Base.factory.IndexFactory;
import pers.solax.util.Connection;

/**
 * Created by solax on 2017-5-5.
 */
public class Bench {
    protected ConnectClient esClient = Connection.getConnectClient();;

    protected IndicesAdminClient adminClient = this.esClient.getTransportClient().admin().indices();

    protected IndexFactory indexFactory = IndexFactory.getInstance();

    public ConnectClient getEsClient() {
        return esClient;
    }

    public void setEsClient(ConnectClient esClient) {
        this.esClient = esClient;
        this.adminClient = this.esClient.getTransportClient().admin().indices();
    }

    public IndicesAdminClient getAdminClient() {
        return adminClient;
    }

    public void setAdminClient(IndicesAdminClient adminClient) {
        this.adminClient = adminClient;
    }

    public boolean indexExist (String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = this.adminClient.exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }

    public void createIndex (String index) {
        this.adminClient.prepareCreate(index).get();
        System.out.println(index + " - create success");
    }
}
