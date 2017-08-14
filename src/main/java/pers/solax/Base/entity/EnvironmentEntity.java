package pers.solax.Base.entity;

/**
 * Created by solax on 2017-4-5.
 */
public class EnvironmentEntity {
    public String LOGSTASH_HOME = System.getenv("LOGSTASH_HOME") + "/";

    public String BASE_PATH = System.getenv("LOGSTASH_HOME") + "/import/";
}
